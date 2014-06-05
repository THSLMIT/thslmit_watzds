package com.thslmit.watz.UIController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class IdleClockController implements Initializable {

	@FXML
	Label time;
	@FXML
	Label date;
	private KeyFrame keyFrame;

	public void initialize(URL location, ResourceBundle resources) {

		keyFrame = new KeyFrame(Duration.seconds(1),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						time.setText(getTime());
						date.setText(getDate());
					}
				});
		final Timeline timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public String getTime() {
		Date now = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm:ss");
		return timeFormatter.format(now);
	}
	
	public String getDate() {
		Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		return dateFormatter.format(now);
	}
}
