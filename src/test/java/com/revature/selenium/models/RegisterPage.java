package com.revature.selenium.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	private static String base_url = "http://52.205.93.132:8006/eBIRProject/";
	private WebDriver webdriver;
	
	private WebElement header;
	private WebElement usernameInput;
	private WebElement passInput;
	private WebElement cPassInput;
	private WebElement fNameInput;
	private WebElement lNameInput;
	private WebElement emailInput;
	private WebElement regBtn;
	private WebElement toLoginBtn;
	
	public RegisterPage(WebDriver wd) {
		this.webdriver = wd;
		this.navTo();
	}
	
	// second constructor in case we do need to set env var
	public RegisterPage(WebDriver wd, String base_url) {
		this.webdriver = wd;
		RegisterPage.base_url = base_url;
		this.navTo();
	}
	
	public void setUsername(String username) {
		this.usernameInput.sendKeys(username);
	}
	
	public String getUsername() {
		return this.usernameInput.getAttribute("value");
	}

	public String getHeader() {
		return header.getText();
	}

	public String getPassword() {
		return passInput.getAttribute("value");
	}
	
	public String getConfirmPassword() {
		return cPassInput.getAttribute("value");
	}
	
	public String fNameInput() {
		return fNameInput.getAttribute("value");
	}
	
	public String lNameInput() {
		return lNameInput.getAttribute("value");
	}

	public String emailInput() {
		return emailInput.getAttribute("value");
	}
	
	public void setPassword(String password) {
		this.passInput.clear();
		this.passInput.sendKeys("password");
	}
	

	public void submit() {
		this.regBtn.click();
	}
	
	public void toLogin() {
		this.toLoginBtn.click();
	}
	
	// just nav to base since it redirects to login page anyways
	public void navTo() {
		webdriver.get(base_url);
	}

}
