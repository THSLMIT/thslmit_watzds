package com.thslmit.watz.Utilities;

import java.util.List;
import java.util.Vector;

public class UtNetwork implements Runnable {

	private List netList = new Vector();
	
	public synchronized void addNetworkListener(UtNetworkListener networkListener) {
		netList.add(networkListener);
	}
	
	public void run() {
		
	}

}
