package com.thslmit.watz.Utilities;

import java.util.logging.Level;
import java.util.logging.LogRecord;

public class UtDebug {

	private static boolean DEBUG_MODE;
	private static boolean LOGING_ENABLED;	
	private static UtAppLog log;
	
	public UtDebug() {
		this(DEBUG_MODE, LOGING_ENABLED);
	}
	
	public UtDebug(boolean debug_enabled, boolean loging_enabled) {
		DEBUG_MODE = debug_enabled;
		LOGING_ENABLED = loging_enabled;
		
		
		String header = "=================================================\n"
				+ "WATZ Firmware Version: 1.0.0 [2014/04/26]\n";
		if (DEBUG_MODE) header += "> DEBUGGING ENABLED\n";
		if (LOGING_ENABLED) header += "> LOGING ENABLED\n";
		header += "=================================================\n"
				+ "Java Version : " + System.getProperty("java.version");
		
		if (DEBUG_MODE)
			System.out.println(header);
		
		if (LOGING_ENABLED) {
			log = new UtAppLog();
			System.out.println("Log File Directory: " + log.getLogDir());
		}
		
		System.out.println();
	}
	
	public static boolean debugEnabled() {
		return DEBUG_MODE;
	}
	
	public static boolean logingEnabled() {
		return LOGING_ENABLED;
	}
	
	public static void setDebugMode(boolean debug) {
		DEBUG_MODE = debug;
	}
	
	public static void setLoggingMode(boolean log_enable) {
		LOGING_ENABLED = log_enable;
	}
	
	public static void logOut(String msg, Level level) {
		System.out.println(msg);
		if (LOGING_ENABLED) {
			log.publish(new LogRecord(level, msg));
			log.flush();
		}
	}
}
