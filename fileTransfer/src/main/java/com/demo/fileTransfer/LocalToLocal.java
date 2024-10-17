package com.demo.fileTransfer;

import java.io.File;
import java.io.IOException;

public class LocalToLocal {

	public static void main(String[] args) {
	
		// Specify the absolute Log file path		
		String logDir = "C:\\Users\\VaibhavGupta\\eclipse-workspace\\fileTransfer\\src\\main\\Log Files"; 
		
		// Specify the absolute source file path
		String sourceFile = "C:\\Users\\VaibhavGupta\\eclipse-workspace\\fileTransfer\\src\\main\\old File\\oldFile.txt";
	
		// Specify the absolute destination file path
		String destinationDir = "C:\\Users\\VaibhavGupta\\eclipse-workspace\\fileTransfer\\src\\main\\transfered Files\\NewFile.txt";
																																		
		transferFile(logDir, sourceFile, destinationDir);
	}

	public static void transferFile(String logDir, String sourceFile, String destinationDir) {
		ProcessBuilder processBuilder = new ProcessBuilder(
				"C:\\Users\\VaibhavGupta\\AppData\\Local\\Programs\\Git\\bin\\bash.exe" + "", // Path to bash
				"-c",

				String.format(
						"C:/Users/VaibhavGupta/eclipse-workspace/fileTransfer/src/main/script\\ file/script.sh"
								+ " '%s' '%s' '%s'",
						logDir.replace("\\", "/"), sourceFile.replace("\\", "/"), destinationDir.replace("\\", "/")));

		// Redirect output and error streams for logging
		processBuilder.redirectOutput(new File(logDir + "/transfer_output.log"));
		processBuilder.redirectError(new File(logDir + "/transfer_error.log"));

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
	}
}
	
	