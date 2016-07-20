package com.nutcracker.app.controller;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.util.TextValidator;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutLoginView;
import com.nutcracker.model.NutUser;
import com.nutcracker.remote.NutcrackerFinderRemote;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;

public class NutLoginController implements INutController {

	private NutLoginView loginView;
	private final NutController mainController;
	private final NutRemoteProxy remoteProxy;
	
	public NutLoginController(NutController mainController, NutRemoteProxy remoteProxy) {
		this.mainController = mainController;
		this.remoteProxy = remoteProxy;
	}
	
	@Override
	public void setView(INutView view) {
		this.loginView = (NutLoginView) view;
	}
	
	public void logIn(String userLogin, String userPassword) {
		if (false == logInValidation(userLogin, userPassword)) {
			return;
		}
		
		setExistingUser(userLogin);
		this.mainController.enterMainWindow();
	}
	
	public void signUp(String userLogin, String userPassword) {
		if (false == signUpValidation(userLogin, userPassword)) {
			return;
		}
		
		if (false == createNewUser(userLogin, userPassword)) {
			return;
		}
		this.mainController.enterMainWindow();
	}
	
	public void onClose() {
		this.mainController.close();
	}
	
	private boolean userExists(String userLogin) {
		NutcrackerGetterRemote nutGetter = this.remoteProxy.getNutGetter();
		return nutGetter.userExist(userLogin);
	}
	
	private boolean validatePasswordComplexity(String password) {
		//TODO: Magic number !!
		return TextValidator.isMultiCase(password) &&
				TextValidator.hasNumber(password) &&
				TextValidator.hasMinimumLength(password, 0);
	}

	private boolean validatePasswordEquality(String password) {
		//TODO: Implementation
		return true;
	}
	
	private boolean createNewUser(String userLogin, String userPassword) {
		NutcrackerSetterRemote nutSetter = this.remoteProxy.getNutSetter();
		NutUser user = new NutUser(userLogin, userPassword);
		int userId = nutSetter.insertUser(user);
		
		if (Integer.MIN_VALUE == userId) {
			return false;
		}
		
		this.mainController.setCurrentUserId(userId);
		return true;
	}
	
	private void setExistingUser(String userLogin) {
		NutcrackerFinderRemote nutFinder = this.remoteProxy.getNutFinder();
		NutUser currentUser = nutFinder.findUserByLogin(userLogin);
		this.mainController.setCurrentUserId(currentUser.getUserId());
	}
	
	private boolean logInValidation(String userLogin, String userPassword) {
		if (TextValidator.isEmpty(userLogin)) {
			loginView.showUserLoginEmptyLabel();
			return false;
		}
		
		if (false == userExists(userLogin)) {
			loginView.showUserNotExistLabel(userLogin);
			return false;
		}
		
		if (false == validatePasswordComplexity(userPassword)) {
			loginView.showInvalidPasswordComplexityLabel();
			return false;
		}
		
		if (false == validatePasswordEquality(userPassword)) {
			loginView.showInvalidPasswordLabel();
			return false;
		}
		
		return true;
	}
	
	private boolean signUpValidation(String userLogin, String userPassword) {
		if (TextValidator.isEmpty(userLogin)) {
			loginView.showUserLoginEmptyLabel();
			return false;
		}
		
		if (userExists(userLogin)) {
			loginView.showUserExistLabel(userLogin);
			return false;
		}
		
		if (false == validatePasswordComplexity(userPassword)) {
			loginView.showInvalidPasswordComplexityLabel();
			return false;
		}
		
		return true;
	}
}
