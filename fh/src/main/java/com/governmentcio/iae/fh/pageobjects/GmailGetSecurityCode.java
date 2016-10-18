
package com.governmentcio.iae.fh.pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.governmentcio.seleniumframework.pom.AbstractPageObject;

/**
 * $Id: This class logins to gmail and gets the OTP security code for GSA IAE
 * application.
 *
 * @author kshah@governmentcio.com
 * @version 1.0
 * @since 1.0
 *
 */
public class GmailGetSecurityCode extends AbstractPageObject {
  
  /**
   * Properties file is used to store static constant value used through out the
   * application.
   */
  private static Properties config = new Properties();
  
  /**
   * Xpath of edit box to enter email.
   */
  @FindBy(xpath = ".//input[@id='Email']")
  private WebElement textbxEmail;
  
  /**
   * Xpath of edit to enter password.
   */
  @FindBy(xpath = ".//input[@id='Passwd']")
  private WebElement textbxPassword;
  
  /**
   * Xpath of Next button.
   */
  @FindBy(xpath = ".//input[@id='next']")
  private WebElement btnNext;
  
  /**
   * Xpath of Sign In button.
   */
  @FindBy(xpath = ".//input[@id='signIn']")
  private WebElement btnSignIn;
  
  /**
   * Xpath of Sign Out options.
   */
  @FindBy(xpath = "//a[contains(@href,'SignOutOptions')]")
  private WebElement iconUser;
  
  /**
   * Xpath of Sign Out button.
   */
  @FindBy(xpath = "//a[.='Sign out']")
  private WebElement btnSignout;
  
  /**
   * Xpath Gmail Account chooser.
   * 
   */
  @FindBy(xpath = ".//a[@id='account-chooser-link']")
  private List<WebElement> lnkAccountChooser;
  
  /**
   * Xpath to allow account edits.
   * 
   */
  @FindBy(xpath = ".//a[@id='edit-account-list']")
  private WebElement lnkRemoveAccount;
  
  /**
   * Xpath Gmail Account chooser.
   * 
   */
  @FindBy(xpath = ".//button[@id='choose-account-0']")
  private WebElement lnkSelectAccount;
  
  /**
   * Xpath for Confirm password.
   * 
   */
  @FindBy(xpath = "//input[@id='iae-password-confirm']")
  private WebElement textboxConfirmPassword;
  
  /**
   * Xpath for new password.
   * 
   */
  @FindBy(xpath = ".//input[@id='iae-reset-password-new']")
  private WebElement textboxNewPassword;
  
  /**
   * Xpath of One Time Password link.
   * 
   */
  @FindBy(xpath = "//*[.='OpenAM One Time Password']")
  private WebElement lnkEmail;
  
  /**
   * Xpath of Forgot Password link.
   * 
   */
  @FindBy(xpath = "//a[contains(@data-saferedirecturl,'reset')]")
  private WebElement lnkForgotPassword;
  
  /**
   * . Xpath of Open button.
   * 
   */
  @FindBy(xpath = "//span [@class='y2' and contains(.,'reset your password.')]")
  private WebElement forgotPasswordEmail;
  
  /**
   * . Xpath of Forgot Password Email.
   * 
   */
  @FindBy(xpath = "//span [@class='y2' and contains(.,'Open')]")
  private WebElement searchEmail;
  
  /**
   * . Xpath of Password Reset Submit button.
   * 
   */
  @FindBy(xpath = "/.//div[@id='password-reset']/button")
  private WebElement buttonSubmit;
  
  /**
   * Xpath of Delete button.
   * 
   */
  @FindBy(xpath = ".//*[@data-tooltip='Delete']")
  private WebElement btnDelete;
  
  /**
   * CSS of email body.
   * 
   */
  @FindBy(css = "body")
  private WebElement body;
  
  /**
   *
   * @param driver
   *          Constructor
   * @throws IOException
   *           Throws IOException.
   */
  public GmailGetSecurityCode(final WebDriver driver) throws IOException {
    super(driver);
    FileInputStream fis = new FileInputStream(
        System.getProperty("user.dir") + "/src/data/GSAIAE.properties");
    config.load(fis);
  }
  
  /**
   *
   * @param driver
   *          Accepts WebDriver argument.
   * @return OTP code.
   * @throws InterruptedException
   *           throws Interrupted Exception.
   *
   */
  public final String getCode(final WebDriver driver)
      throws InterruptedException {
    final String oldTab = driver.getWindowHandle();
    
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) {
      // Opens new tab
      body.sendKeys(Keys.CONTROL + "t");
    } else if (os.contains("mac")) {
      // Opens new tab
      body.sendKeys(Keys.COMMAND + "t");
    } else if (os.contains("nux") || os.contains("nix")) {
      // Opens new tab
      body.sendKeys(Keys.CONTROL + "t");
    }
    
