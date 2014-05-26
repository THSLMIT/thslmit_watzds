package com.thslmit.watz.Account;

import com.thslmit.watz.Database.MySQL;
import com.thslmit.watz.Database.MySQLResult;

public class AccountUtilities {
	private static MySQL db;
	
	static {
		db = new MySQL();
		db.connect("sql3.freesqldatabase.com:3306", "sql340983", "sH9%mW8!");
		db.SelectDB("sql340983");
	}
	
	public static boolean deviceIDExist(String deviceID) {
		MySQLResult mysql_res = db.Query("SELECT * FROM useracct WHERE uid = '" + deviceID + "'");
		if (mysql_res.getNumRows() == 1) return true;
		
		return false;
	}
	
	public static boolean linkAccountDevice(String linkID, String deviceID) {
		if (!deviceIDExist(linkID)) return false;
		MySQLResult mysql_res = db.Query("");
		return false;
	}
}
