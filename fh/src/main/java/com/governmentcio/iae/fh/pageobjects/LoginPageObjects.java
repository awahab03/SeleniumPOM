
package com.governmentcio.iae.fh.pageobjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.governmentcio.seleniumframework.pom.AbstractPageObject;

/**
 * This class contains objects and methods for login page.
 * 
 * @author awahab@governmentcio.com
 * @version 1
 * @since 1
 * 
 */
public class LoginPageObjects extends AbstractPageObject {

	/**
	 * holds a wait time value of 20 seconds
	 */
	private final int waitTime = 20;

	/**
	 * Properties file is used to store static constant value used through out the
	 * application.
	 */
	private static Properties config = new Properties();

	/**
	 * xpath for Sign in button
	 */
	@FindBy(xpath = "//*[@id='iae-header']/header/div[1]/div/button")
	private WebElement signIn;

	/**
	 * xpath for user name field
	 */
	@FindBy(xpath = "//*[@id='auth_email']")
	private WebElement userName;

	/**
	 * xpath for password field
	 */
	@FindBy(xpath = "//*[@id='auth_password']")
	private WebElement passWd;

	/**
	 * xpath for next button
	 */
	@FindBy(xpath = "//*[@id='auth_submit']")
	private WebElement nextBtn;

	/**
	 * xpath for OTP edit box
	 */
	@FindBy(xpath = "//*[@id='pin-input']")
	private WebElement otpEditbox;

	/**
	 * xpath for submit button for OTP
	 */
	@FindBy(xpath = "//*[@id='iae-header']/header/div[1]/div/div/div/div[2]/div/div[2]/div/div/button")
	private WebElement otpSubmit;

	/**
	 * url for Federal Hierarchy Home page
	 */
	private static final String FH_Login_PAGE_URL =
			"https://minc.fh.micropaas.io/";

	/**
	 * 
	 * @param driver
	 *          Constructor. Creates Attachment page object. Accepts WebDriver
	 *          argument.
	 */
	public LoginPageObjects(WebDriver driver) {
		super(driver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.governmentcio.seleniumframework.pom.PageObject#getHomeURL()
	 */
	@Override
	public final String getHomeURL() {
		return FH_Login_PAGE_URL;
	}

	public final Properties getConfig() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/data/GSAIAE.properties");
		config.load(fis);                       //src\data\GSAIAE.properties
		return config;
	}

	/**
	 * Clicks on Sing in link
	 */
	public final void clickSignIn() {
		signIn = waitUntil(signIn, waitTime);
		if (null != signIn) {
			signIn.click();
		}
	}

	/**
	 * Enters username into username field
	 * 
	 * @param usrName
	 */
	public final void enterUserName(String usrName) {
		userName = waitUntil(userName, waitTime);
		if (null != userName) {
			userName.clear();
			userName.sendKeys(usrName);
		}
	}

	/**
	 * enters password into password field
	 * 
	 * @param paswd
	 */
	public final void enterPassword(String paswd) {
		passWd = waitUntil(passWd, waitTime);
		if (null != passWd) {
			passWd.clear();
			passWd.sendKeys(paswd);
		}
	}

	/**
	 * clicks next button
	 */
	public final void clickNextButton() {
		nextBtn = waitUntil(nextBtn, waitTime);
		if (null != nextBtn) {
			nextBtn.click();
		}
	}

	/**
	 * Enters OTP code into OTP edit box
	 * 
	 * @param code
	 */
	public final void enterOTPcode(final String code) {
		otpEditbox.sendKeys(code);
	}

	/**
	 * clicks sign in button
	 */
	public final void ClickSignInButton() {
		otpSubmit.click();
	}

}
