package kth_a2_continuous_integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class that handles command executions and console messages, by method exec()
 * (which uses output() as helper method).
 */
public class CommandLine {

    /**
     * Executes a console/terminal command and returns text output as a string.
     * <p>
     * Designed to work on Unix based and Windows systems.
     * @param command   The command to be executed
     * @param path      The path
     * @return Console output
     * @throws IOException
     * @see output()
     */
    public static String exec(String command, String path) throws IOException {
        Process process;
        String os = System.getProperty("os.name").toLowerCase();
        if("windows 10".equals(os) || "windows 11".equals(os)) process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command}, null, new File(path));
        else                                                   process = Runtime.getRuntime().exec(new String[]{"bin/sh", "-c", command}, null, new File(path));

        return output(process.getInputStream());
    }

    /** 
     * Reads input stream and returns console output (the message string).
     * @param iStream   Input stream
     * @return msg      Console output
     */
    private static String output(InputStream iStream) {
        String msg = "";
        try {
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(iStream)
                );
            String line;
            while((line = bReader.readLine()) != null) {
                msg += line + "\n";
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return msg;
    }
}
