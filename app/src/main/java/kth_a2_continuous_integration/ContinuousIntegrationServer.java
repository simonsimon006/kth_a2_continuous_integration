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
import org.gradle.tooling.*;

/**
 * Skeleton of a ContinuousIntegrationServer which acts as webhook
 * See the Jetty documentation for API documentation of those classes.
 */
public class ContinuousIntegrationServer extends AbstractHandler {
	private String compilePath = "/home/simon/Code/DECIDE/";
	
	public void handle(String target,
			Request baseRequest,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);

		System.out.println(target);

		// here you do all the continuous integration tasks
		// for example
		// 1st clone your repository
		// 2nd compile the code
		ProjectConnection connection = GradleConnector.newConnector()
				.forProjectDirectory(new File(
						this.compilePath))
				.connect();
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {

			connection.newBuild().forTasks("build").setStandardOutput(output).run();
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		} finally {
			connection.close();
		}
		response.getWriter().println(output.toString());
		output.close();
	}

	// used to start the CI server in command line
	public static void main(String[] args) throws Exception {

		Server server = new Server(9000);
		server.setHandler(new ContinuousIntegrationServer());
		server.start();
		server.join();
	}
}
