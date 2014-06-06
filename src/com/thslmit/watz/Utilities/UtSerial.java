package com.thslmit.watz.Utilities;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

public class UtSerial {
	/* Class Variables and Constants */
	private static String PORT_NAME = null;
	private static int TIME_OUT = 2000;
	private static int BAUD_RATE = 9600;
	private static boolean commandListen;
	
	/* Program Classes */
	SerialPort cerealPort;
	private static BufferedReader input;
	private static OutputStream output;
	private static UtSerialCommand inputQueue, outputQueue;
	
	/**
	 * Construct a JCereal object and initializes the serial connection.
	 * 
	 * @param port 		(String) 	Name of the specified port
	 * @param time_out 	(int)		Milliseconds to block while waiting for port to open
	 * @param baud_rate (int)		Rate of bits per second for the COM port
	 */
	public UtSerial(String port) {
		PORT_NAME = port;
		System.out.println("init@"+port);
		initalize();
	}
	
	/**
	 * Construct a JCereal object and initializes the serial connection.
	 * 
	 * @param port 		(String) 	Name of the specified port
	 * @param time_out 	(int)		Milliseconds to block while waiting for port to open
	 * @param baud_rate (int)		Rate of bits per second for the COM port
	 */
	public UtSerial(String port, int time_out, int baud_rate) {
		PORT_NAME = port;
		TIME_OUT = time_out;
		BAUD_RATE = baud_rate;
	
		initalize();
	}
	
	/** 
	 * Initialize the serial port and attempt connection.
	 * 
	 * @param  none
	 * @return none
	 */
	private void initalize() {
		// Serial Initialization for Linux Systems
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
		
		// Method Variables
		CommPortIdentifier portID = null;
		Enumeration port_enum = CommPortIdentifier.getPortIdentifiers();
		
		// Find and attempt initialization of serial port
		while (port_enum.hasMoreElements()) {
			CommPortIdentifier curr_PortID = (CommPortIdentifier) port_enum.nextElement();
			
			if (curr_PortID.getName().equals(PORT_NAME)) {
				portID = curr_PortID;
				break;
			}
		}
		
		// Report port availability status
		if (portID == null) {
			System.out.println("[ERR]: " + PORT_NAME + " is not found.");
			System.out.println("[TERMINATING]");
			return;
		} else {
			System.out.println("[INIT_SUCCESS]: " + PORT_NAME + " initialized successfully.");
			
		}
		
		// Attempt Serial Connection
		try {
			// Initialize port and set application name
			cerealPort = (SerialPort) portID.open("JCereal", TIME_OUT);
			
			// Set port parameters
			cerealPort.setSerialPortParams(BAUD_RATE,
										   SerialPort.DATABITS_8, 
										   SerialPort.STOPBITS_1, 
										   SerialPort.PARITY_NONE);
			
			// Open I/O Streams
			input = new BufferedReader(new InputStreamReader(cerealPort.getInputStream()));
			output = cerealPort.getOutputStream();
			
			// Add event listeners
			// cerealPort.addEventListener(this);
			cerealPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		// Establish sereal command queues - use max for now(?)
		inputQueue = new UtSerialCommand(100);
		outputQueue = new UtSerialCommand(100);
		commandListen = false;
	}

	/** 
	 * Closes the serial port and attempts to terminate connection.
	 */
	public synchronized void close() {
		if (cerealPort != null) {
			cerealPort.removeEventListener();
			cerealPort.close();
		}
	}
	
	public static int iter = 0;
	
	/** 
	 * Initialize the serial port and attempt connection.
	 */
	public synchronized void serialEvent(SerialPortEvent serialEvent) {
		if (serialEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String serialInput = input.readLine(); // Get input
				
				if (commandListen)
					inputQueue.add(serialInput);
				
				/* Serial Std Commands */
				if (serialInput.startsWith("echo"))
					System.out.println("[CEREAL ECHO]: " + serialInput.substring(5));
				
				/* Serial Input Commands */
				if (serialInput.equals("CEREAL_COM_INIT") && !commandListen) {
					commandListen = true;
					System.out.println("[SERIAL INIT]");
				} else if (serialInput.equals("CEREAL_COM_CLOSE") && commandListen) {
					System.out.println("[SERIAL TERM]");
					commandListen = false;
				}
				
				/* Serial Output Commands */
			} catch (Exception e) {
				System.err.println(e.toString());	
			}
		}
		
		if (!inputQueue.isEmpty()) {
			String command = inputQueue.remove();
			
			switch (command) {
				case "COMMAND1":
					// Command Body
					break;
				case "COMMAND2":
					// Command Body
					break;
			}
		}
	}
}
