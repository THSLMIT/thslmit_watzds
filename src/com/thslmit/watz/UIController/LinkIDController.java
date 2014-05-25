package com.thslmit.watz.UIController;

import java.net.URL;
import java.util.ResourceBundle;

import com.thslmit.watz.AppVar;
import com.thslmit.watz.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LinkIDController implements Initializable {
	
	@FXML Label deviceID;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String devID = AppVar.getDSDevID().substring(0, 8);
		deviceID.setText(devID);
	}
}