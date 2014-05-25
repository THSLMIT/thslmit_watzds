package com.thslmit.watz.UIController;

import java.net.URL;
import java.util.ResourceBundle;

import com.thslmit.watz.Main;
import com.thslmit.watz.fxml.UtFXML;

import javafx.fxml.Initializable;

public class LinkID_helpController implements Initializable {
	
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}

	public void goBack() {
		Main.screenStage.getScene().setRoot(UtFXML.getParent("LinkID.fxml"));
	}
}
