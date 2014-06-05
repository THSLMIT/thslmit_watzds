package com.thslmit.watz.fxml;

import com.thslmit.watz.Main;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class UtFXML {

	public static void changeScene(final String fxml) {
		Platform.runLater(new Runnable() {
		    public void run() {
				Main.screenStage.getScene().setRoot(getParent(fxml));
		    }
		});
	}
	
	public static Scene get(String resourceName) {
		try {
			Parent root = FXMLLoader.load(UtFXML.class.getResource(resourceName));
			return new Scene(root);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Parent getParent(String resourceName) {
		try {
			return FXMLLoader.load(UtFXML.class.getResource(resourceName));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
