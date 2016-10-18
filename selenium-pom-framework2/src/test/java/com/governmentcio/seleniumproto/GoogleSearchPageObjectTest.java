package com.governmentcio.seleniumproto;

import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

import com.governmentcio.seleniumframework.pom.AbstractPageObject;
import com.governmentcio.seleniumframework.pom.PageObject;
import com.governmentcio.seleniumframework.pom.factory.WebDriverFactory;
import com.governmentcio.seleniumframework.rules.TestCaseRecorder;
import com.governmentcio.seleniumframework.rules.TestReporter;
import com.governmentcio.seleniumframework.rules.WebDriverResource;

/**
 * Tests using Google search functionality.
 * <p>
 * 
 * @author William Drew (wdrew@governmentcio.com)
 * @version 1.0
 * @since 1.0
 * @see PageObject
 * @see AbstractPageObject
 */
public class GoogleSearchPageObjectTest {

  /**
   * Instantiates, configures and cleanups the {@link WebDriver} using the
   * {@link WebDriverFactory}.
   * 
   * @see WebDriver
   * @see WebDriverFactory
   */
  @ClassRule
  public static WebDriverResource webDriverResource = new WebDriverResource();

  /**
   * Reports on the outcome of the overall test recording all test cases as
   * captured in the {@link TestCaseRecorder}.
   * 
   * @see TestCaseRecorder
   */
  @ClassRule
  public static TestReporter testReporter =
      new TestReporter("Google Search Test", "Google search page testing");

  /**
   * Name of each test method provided by the {@link TestName} rule passed into
   * the constructor.
   */
  @Rule
  public TestName name = new TestName();

  /**
   * {@link TestCaseRecorder} for this unit test which records the progress of
   * each test case and the unit test overall.
   */
  @Rule
  public TestCaseRecorder tr =
      new TestCaseRecorder(testReporter, webDriverResource.getDriver());

  /**
   * Tests a basic search of the string "Selenium" via the Google web site
   * Navigates through the Selenium web site including the download and Wiki
   * pages.
   * 
   * @throws InterruptedException
   *           Thrown if this thread is unexpectedly interrupted before
   *           returning from a wait period.
   */
  @Test
  public final void testGooglePageBasicSearch() throws InterruptedException {

    GoogleHomePageObject gpo =
        new GoogleHomePageObject(webDriverResource.getDriver());

    assertNotNull(gpo);

    gpo.goHome();

    // Search using the text "Selenium"
    GoogleSearchResultsSeleniumPageObject spo = gpo.search("Selenium");

    assertNotNull(spo);

    SeleniumHomePageObject seliumHomePageObj = spo.getSeleniumHomePageLink();

    assertNotNull(seliumHomePageObj);

    SeleniumDownloadPageObject seleiumDownLoadPage =
        seliumHomePageObj.clickDownloadLink();

    assertNotNull(seleiumDownLoadPage);

    seleiumDownLoadPage.gotoWikiPage();

    webDriverResource.getDriver().navigate().back();
  }

}
