package com.thslmit.watz.Configuration;

import com.thslmit.watz.AppVar;

public class ConfigParams {

	public static final String[] configKey = {
		"DSDeviceID", "DSDeviceIMEI", "DSDeviceNum", 
		"SWDeviceID", "SWDeviceIMEI", "SWDeviceNum"
	};
	
	// Docking Station GSM Constants
	private String DSDeviceID;
	private String DSDeviceIMEI;
	private int DSDeviceNum;
	
	// Smartwatch Device Constants
	private String SWDeviceID;
	private String SWDeviceIMEI;
	private int SWDeviceNum;
	
	public ConfigParams() {
		DSDeviceID = "";
		DSDeviceIMEI = "";
		DSDeviceNum = 0;
		
		SWDeviceID = "";
		SWDeviceIMEI = "";
		SWDeviceNum = 0;
	}
	
	public void parseConfig(Config cfg) {
		try {
			//System.out.println(cfg.getString(configKey[0]));
			
			setDSDevID(cfg.getString(configKey[0]));
			setDSDevIMEI(cfg.getString(configKey[1]));
			setDSDevNum(cfg.getInt(configKey[2]));
			setSWDevID(cfg.getString(configKey[3]));
			setSWDevIMEI(cfg.getString(configKey[4]));
			setSWDevNum(cfg.getInt(configKey[5]));	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void loadConfig() {
		AppVar.setDSDevID(getDSDevID());
		AppVar.setDSDevIMEI(getDSDevIMEI());
		AppVar.setDSDevNum(getDSDevNum());
		AppVar.setSWDevID(getSWDevID());
		AppVar.setSWDevIMEI(getSWDevIMEI());
		AppVar.setSWDevNum(getSWDevNum());
	}
	
	public String toString() {
		String output = "";
		
		output += configKey[0] + " = " + "\"" + getDSDevID() + "\"" + ";\n";
		output += configKey[1] + " = " + "\"" + getDSDevIMEI() + "\"" + ";\n";
		output += configKey[2] + " = " + getDSDevNum() + ";\n";
		
		output += "\n";
		
		output += configKey[3] + " = " + "\"" + getSWDevID() + "\"" + ";\n";
		output += configKey[4] + " = " + "\"" + getSWDevIMEI() + "\"" + ";\n";
		output += configKey[5] + " = " + getSWDevNum() + ";";
		
		return output;
	}
	
	/** Docking Station Parameters */
	public String getDSDevID() {
		return DSDeviceID;
	}
	
	public void setDSDevID(String deviceID) {
		DSDeviceID = deviceID;
	}
	
	public String getDSDevIMEI() {
		return DSDeviceIMEI;
	}
	
	public void setDSDevIMEI(String imei) {
		DSDeviceIMEI = imei;
	}
	
	public int getDSDevNum() {
		return DSDeviceNum;
	}
	
	public void setDSDevNum(int num) {
		DSDeviceNum = num;
	}

	/** Smartwatch Device Parameters */
	public String getSWDevID() {
		return SWDeviceID;
	}
	
	public void setSWDevID(String deviceID) {
		SWDeviceID = deviceID;
	}
	
	public String getSWDevIMEI() {
		return SWDeviceIMEI;
	}
	
	public void setSWDevIMEI(String imei) {
		SWDeviceIMEI = imei;
	}
	
	public int getSWDevNum() {
		return SWDeviceNum;
	}
	
	public void setSWDevNum(int num) {
		SWDeviceNum = num;
	}
}
