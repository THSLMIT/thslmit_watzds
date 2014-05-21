package com.thslmit.watz.UIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import com.thslmit.watz.Main;
import com.thslmit.watz.fxml.UtFXML;

public class WelcomeScreenController implements Initializable {
	
	@FXML private AnchorPane pane;
	@FXML Button startButton;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void pressStart() throws IOException {
		Main.screenStage.getScene().setRoot(UtFXML.getParent("CreateAccountInst.fxml"));
	}
}
