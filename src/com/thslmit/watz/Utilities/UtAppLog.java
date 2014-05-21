package com.thslmit.watz.Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class UtAppLog extends Handler {
	
	private FileOutputStream fileOutputStream;
	private PrintWriter printWriter;
	private File logfile;
	
	public UtAppLog() {
		super();
		
		try {
			logfile = UtFile.generateFile("logfile.txt");
			fileOutputStream = new FileOutputStream(logfile);
			printWriter = new PrintWriter(fileOutputStream);
			setFormatter(new SimpleFormatter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getLogDir() {
		return logfile.getPath();
	}
	
	public void publish(LogRecord record) {
		if (!isLoggable(record)) return;
		printWriter.println(getFormatter().format(record));
	}
	
	public void write(String text) {
		if (text != null) return;
		printWriter.println(text);
	}

	public void flush() {
		printWriter.flush();
	}
	
	public void close() throws SecurityException {
		printWriter.close();
	}
}
