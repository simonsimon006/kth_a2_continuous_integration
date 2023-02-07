package kth_a2_continuous_integration;

import java.io.File;
import java.io.IOException;

public class CommandLine {
    public static void exec(String command, String path) throws IOException {
        Process process;
        String os = System.getProperty("os.name").toLowerCase();
        if("windows 10".equals(os)) process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command}, null, new File(path));
        else                        process = Runtime.getRuntime().exec(new String[]{"bin/sh", "-c", command}, null, new File(path));
    }
}
