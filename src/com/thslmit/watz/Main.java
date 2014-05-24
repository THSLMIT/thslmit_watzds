package com.thslmit.watz;

import java.util.logging.Level;

import javax.swing.text.html.HTMLEditorKit.LinkController;

import javafx.application.Application;
import javafx.stage.Stage;

import com.thslmit.watz.Utilities.UtDebug;
import com.thslmit.watz.fxml.UtFXML;

public class Main extends Application {
	
	public static Stage screenStage;
	
	public static void main(String[] args) {
		init(args);
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
			screenStage = stage;
			
			// Initiate SplashScreen
			screenStage.setScene(UtFXML.get("SplashScreen.fxml"));
			stage.setFullScreen(true);
			screenStage.show();
			
			Thread th = new Thread(new SplashLoader());
			th.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
