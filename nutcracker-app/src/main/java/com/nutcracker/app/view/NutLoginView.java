package com.nutcracker.app.view;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutLoginController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public final class NutLoginView implements INutView {

	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label loginErrorLabel;
	@FXML
	private Label passwordErrorLabel;
	
	private NutLoginController loginController;
	
	public void showUserNotExistLabel(String userLogin) {
		this.loginErrorLabel.setText(String.format("User %s does not exist", userLogin));
		this.loginErrorLabel.setVisible(true);
	}
	
	public void showUserExistLabel(String userLogin) {
		this.loginErrorLabel.setText(String.format("User already %s exist", userLogin));
		this.loginErrorLabel.setVisible(true);
	}
	
	public void showInvalidPasswordComplexityLabel() {
		this.passwordErrorLabel.setText("Password is too weak");
		this.passwordErrorLabel.setVisible(true);
	}
	
	public void showInvalidPasswordLabel() {
		this.passwordErrorLabel.setText("Incorrect password");
		this.passwordErrorLabel.setVisible(true);
	}
	
	@Override
	public void setController(INutController controller) {
		this.loginController = (NutLoginController) controller;
		this.loginController.setView(this);
	}
	
	private void addCleanListener(TextField textField, Label label) {
		textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					textField.clear();
					label.setVisible(false);
				}
			}
		});
	}
	
	@FXML
	private void initialize() {
		addCleanListener(this.loginField, this.loginErrorLabel);
		addCleanListener(this.passwordField, this.passwordErrorLabel);
	}
	
	@FXML
	private void handleSignUpButtonReleased() {
		this.loginController.signUp(
				this.loginField.getText(), 
				this.passwordField.getText());
	}
	
	@FXML
	private void handleEnterButtonReleased() {
		this.loginController.logIn(
				this.loginField.getText(), 
				this.passwordField.getText());
	}
	
	@FXML
	private void handleCloseButtonReleased() {
		this.loginController.onClose();
	}
}
