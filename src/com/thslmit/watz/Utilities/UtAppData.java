package com.thslmit.watz.Utilities;

import java.io.File;

public class UtAppData {

	private static final String DATA_DIR = System.getProperty("DATA_DIR"); 
	static String app_dir = DATA_DIR != null ? DATA_DIR : "data";
	
	public static String getTempDirectory() {
		try {
			File appData = new File(System.getenv("APPDATA"));
			
			if (!appData.exists()) {
				System.err.println("Application Directory is not found: " + appData.getAbsolutePath());
				return System.getProperty("java.io.tmpdir");
			}
			
			return appData.getAbsolutePath();
		} catch (RuntimeException r) {} // SHHHHHHHHHHHHHHH!
		
		return System.getProperty("java.io.tmpdir");
	}

	public static void setDirectory(String directory) {
		app_dir = directory;
	}

	public static boolean directoryPresent() {
		return new File(getTempDirectory() + "/WATZ").exists();
	}
	
	// Returns true if directory did not exist before.
	public static File getApplicationDirectory() {
		File appDirectory = new File(getTempDirectory() + "/WATZ");
		if (!directoryPresent()) appDirectory.mkdir();
		return appDirectory;
	}
}