package com.thslmit.watz.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;

import com.thslmit.watz.AppVar;
import com.thslmit.watz.Utilities.UtDebug;
import com.thslmit.watz.Utilities.UtFile;
import com.thslmit.watz.Utilities.UtSystem;

public class ConfigFile {
	
	private static PrintWriter pOut;

	public static boolean configExist(String filename) {
		return UtFile.fileExist(filename);
	}
	
	public static boolean validConfig(String filename) {
		if (!configExist(filename)) return false;
		return false;
	}
	
	public static void setupConfig() {
		UtDebug.logOut("Generating WATZ System Configuration File: " + AppVar.WATZ_CONFIG, Level.ALL);
		
		try {
			File configFile;
			if ((configFile = UtFile.getFile(AppVar.WATZ_CONFIG)) == null)
				configFile = UtFile.generateFile(AppVar.WATZ_CONFIG);
			
			// Set Initial System Parameters
			ConfigParams cfg = new ConfigParams();
			cfg.setDSDevID(UtSystem.md5(UtSystem.getDevName()));
			
			pOut = new PrintWriter(new FileOutputStream(configFile));
			pOut.println(cfg.toString());
			pOut.close();
		} catch (Exception e) {
			System.out.println("Error generating configuration file.");
		}
	}
}
