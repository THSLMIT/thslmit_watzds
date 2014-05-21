package com.thslmit.watz;

public class AppVar {
	
	/* Application Constants */
	public static final String WATZ_CONFIG = "watz_config.cfg";
	
	/* System */
	private static String DSDeviceID = "";
	private static String DSDeviceIMEI = "";
	private static int DSDeviceNum = 0;
	
	private static String SWDeviceID = "";
	private static String SWDeviceIMEI = "";
	private static int SWDeviceNum = 0;
	
	private static boolean isNewDevice = false;
	
	/** Docking Station Parameters */
	public static String getDSDevID() {
		return DSDeviceID;
	}
	
	public static void setDSDevID(String deviceID) {
		DSDeviceID = deviceID;
	}
	
	public static String getDSDevIMEI() {
		return DSDeviceIMEI;
	}
	
	public static void setDSDevIMEI(String imei) {
		DSDeviceIMEI = imei;
	}
	
	public static int getDSDevNum() {
		return DSDeviceNum;
	}
	
	public static void setDSDevNum(int num) {
		DSDeviceNum = num;
	}

	/** Smartwatch Device Parameters */
	public static String getSWDevID() {
		return SWDeviceID;
	}
	
	public static void setSWDevID(String deviceID) {
		SWDeviceID = deviceID;
	}
	
	public static String getSWDevIMEI() {
		return SWDeviceIMEI;
	}
	
	public static void setSWDevIMEI(String imei) {
		SWDeviceIMEI = imei;
	}
	
	public static int getSWDevNum() {
		return SWDeviceNum;
	}
	
	public static void setSWDevNum(int num) {
		SWDeviceNum = num;
	}
	
	/* System - Device Setup Parameters */
	public static boolean getDevSetup() { 
		return isNewDevice; 
	}
	
	public static void setDevSetup(boolean status) { 
		isNewDevice = status;
	}
}
