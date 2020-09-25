package com.revature.selenium.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private static String base_url = "http://52.205.93.132:8006/eBIRProject/";
	private WebDriver webdriver;
	
	private WebElement header;
	private WebElement usernameInput;
	private WebElement passInput;
	private WebElement loginBtn;
	private WebElement regBtn;
	
	public LoginPage(WebDriver wd) {
		this.webdriver = wd;
		this.navTo();
	}
	
	// second constructor in case we do need to set env var
	public LoginPage(WebDriver wd, String base_url) {
		this.webdriver = wd;
		LoginPage.base_url = base_url;
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

	public void setPassword(String password) {
		this.passInput.clear();
		this.passInput.sendKeys("password");
	}

	public void submit() {
		this.loginBtn.click();
	}
	
	// just nav to base since it redirects to login page anyways
	public void navTo() {
		webdriver.get(base_url);
	}

}