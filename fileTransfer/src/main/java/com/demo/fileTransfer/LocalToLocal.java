package com.demo.fileTransfer;

import java.io.File;
import java.io.IOException;

public class LocalToLocal {

    public static void main(String[] args) {
        // Example usage
        String logDir = "C:\\Transfer check\\Log";            // Specify your log directory
        String sourceFile = "C:\\Transfer check\\newFile.txt"; // Specify the absolute source file path
        String destinationDir = "C:\\New Target\\transferredFile.txt"; // Specify the local destination directory

        transferFile(logDir, sourceFile, destinationDir);
    }

    public static void transferFile(String logDir, String sourceFile, String destinationDir) {
        ProcessBuilder processBuilder = new ProcessBuilder(
        		"C:\\Users\\VaibhavGupta\\AppData\\Local\\Programs\\Git\\bin\\bash.exe"
                + "", // Path to bash
                "-c",
                String.format("C:/Transfer\\ check/ScriptFile/eers.sh '%s' '%s' '%s'", 
                              logDir.replace("\\", "/"), 
                              sourceFile.replace("\\", "/"), 
                              destinationDir.replace("\\", "/"))
        );

        // Redirect output and error streams for logging
        processBuilder.redirectOutput(new File(logDir + "/transfer_output.log"));
        processBuilder.redirectError(new File(logDir + "/transfer_error.log"));

        // Print the command for debugging
        System.out.println("Command: " + String.join(" ", processBuilder.command()));

        try {
            // Start the process
            Process process = processBuilder.start();

            // Wait for the process to complete and get exit code
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("File transferred successfully.");
            } else {
                System.err.println("File transfer failed with exit code: " + exitCode);
            }
        } catch (IOException e) {
            System.err.println("IOException occurred while executing the script: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Process was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }}
    /*
    public static void transferFile(String logDir, String sourceFile, String destinationDir) {
//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "C:\\Transfer check\\ScriptFile\\eers.sh", // Path to your shell script
//                logDir,                                   // Log directory
//                sourceFile,                               // Source file
//                destinationDir                            // Destination directory
//        );
//    ProcessBuilder processBuilder = new ProcessBuilder(
//            "C:\\Users\\VaibhavGupta\\AppData\\Local\\Programs\\Git\\bin\\bash.exe", // Path to bash executable
//            "-c", // Command to run
//            "C:\\Transfer check\\ScriptFile\\eers.sh " + logDir + " " + sourceFile + " " + destinationDir // Your shell script
//    );
    	ProcessBuilder processBuilder = new ProcessBuilder(
    	        "C:\\Program Files\\Git\\usr\\bin\\bash.exe", // Path to bash
    	        "-c",
    	        String.format("C:/Transfer\\ check/ScriptFile/eers.sh '%s' '%s' '%s'", 
    	                      logDir.replace("\\", "/"), 
    	                      sourceFile.replace("\\", "/"), 
    	                      destinationDir.replace("\\", "/"))
    	);

        // Redirect output and error streams for logging
        processBuilder.redirectOutput(new File(logDir + "/transfer_output.log"));
        processBuilder.redirectError(new File(logDir + "/transfer_error.log"));
        
     // Print the command for debugging
        System.out.println("Command: " + processBuilder.command());
        
        try {
            // Start the process
            Process process = processBuilder.start();

            // Wait for the process to complete and get exit code
            int exitCode = process.waitFor();
            System.out.println("WaitFor: " + process.waitFor());
            if (exitCode == 0) {
                System.out.println("File transferred successfully.");
            } else {
                System.err.println("File transfer failed with exit code: " + exitCode);
            }
        } catch (IOException e) {
            System.err.println("IOException occurred while executing the script: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Process was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
}*/