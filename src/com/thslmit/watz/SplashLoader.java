package com.thslmit.watz;

import java.util.logging.Level;

import javafx.application.Platform;
import javafx.concurrent.Task;

import com.thslmit.watz.Configuration.Config;
import com.thslmit.watz.Configuration.ConfigFile;
import com.thslmit.watz.Configuration.ConfigParams;
import com.thslmit.watz.Utilities.UtDebug;
import com.thslmit.watz.Utilities.UtFile;

public class SplashLoader extends Task {

	protected Object call() throws Exception {
		if (!UtFile.fileExist("watz_config.cfg")) {
			UtDebug.logOut(AppVar.WATZ_CONFIG + " does not exist. Setup as new device enabled.", Level.ALL);
			
			AppVar.setDevSetup(true);
			ConfigFile.setupConfig();
		}

		ConfigParams cfg = new ConfigParams();
		cfg.parseConfig(new Config(UtFile.getFile(AppVar.WATZ_CONFIG)));
		cfg.loadConfig();
		
		// 4) Test network connection and attempt establish to server.
		// TODO: Use native Linux commands to interface with the network - PENDING...
		
		Platform.runLater(new Runnable() {
			public void run() {
				Main.fxmlChange("WelcomeScreen.fxml");
				System.out.println("run later");
			}
		});
		
		return null;
	}
}