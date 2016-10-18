package com.governmentcio.seleniumproto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.governmentcio.seleniumframework.pom.AbstractPageObject;

/**
 * 
 * @author William Drew
 * @version 1.0
 * @since 1.0
 * @see AbstractPageObject
 */
public class IndeedHomePageObject extends AbstractPageObject {

  /**
   * Home page for Indeed home site.
   */
  private static final String INDEED_HOME_URL = "http://www.indeed.com";

  /**
   * Job name entry box.
   */
  @FindBy(id = "what")
  private WebElement jobName;

  /**
   * Job location entry box.
   */
  @FindBy(id = "where")
  private WebElement jobLocation;

  /**
   * Button when pressed starts the search function.
   */
  @FindBy(id = "fj")
  private WebElement searchButton;

  /**
   * {@link WebDriver} instance that will be initialized further by
   * {@link AbstractPageObject} constructor. The driver is then navigated to the
   * home page represented by {@link #getHomeURL()}.
   * 
   * @param driver
   *          {@link WebDriver} which will be initialized by the
   *          {@link AbstractPageObject} super class.
   */
  public IndeedHomePageObject(final WebDriver driver) {
	super(driver);
  }

  /**
   * Clears the {@link #jobName} {@link WebElement} entry field and then enters
   * the supplied job name value.
   * 
   * @param name
   *          String value of job to enter in the jobName field
   */
  public final void enterJobEntry(final String name) {
	jobName.clear();
	jobName.sendKeys(name);
  }

  /**
   * Clears the {@link #jobLocation} {@link WebElement} entry field and then
   * enters the supplied job location value.
   * 
   * @param location
   *          String value of job to enter in the jobLocation field
   */
  public final void enterJobLocation(final String location) {
	jobLocation.clear();
	jobLocation.sendKeys(location);
  }

  /**
   * Clicks on the searchButton {@link WebElement}.
   */
  public final void search() {
	searchButton.click();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.governmentcio.seleniumframework.pom.PageObject#getHomeURL()
   */
  @Override
  public final String getHomeURL() {
	return INDEED_HOME_URL;
  }
}
