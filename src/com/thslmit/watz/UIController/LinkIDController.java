package com.thslmit.watz.UIController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LinkIDController implements Initializable {
	
	@FXML static Label deviceID;
	
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void loadOnStart() {
		System.out.println("LOAD");
	}
}