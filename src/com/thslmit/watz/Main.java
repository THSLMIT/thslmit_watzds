package com.thslmit.watz;

import java.util.logging.Level;

import javafx.application.Application;
import javafx.stage.Stage;

import com.thslmit.watz.AppThread.IdleClockThread;
import com.thslmit.watz.Utilities.UtDebug;
import com.thslmit.watz.Utilities.UtSerial;
import com.thslmit.watz.fxml.UtFXML;

public class Main extends Application {

	public static Stage screenStage;

	public static void main(String[] args) {

		UtSerial cereal = new UtSerial("ttyACM0");
		// init(args);
	}

	public static void init(String[] args) {
		try {
			// Initial Parameter Check
			boolean dev_mode = false, log_mode = false;
			if (args != null) {
				for (String a : args) {
					if (a.equals("DEV_MODE")) dev_mode = true;
					if (a.equals("LOG_ENABLED")) log_mode = true;
				}
			}

			UtDebug debug = new UtDebug(dev_mode, log_mode);
			UtDebug.logOut("WATZ Docking Station Firmware Initiated", Level.ALL);

			// Launch Application
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fxmlChange(String fxml) {
		screenStage.getScene().setRoot(UtFXML.getParent(fxml));
	}

	public void start(Stage stage) {
		try {
			// Initial ScreenStage & Stage Setup
			stage.setFullScreen(true);
			screenStage = stage;
			screenStage.show();
			
			// Initiate SplashScreen
			screenStage.setScene(UtFXML.get("SplashScreen.fxml"));
			stage.setFullScreen(true);
			screenStage.show();
			
			// Initialize IdleClockThread - Skip setup procedure...
			Thread idleClockThread = new Thread(new IdleClockThread());
			idleClockThread.start();
		} catch (Exception e) {	
		}
	}
}
