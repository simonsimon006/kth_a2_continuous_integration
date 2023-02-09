package kth_a2_continuous_integration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import java.util.logging.*;

/**
 * Skeleton of a Continuous Integration server which acts as webhook.
 * <p>
 * (See the Jetty documentation for API documentation of those classes.)
 */
public class ContinuousIntegrationServer extends AbstractHandler {

	// Set this to the path where you clone the repo to
	private String compilePath = "git_repo";
	private String logpath = "commits.log";
	Logger logger = Logger.getLogger("commits");

	ContinuousIntegrationServer() throws IOException {
		FileHandler handler = new FileHandler(this.logpath, true);
		handler.setLevel(Level.INFO);

		this.logger.addHandler(handler);
		this.logger.addHandler(new ConsoleHandler());
	}
	/**
	 * Executes build and test tasks in the repository.
	 * Connects to the Gradle repository stored under this.compilePath.
	 * <p>
	 * The console log of these actions is returned as a string.
	 * @return result String
	 * @throws IOException
	 * @see Commandline.exec()
	 */
	String execute(String target) throws IOException {
		String result = "";
		try {
				String os = System.getProperty("os.name").toLowerCase();
				result += "BUILD RESULT:\n";
				if("windows 10".equals(os) || "windows 11".equals(os))
					result += CommandLine.exec("gradlew build", compilePath);
				else 
					result += CommandLine.exec("./gradlew build", compilePath);
				result += "TEST RESULT:\n";
				if("windows 10".equals(os) || "windows 11".equals(os))
					result += CommandLine.exec("gradlew test", compilePath);
				else
					result += CommandLine.exec("./gradlew test", compilePath);
		} catch (Exception e) {
			result += e.getMessage();
			System.out.println(result);
		}
		return result;
	}
	
	/** 
	 * Clones repository and compiles the code, and sends email notification.
	 * @param baseRequest
	 * @param request
	 * @param response		An HttpServletResponse as given by the HTTP handle function.
	 * @throws IOException
	 * @throws ServletException
	 * @see EmailSender.sendEmail()
	 */
	public void handle(String target,
			Request baseRequest,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		
		// I am sorry.
		if (target.equals("/logs")) {
			try {
				response.getWriter().write(Files.readString(Path.of(this.logpath)));
			} catch (Exception e) {
				response.getWriter().println(e.getMessage());
			}
			return;
		}
		// here you do all the continuous integration tasks
		// for example
		String output = "";

		String to = "";

		try {
			// 1st clone your repository
			to = GitInteractions.download(request);
			// 2nd compile the code
			output = execute(target);
			GitInteractions.cleanUp();
		} catch (InvalidRemoteException e) {
			output += e.getMessage();
			e.printStackTrace();
		} catch (TransportException e) {
			output += e.getMessage();
			e.printStackTrace();
		} catch (GitAPIException e) {
			output += e.getMessage();
			e.printStackTrace();
		}
    
    // Send email notification
		EmailSender emailSender = new EmailSender();
		String from = "dd2480group19@gmail.com";
		String subject = "Project build";
		String text = output;
		emailSender.sendEmail(to, from, subject, text);

		logger.info(output);

		response.getWriter().println(output);
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

	/** 
	 * Used to start the CI server in command line.
	 * @param args
	 * @throws Exception
	 * @see ContinuousIntegrationServer
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server(9000);
		server.setHandler(new ContinuousIntegrationServer());
		server.start();
		server.join();
	}
}
