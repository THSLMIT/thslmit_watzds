package com.thslmit.watz.AppThread;

import com.thslmit.watz.fxml.UtFXML;

import javafx.concurrent.Task;

public class IdleClockThread extends Task {

	protected Object call() throws Exception {
		Thread.sleep(2000);
		UtFXML.changeScene("IdleClock.fxml");
		
		return null;
	}

}
