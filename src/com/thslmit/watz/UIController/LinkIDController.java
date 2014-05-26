package com.thslmit.watz.UIController;

import java.net.URL;
import java.util.ResourceBundle;

import com.thslmit.watz.AppVar;
import com.thslmit.watz.Main;
import com.thslmit.watz.Account.AccountUtilities;
import com.thslmit.watz.fxml.UtFXML;

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
	
	public void getHelp() {
		Main.screenStage.getScene().setRoot(UtFXML.getParent("LinkID_help.fxml"));
	}
	
	public void nextStep() {
		Main.screenStage.getScene().setRoot(UtFXML.getParent("CheckLinkID.fxml"));
		String linkID = AppVar.getDSDevID().substring(0, 8);
	}
}