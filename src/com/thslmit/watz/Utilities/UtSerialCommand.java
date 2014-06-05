package com.thslmit.watz.Utilities;

public class UtSerialCommand {
	/* Class Variables and Constants */
	private String[] cmd;
	private int front;
	private int rear;
	private int cmdCount;
	
	/**
	 * Construct a SerialCommand Queue through specified maximum value for the number of
	 * maximum commands it can handle.
	 * 
	 * @param max (int)			The maximum number of command it can handle
	 */
	public UtSerialCommand(int max) {
		cmd = new String[max];
		
		front = 0;
		rear = -1;
		cmdCount = 0;
	}
	
	/**
	 * Inserts a command at the rear of the queue.
	 * Returns false if the queue is already full, otherwise true.
	 * 
	 * @param port (String) 	The specified command
	 * @return True if data added successfully, false if queue is full.
	 */
	public boolean add(String command) {
		
		if (isFull()) return false;
		
		rear = (rear + 1) % cmd.length;
		cmd[rear] = command;
		cmdCount++;
		
		return true;
	}

	/**
	 * Removes a command at the front of the queue.
	 * Returns the reference to the removed command.
	 * Returns null if the queue is already empty.
	 * 
	 * @return Reference to removed object. NULL if queue is empty.
	 */
	public String remove() {
		if (isEmpty()) return null;
		
		String rm_cmd = cmd[front];
		cmd[front] = null;
		front = (front + 1) % cmd.length;
		
		return rm_cmd;
	}
	
	/**
	 * Returns a reference of the front of the queue without removal of the command.
	 * Returns NULL if queue is empty.
	 * 
	 * @return Reference to first object in queue. NULL if queue is empty.
	 */
	public String peek() {
		if (isEmpty()) return null;
		return cmd[front];
	}
	
	/** 
	 * Checks to see if the specified queue is empty or not through boolean expression.
	 * 
	 * @return True if queue is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return (cmdCount == 0);
	}

	/** 
	 * Checks to see if the specified queue is full or not through boolean expression.
	 * 
	 * @return True if queue is full, otherwise false.
	 */
	public boolean isFull() {
		return (cmdCount == cmd.length);
	}
}
