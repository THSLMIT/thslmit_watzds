package com.thslmit.watz.Utilities;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtSystem {
	public static String getDevName() {
		String hostname = null;
		try {
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		} catch (UnknownHostException ex) {
		    System.out.println("Hostname can not be resolved");
		}
		
		return hostname;
	}
	
	public static String md5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
	        //Create MessageDigest object for MD5
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	         
	        //Update input string in message digest
	        digest.update(input.getBytes(), 0, input.length());
	 
	        //Converts message digest value in base 16 (hex) 
	        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
}
