package com.thslmit.watz.UIController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import com.thslmit.watz.AppVar;

public class LinkIDController implements Initializable {
	
	@FXML Label deviceID;
	
	public void initialize(URL location, ResourceBundle resources) {
		
		 
		String devID = AppVar.getDSDevID().substring(0, 8);
	}
}
