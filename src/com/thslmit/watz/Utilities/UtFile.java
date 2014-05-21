package com.thslmit.watz.Utilities;

import java.io.File;

public class UtFile {
	
	public static boolean fileExist(String filename) {
		return new File(UtAppData.getApplicationDirectory() + "/" + filename).exists();
	}
	
	public static File getFile(String filename) {
		if ((filename == null) || !fileExist(filename)) return null;
		return new File(UtAppData.getApplicationDirectory() + "/" + filename);
	}
 	
	public static File generateFile(String filename) {
		try {
			File f = new File(UtAppData.getApplicationDirectory()+ "/" + filename);
			f.createNewFile();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}