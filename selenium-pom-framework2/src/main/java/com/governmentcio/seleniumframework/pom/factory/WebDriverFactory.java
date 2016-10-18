/**
 * 
 */
package com.governmentcio.seleniumframework.pom.factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This class creates a WebDriver implementation such as {@link FirefoxDriver}
 * or a {@link RemoteWebDriver} instance depending on the presence of a value
 * for the environment variable SELENIUM_GRID_URL. If there is a value for
 * SELENIUM_GRID_URL, it will be used in constructing a WebDriver instance. An
 * example might be
 * SELENIUM_GRID_URL=http://seleniumhub.devgovcio.com:4444/wd/hub. As the name
 * implies, this would be the instance serving as the hub in a Selenium grid
 * deployment which would then proxy commands to the correct node on the
 * Selenium grid. If there is no value for SELENIUM_GRID_URL then the default
 * FirefoxDriver will be instantiated and all tests will be executed locally.
 * <p>
 * 
 * @author William Drew
 * @version 2.0
 * @since 1.0
 * @see WebDriver
 */
public final class WebDriverFactory {

  /**
   * WebDriver preference value for "blank".
   */
  private static final String ABOUT_BLANK_PREFERENCE = "about:blank";

  /**
   * Single instance of the {@link WebDriverFactory} class which is instantiated
   * at first instantiation of the class.
   */
  private static final WebDriverFactory WEB_DRIVER_FACTORY =
      new WebDriverFactory();

  /**
   * 
   */
  private WebDriverFactory() {
    // do not instantiate
  }

  /**
   * 
   * @return Single instance of the {@link WebDriverFactory}
   */
  public static WebDriverFactory getInstance() {
    return WEB_DRIVER_FACTORY;
  }

  /**
   * Retrieves the {@link WebDriver} to be used to interact with the target web
   * site. There are two environment variables used to determine the type of
   * WebDriver (for example Firefox, Chrome, IE, etc.) and whether it's going to
   * execute locally or utilize a Selenium Grid installation.
   * 
   * @return Either a {@link RemoteWebDriver} or WebDriver implementation
   *         specified by the value of the environment variable
   *         "WEBDRIVER_TYPE".
   * @throws MalformedURLException
   *           If the environment variable SELENIUM_GRID_URL has been set to a
   *           malformed URL.
   */
  public synchronized WebDriver getDriver() throws MalformedURLException {

    WebDriver webDriver = null;

    /**
     * get the driver type requested by the WEBDRIVER_TYPE env variable or use
     * the default
     * 
     */
    String webDriverType = System.getenv("WEBDRIVER_TYPE");

    DesiredCapabilities desiredCaps = getDesiredCapabilities(webDriverType);

    /**
     * Check if user is providing a URL to a Selenium Grid instance
     */
    String seleniumGridURL = System.getenv("SELENIUM_GRID_URL");

    /**
     * If the SELENIUM_GRID_URL environment variable present then use the
     * DesiredCapabilities and create a RemoteWebDriver.
     */
    if ((null != seleniumGridURL) && (!("".equals(seleniumGridURL)))) {
      webDriver = new RemoteWebDriver(new URL(seleniumGridURL), desiredCaps);
    } else {
      /**
       * Executing locally so we're creating the appropriate WebDriver type
       */
      if ((null != webDriverType) && (!("".equals(webDriverType)))) {
        if (webDriverType.equals(BrowserType.CHROME)) {

          /** TODO:
           * Put these into chrome.properties file.
           */
          String pathToDriver = "/tmp/chrome_install/chromedriver";
          String pathToBinary = "/opt/google/chrome/google-chrome";

          System.setProperty("webdriver.chrome.driver", pathToDriver);

          ChromeOptions options = new ChromeOptions();
          options.setBinary(pathToBinary);
          
          webDriver = new ChromeDriver(options);

        } else if (webDriverType.equals(BrowserType.EDGE)) {
          webDriver = new EdgeDriver(DesiredCapabilities.edge());
        } else if (webDriverType.equals(BrowserType.FIREFOX)) {
          webDriver = new FirefoxDriver(getStandardFirefoxProfile());
        } else if (webDriverType.equals(BrowserType.IE)) {
          webDriver = new InternetExplorerDriver(
              DesiredCapabilities.internetExplorer());
        }
      } else {
        /**
         * webDriverType is null means that WEBDRIVER_TYPE was not specified so
         * we're going to fall back and use Firefox and a standard profile as
         * the default.
         */
        webDriver = new FirefoxDriver(getStandardFirefoxProfile());
      }
    }

    return webDriver;

  }

  /**
   * Get the DesiredCapabilities for the local or remote web driver requested
   * via the environment variable WEBDRIVER_TYPE. If the string is empty or no
   * match is found then Firefox is used as the default if not specified
   * 
   * @param webDriverTypeSpecified
   *          Name of the requested web driver name specified in the environment
   *          variable WEBDRIVER_TYPE.
   * @return DesiredCapabilities as specified by the webDriver type.
   */
  private DesiredCapabilities getDesiredCapabilities(
      final String webDriverTypeSpecified) {

    DesiredCapabilities desiredCaps = DesiredCapabilities.firefox();

    if ((null != webDriverTypeSpecified)
        && (!("".equals(webDriverTypeSpecified)))) {

      if (webDriverTypeSpecified.equals(BrowserType.CHROME)) {
        desiredCaps = DesiredCapabilities.chrome();
      } else if (webDriverTypeSpecified.equals(BrowserType.EDGE)) {
        desiredCaps = DesiredCapabilities.edge();
      } else if (webDriverTypeSpecified.equals(BrowserType.IE)) {
        desiredCaps = DesiredCapabilities.internetExplorer();
      }
    }

    return desiredCaps;
  }

  /**
   * Creates a default {@link FirefoxProfile} to pass to the {@link WebDriver} .
   * 
   * @return {@link FirefoxProfile}
   */
  private FirefoxProfile getStandardFirefoxProfile() {
    FirefoxProfile prof = new FirefoxProfile();
    prof.setPreference("browser.startup.homepage", ABOUT_BLANK_PREFERENCE);
    prof.setPreference("startup.homepate_welcome_url", ABOUT_BLANK_PREFERENCE);
    prof.setPreference("startup.homepate_welcome_url.additional",
        ABOUT_BLANK_PREFERENCE);
    return prof;
  }

  /**
   * Closes and quits the {@link WebDriver}.
   * 
   * @param webDriver
   *          Valid WebDriver reference.
   */
  public static synchronized void closeAndQuit(final WebDriver webDriver) {
    if (null != webDriver) {
      webDriver.close();
      webDriver.quit();
    }
  }

}