    // Stores window handles into Array
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    // Switches to new window
    driver.switchTo().window(tabs.get(0));
    driver.get(config.getProperty("url"));
//    if (lnkAccountChooser.size() > 0) {
//      removeAccount();
//      driver.get(config.getProperty("url"));
//    }
    if (!textbxEmail.isDisplayed()) {
			textbxPassword.sendKeys(config.getProperty("password"));
			btnSignIn.click();
		} else {
			textbxEmail.sendKeys(config.getProperty("username"));
			btnNext.click();
			textbxPassword.sendKeys(config.getProperty("password"));
			btnSignIn.click();
		}
    // Logs in to Gmail
   // gmailLogin(config.getProperty("username"), config.getProperty("password"));
    final int wait20 = 20;
    
    WebDriverWait wait = new WebDriverWait(driver, wait20);
    
    // Waits for OTP email to be visible
    wait.until(ExpectedConditions.elementToBeClickable(searchEmail));
    
    final int subString = 6;
    
    // Stores subject header text into variable
    final String codeEmail = searchEmail.getText();
    
    // Deletes email
    Actions keyboard = new Actions(driver);
    
    keyboard.contextClick(searchEmail).sendKeys(Keys.ARROW_DOWN)
        .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
        .sendKeys(Keys.ENTER).perform();
    
    WebDriverWait waitLogout = new WebDriverWait(driver, wait20);
    
    waitLogout.until(ExpectedConditions.elementToBeClickable(iconUser));
    
    // Signs out of Gmail
    iconUser.click();
    btnSignout.click();
    
    // Close tab
    if (os.contains("win")) {
      // Closes new tab
      body.sendKeys(Keys.CONTROL + "w");
    } else if (os.contains("mac")) {
      // Closes new tab
      body.sendKeys(Keys.COMMAND + "w");
    } else if (os.contains("nux") || os.contains("nix")) {
      // Closes new tab
      body.sendKeys(Keys.CONTROL + "w");
    }
    
    // Handle alert
    
    try {
      wait.until(ExpectedConditions.alertIsPresent());
      Alert alert = driver.switchTo().alert();
      alert.accept();
    } catch (TimeoutException | NoAlertPresentException noAlert) {
    }
    
    // Change focus back to old tab
    try {
      driver.switchTo().window(oldTab);
    } catch (UnhandledAlertException noAlert) {
    }
    
    // Change focus back to orginal content
    try {
      driver.switchTo().defaultContent();
    } catch (UnhandledAlertException noAlert) {
    }
    
