package com.thslmit.watz.UIController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import com.thslmit.watz.AppVar;
import com.thslmit.watz.Main;
import com.thslmit.watz.fxml.UtFXML;

public class CreateAccountInstController implements Initializable {
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void getHelp() {
		Main.screenStage.getScene().setRoot(UtFXML.getParent("CreateAccountInst_help.fxml"));
	}
	
	public void nextStep() {
		Main.screenStage.getScene().setRoot(UtFXML.getParent("LinkID.fxml"));
	}
}