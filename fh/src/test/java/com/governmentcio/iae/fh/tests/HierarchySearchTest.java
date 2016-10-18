package com.governmentcio.iae.fh.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import com.governmentcio.iae.fh.pageobjects.GmailGetSecurityCode;
import com.governmentcio.iae.fh.pageobjects.HierarchyPageObjects;
import com.governmentcio.iae.fh.pageobjects.LoginPageObjects;
import com.governmentcio.seleniumframework.pom.factory.WebDriverFactory;
import com.governmentcio.seleniumframework.rules.TestCaseRecorder;
import com.governmentcio.seleniumframework.rules.TestReporter;
import com.governmentcio.seleniumframework.rules.WebDriverResource;

/**
 * $Id: This class stores all Tests related to testing FH search functionality.
 * 
 * @author awahab@governmentcio.com
 * @version 1
 * @since 1
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HierarchySearchTest {

	/**
	 * Creates Object for GmailGetSecurityCode class
	 */
	private GmailGetSecurityCode gmailCode;

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
	public static TestReporter testReporter = new TestReporter(
			"Federal Hierarchy Login Test", "Login & OTP page Testing");

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
	 * Created object for LoginPageObjects class
	 */
	private LoginPageObjects loginPage =
			new LoginPageObjects(webDriverResource.getDriver());

	private HierarchyPageObjects fhHomePage =
			new HierarchyPageObjects(webDriverResource.getDriver());

	@Before
	public final void setUp() throws IOException {
		webDriverResource.getDriver().get(loginPage.getHomeURL());
		gmailCode = new GmailGetSecurityCode(webDriverResource.getDriver());
		webDriverResource.getDriver().get(fhHomePage.getHomeURL());

	}

	/**
	 * This test validates expected results that is returned by hierarchy name
	 * search
	 * 
	 * @throws InterruptedException
	 * @throws IOException 
	 * 
	 */
	@Test
	public final void test1HierarchyNameSearch() throws InterruptedException, IOException {
		loginPage.clickSignIn();
		loginPage.enterUserName(loginPage.getConfig().getProperty("email")); // from property file
		loginPage.enterPassword(loginPage.getConfig().getProperty("fhPswd"));
		loginPage.clickNextButton();
		String code = gmailCode.getCode(webDriverResource.getDriver());
		loginPage.enterOTPcode(code);
		loginPage.ClickSignInButton();
		fhHomePage.waitForSearchPage();
		fhHomePage.enterSearchItem("Water Resources Council");
		fhHomePage.clickSearch();
		fhHomePage.clickApplyFilter();
		fhHomePage.waitForSearchResults();
		String runTimeString = fhHomePage.getNameString();
		assertTrue("Org name in search not found!", runTimeString.toLowerCase()
				.contains("Water Resources Council".toLowerCase()));

	}

	/**
	 * Test for short name search
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public final void test2HierarchyShortNameSearch()
			throws InterruptedException {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		fhHomePage.enterSearchItem("DoML"); // direct input
		fhHomePage.clickSearch();
		fhHomePage.clickApplyFilter();
		fhHomePage.waitForSearchResults();
		String runTimeShortName = fhHomePage.getShortName();
		assertTrue("Short name in search not found!",
				runTimeShortName.toLowerCase().contains("DoML".toLowerCase()));
	}

	/**
	 * Test for search by Hierarchy code
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public final void test3HierarchyCodeSearch() throws InterruptedException {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		fhHomePage.enterSearchItem("WF3MPB");
		fhHomePage.clickSearch();
		fhHomePage.clickApplyFilter();
		fhHomePage.waitForSearchResults();
		String runTimeCode = fhHomePage.getOrgCode();
		assertTrue("Org code in search not found!",
				runTimeCode.toLowerCase().contains("WF3MPB".toLowerCase()));
	}

	/**
	 * Test for Active Hierarchy
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public final void test4FilterActiveHiearchy() throws InterruptedException {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		fhHomePage.selectActiveStatus();
		fhHomePage.clickApplyFilter();
		String runTimeStatus = fhHomePage.getActiveVerifier();
		assertTrue("Active org not found!", runTimeStatus.contains("-"));
	}

	/**
	 * Test for Active Hierarchy
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public final void test5FilterInactiveHiearchy() throws InterruptedException {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		fhHomePage.selectInactiveStatus();
		fhHomePage.clickApplyFilter();
		fhHomePage.waitForRedtag();
		String runTimestatus = fhHomePage.getInactiveVerifier();
		assertTrue("InActive org not found!",
				runTimestatus.contains("fa fa-times-circle-o red-text"));

	}

	/**
	 * Test for filtering of Hierarchy pending end date
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public final void test6FilterPendingEndDate() throws InterruptedException {
		fhHomePage.waitForSearchPage();
		fhHomePage.selectPendingEnDate();
		fhHomePage.clickApplyFilter();
		fhHomePage.refreshPage();
		fhHomePage.waitForSearchResults();
		String runTimeStatus = fhHomePage.getPendingEnDateValidator();
		assertTrue("No Org with pending end date found!",
				runTimeStatus.contains("/"));

	}
}
