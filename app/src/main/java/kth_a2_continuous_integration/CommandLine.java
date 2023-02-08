package kth_a2_continuous_integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandLine {

    /**
     * The function executes a console/terminal command and returns the text output as a string.
     * Designed to work on unix based and windows systems.
     * @param command
     * @param path
     * @return String: Console output
     * @throws IOException
     */
    public static String exec(String command, String path) throws IOException {
        Process process;
        String os = System.getProperty("os.name").toLowerCase();
        if("windows 10".equals(os) || "windows 11".equals(os)) process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command}, null, new File(path));
        else                                                   process = Runtime.getRuntime().exec(new String[]{"bin/sh", "-c", command}, null, new File(path));

        return output(process.getInputStream());
    }

    private static String output(InputStream iStream) {
        String msg = "";
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
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
