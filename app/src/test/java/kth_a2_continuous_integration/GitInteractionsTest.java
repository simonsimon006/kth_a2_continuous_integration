package kth_a2_continuous_integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class GitInteractionsTest {

    @BeforeEach
    void clean() throws IOException{
        GitInteractions.cleanUp();
    }
    
    /**
     * Makes sure an invalid repo will result in an error.
     * @throws IOException
     */
    @Test
    @DisplayName("GitInteractions.pull should throw correct error for faulty input")
    void pullNegativetest() throws IOException{
        Throwable exception = assertThrows(GitAPIException.class, () -> GitInteractions.pull("invalid", "invalid"));
    }
    
    /**
     * Makes sure a sample project can be downloaded and deleted
     * @throws InvalidRemoteException
     * @throws TransportException
     * @throws GitAPIException
     * @throws IOException
     */
    @Test
    @DisplayName("GitInteractions.pull and GitInteractions.cleanup should work for valid input")
    void pullPositiveTest() throws InvalidRemoteException, TransportException, GitAPIException, IOException{
        Path path = Paths.get("git_repo");
        assertFalse(Files.exists(path));
        GitInteractions.pull("https://github.com/hansstammler/DECIDE", "refs/heads/main");
        assertTrue(Files.exists(path));
        GitInteractions.cleanUp();
        assertFalse(Files.exists(path));
    }
    
}
