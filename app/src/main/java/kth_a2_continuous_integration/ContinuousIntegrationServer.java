package kth_a2_continuous_integration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * Skeleton of a ContinuousIntegrationServer which acts as webhook
 * See the Jetty documentation for API documentation of those classes.
 */
public class ContinuousIntegrationServer extends AbstractHandler {

	// Set this to the path where you clone the repo to.
	private String compilePath = "git_repo";

	/**
	 * This function connects to the gradle repository stored under
	 * this.compilePath. If target
	 * equals "/compile" it executes the build task in the repo, for "/test" it
	 * exectues the test task.
	 * The console log of these actions is returned as a string.
	 * 
	 * @param target   "The HTTP target as given by the HTTP handle function."
	 * @param response "An HttpServletResponse as given by the HTTP handle
	 *                 function."
	 * @return
	 * @throws IOException
	 */
	String execute(String target) throws IOException {
		String result = "";
		try {
			if (target.equals("/compile")) {
				result += CommandLine.exec("./gradlew build", compilePath);
			} else if (target.equals("/test")) {
				result += CommandLine.exec("./gradlew test", compilePath);
			}
		} catch (Exception e) {
			result += e.getMessage();
			System.out.println(result);
		} 
		return result;
	}

	public void handle(String target,
			Request baseRequest,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);

		// here you do all the continuous integration tasks
		// for example
		// 1st clone your repository
		// 2nd compile the code
		String output = execute(target);
		response.getWriter().println(output.toString());
	}

	// used to start the CI server in command line
	public static void main(String[] args) throws Exception {


		Server server = new Server(9000);
		server.setHandler(new ContinuousIntegrationServer());
		server.start();
		server.join();
	}
}
