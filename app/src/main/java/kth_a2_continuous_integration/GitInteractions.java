package kth_a2_continuous_integration;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class GitInteractions {
    public static boolean pull(String url, String branch){
        
        try {
            Git.cloneRepository()
            .setURI("https://github.com/simonsimon006/kth_a2_continuous_integration")
            .setDirectory(new File("gittest")) 
            .call();
            return true;
        } catch (InvalidRemoteException e) {
            // Remote could not be found
            return false;
        } catch (TransportException e) {
            // Could not download repository to location
            return false;
        } catch (GitAPIException e) {
            // Incorrect usage of git api
            return false;
        }
    }

    public static void delete(){
        
    }
}
