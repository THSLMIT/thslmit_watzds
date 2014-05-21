package com.thslmit.watz.fxml;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class UtFXML {

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
