package kth_a2_continuous_integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.util.Scanner;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.json.JSONObject;

/**
 * Handles Git interactions such as downloading repository, 
 * deleting local directory, pulling, 
 */
public class GitInteractions {

    /**
     * Downloads the Git repository and branch specified to the server. 
     * Processes HTTP request automatically.
     * @param request   HTTP request containing Git webhook request 
     *                  with json formatting.
     * @throws IOException
     * @throws InvalidRemoteException
     * @throws TransportException
     * @throws GitAPIException
     */
    public static String download(HttpServletRequest request) 
                            throws IOException, InvalidRemoteException, 
                            TransportException, GitAPIException{        
        JSONObject json = new JSONObject(payload(request));
        String repositoryURL = json.getJSONObject("repository").getString("html_url");
        String branch = json.getString("ref");
        System.out.println(branch);
        System.out.println(repositoryURL);
        pull(repositoryURL, branch);
        return json.getJSONObject("pusher").getString("email");
    }

    /**
     * Deletes local directory where Git repository is stored.
     * @throws IOException
     */
    public static void cleanUp() throws IOException{
        FileUtils.deleteDirectory(new File("git_repo"));
    }

    /**
     * Downloads the Git repository and branch specified to the server.
     * @param url       the URL of the Git repository to check out
     * @param branch    the branch to check out
     * @throws InvalidRemoteException
     * @throws TransportException
     * @throws GitAPIException
     */
    static void pull(String url, String branch) throws InvalidRemoteException, 
                                                    TransportException, 
                                                    GitAPIException{
        Git.cloneRepository()
        .setURI(url)
        .setBranchesToClone(Arrays.asList(branch))
        .setBranch(branch)
        .setDirectory(new File("git_repo")) 
        .call()
        .close();
    }

    /**
     * Returns the json object from the GitHub webhook request payload encoded as a string.
     * @param request the request from the GitHub webhook.
     * @return the payload json formated as a string.
     * @throws IOException
     */
    static String payload(HttpServletRequest request) throws IOException{
        BufferedReader br = request.getReader();
        StringBuilder builder = new StringBuilder(); 
        String line;
        while((line = br.readLine()) != null){
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