    // Trims the subject line to return the OTP password
    return codeEmail.substring(codeEmail.length() - subString);
    
  }
  
  /**
   * Deletes any existing OTP email
   * 
   * @param driver
   *          Accepts WebDriver argument.
   * @throws InterruptedException
   *           throws Interrupted Exception.
   *
   */
  public final void deleteEmail(final WebDriver driver)
      throws InterruptedException {
    final String oldTab = driver.getWindowHandle();
    
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) {
      // Opens new tab
      body.sendKeys(Keys.CONTROL + "t");
    } else if (os.contains("mac")) {
      // Opens new tab
      body.sendKeys(Keys.COMMAND + "t");
    } else if (os.contains("nux") || os.contains("nix")) {
      // Opens new tab
      body.sendKeys(Keys.CONTROL + "t");
    }
    
    // Stores window handles into Array
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    // Switches to new window
    driver.switchTo().window(tabs.get(0));
    driver.get(config.getProperty("url"));
    if (lnkAccountChooser.size() > 0) {
      removeAccount();
      driver.get(config.getProperty("url"));
    }
    // Logs in to Gmail
    gmailLogin(config.getProperty("username"), config.getProperty("password"));
    final int wait20 = 20;
    
    // Deletes OTP email if displayed
    
    try {
      
      Actions keyboard = new Actions(driver);
      
      keyboard.contextClick(searchEmail).sendKeys(Keys.ARROW_DOWN)
          .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
          .sendKeys(Keys.ENTER).perform();
    } catch (NoSuchElementException e) {
    }
    
    WebDriverWait waitLogout = new WebDriverWait(driver, wait20);
    
    waitLogout.until(ExpectedConditions.elementToBeClickable(iconUser));
    
    // Signs out of Gmail
    iconUser.click();
    btnSignout.click();
    
    // Close tab
    if (os.contains("win")) {
      // Closes new tab
      body.sendKeys(Keys.CONTROL + "w");
    } else if (os.contains("mac")) {
      // Closes new tab
      body.sendKeys(Keys.COMMAND + "w");
    } else if (os.contains("nux") || os.contains("nix")) {
      // Closes new tab
      body.sendKeys(Keys.CONTROL + "w");
    }
    
    
    // Handle alert
    try {
      WebDriverWait wait = new WebDriverWait(driver, wait20);
      wait.until(ExpectedConditions.alertIsPresent());
      Alert alert = driver.switchTo().alert();
      alert.accept();
    } catch (TimeoutException | NoAlertPresentException noAlert) {
    }
    
    // Change focus back to old tab
    try {
      driver.switchTo().window(oldTab);
    } catch (UnhandledAlertException noAlert) {
    }
    
    // Change focus back to orginal content
    try {
      driver.switchTo().defaultContent();
    } catch (UnhandledAlertException noAlert) {
    }
    
  }
  
  /**
   *
   * @param driver
   *          Constructor
   * @param newPassword
   *          Constructor
   * 
   * @throws InterruptedException
   *           Throws InterruptedException.
   */
  public final void gmailClickForgotPasswordLink(final WebDriver driver,
      final String newPassword) throws InterruptedException {
    final String oldTab = driver.getWindowHandle();
    
    // Opens new tab
    body.sendKeys(Keys.CONTROL + "t");
    // Stores window handles into Array
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    // Switches to new window
    driver.switchTo().window(tabs.get(0));
    driver.get(config.getProperty("url"));
    if (lnkAccountChooser.isEmpty()) {
      removeAccount();
      driver.get(config.getProperty("url"));
    }
    // Logs in to Gmail
    gmailLogin(config.getProperty("gmailUsername"),
        config.getProperty("gmailPassword"));
    final int wait20 = 20;
    
    WebDriverWait wait = new WebDriverWait(driver, wait20);
    
    // Waits for OTP email to be visible
    wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordEmail));
    
    forgotPasswordEmail.click();
    
    lnkForgotPassword.click();
    
    for (String winHandle : driver.getWindowHandles()) {
      driver.switchTo().window(winHandle);
    }
    
    textboxNewPassword.sendKeys(newPassword);
    textboxConfirmPassword.sendKeys(newPassword);
    
    buttonSubmit.click();
    
    body.sendKeys(Keys.CONTROL + "w");
    
    driver.switchTo().window(tabs.get(0));
    
    driver.navigate().back();
    
    driver.navigate().refresh();
    
    // Deletes email
    Actions keyboard = new Actions(driver);
    
    keyboard.contextClick(forgotPasswordEmail).sendKeys(Keys.ARROW_DOWN)
        .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
        .sendKeys(Keys.ENTER).perform();
    
    WebDriverWait waitLogout = new WebDriverWait(driver, wait20);
    
    waitLogout.until(ExpectedConditions.elementToBeClickable(iconUser));
    
    // Signs out of Gmail
    iconUser.click();
    btnSignout.click();
    
    // Close tab
    body.sendKeys(Keys.CONTROL + "w");
    
    // Handle alert
    
    try {
      Alert alert = driver.switchTo().alert();
      alert.accept();
    } catch (NoAlertPresentException noAlert) {
    }
    
    try {
      Alert alert = driver.switchTo().alert();
      alert.accept();
    } catch (NoAlertPresentException noAlert) {
    }
    
    // Change focus back to old tab
    driver.switchTo().window(oldTab);
    
    driver.switchTo().defaultContent();
    // Trims the subject line to return the OTP password
    
  }
  
  @Override
  public final String getHomeURL() {
    return null;
  }
  
  /**
   *
   * Removes existing gmail account.
   *
   * @throws InterruptedException
   *           throws Interrupted Exception.
   *
   */
  
  public final void removeAccount() throws InterruptedException {
    
    lnkAccountChooser.get(0).click();
    lnkRemoveAccount.click();
    lnkSelectAccount.click();
    lnkRemoveAccount.click();
  }
  
  /**
   *
   * @param username
   *          Accepts username for gmail.
   * @param password
   *          Accepts password for gmail.
   * @throws InterruptedException
   *           throws Interrupted Exception.
   *
   */
  public final void gmailLogin(final String username, final String password)
      throws InterruptedException {
    
    textbxEmail.sendKeys(username);
    btnNext.click();
    textbxPassword.sendKeys(password);
    btnSignIn.click();
  }
  
  
}
