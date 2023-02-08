package kth_a2_continuous_integration;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;

public class CommandLineTest {
    /**
     * Tests that CommandLine.exec() returns the appropriate string upon being called with a command.
     * @throws IOException
     * 
     */
    @Test
    @DisplayName("CommandLine test with valid input")
    void CommandLineValid() throws IOException {
        String result = CommandLine.exec("echo test", "/");
        Boolean correct = "test\n".equals(result);
        assertTrue(correct, "CommandLine.exec() did not return correct string");
    }
}
