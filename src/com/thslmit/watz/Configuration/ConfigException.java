package com.thslmit.watz.Configuration;

public class ConfigException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConfigException(Throwable t) {
		super(t);
	}

	public ConfigException(String msg) {
		super(msg);
	}
}
