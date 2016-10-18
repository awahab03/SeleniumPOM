package com.governmentcio.iae.fh.tests;

import static org.junit.Assert.assertFalse;
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
 * $Id: This class stores all Tests related to testing available FH
 * functionalities in public view state.
 * 
 * @author awahab@governmentcio.com
 * @version 1
 * @since 1
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HierarchyPublicViewTests {

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
	 * This test verifies name search in public view state of hierarchy.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public final void test1PublicViewNameSearch()
			throws InterruptedException, IOException {
		loginPage.clickSignIn();
		loginPage.enterUserName(loginPage.getConfig().getProperty("email2"));
		loginPage.enterPassword(loginPage.getConfig().getProperty("fhPswd2"));
		loginPage.clickNextButton();
		String code = gmailCode.getCode(webDriverResource.getDriver());
		loginPage.enterOTPcode(code);
		loginPage.ClickSignInButton();
		fhHomePage.waitForSearchPage();
		fhHomePage.enterSearchItem("Water Resources Council");
		fhHomePage.clickSearch();
		fhHomePage.waitForSearchResults();
		String runTimeString = fhHomePage.getNameString();
		assertTrue("Org name in search not found!", runTimeString.toLowerCase()
				.contains("Water Resources Council".toLowerCase()));

	}

	/**
	 * Tests short name search in public view state of FH.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public final void test2HierarchyShortNameSearch()
			throws InterruptedException {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		fhHomePage.enterSearchItem("DoML");
		fhHomePage.clickSearch();
		fhHomePage.waitForSearchResults();
		String runTimeShortName = fhHomePage.getShortName();
		assertTrue("Short name in search not found!",
				runTimeShortName.toLowerCase().contains("DoML".toLowerCase()));
	}

	/**
	 * Test for search by Hierarchy code in Public view
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public final void test3HierarchyCodeSearch() throws InterruptedException {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		fhHomePage.enterSearchItem("WF3MPB");
		fhHomePage.clickSearch();
		fhHomePage.waitForSearchResults();
		String runTimeCode = fhHomePage.getOrgCode();
		assertTrue("Org code in search not found!",
				runTimeCode.toLowerCase().contains("WF3MPB".toLowerCase()));
	}

	/**
	 * Test hierarchy level filter.
	 */
	@Test
	public final void test4HierarchyLevelFilter() {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		fhHomePage.clickFilterButton();
		fhHomePage.clickLevelFilterBox();
		fhHomePage.selectHierLevel();
		fhHomePage.clickApplyFilterOnPublicView();
		fhHomePage.waitForHierLevel();
		assertTrue("Org level in search not found!",
				fhHomePage.getHierLevelInPublicView().equals("Level 3"));
	}

	/**
	 * Test if all hierarchies displayed are active.
	 */
	@Test
	public final void test5AllActiveHierarchies() {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		assertFalse("Some hierarchies may not be in active state!",
				fhHomePage.getColorTag() > 0);
	}

	/**
	 * Test if nothing is editable.
	 */
	@Test
	public final void test6nothingIsEditable() {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		assertFalse("records appear editable when should not!",
				fhHomePage.getActions() > 0);
	}

	/**
	 * Test to verify no FH administrators data exist.
	 */
	@Test
	public final void test7NoAdminsData() {
		fhHomePage.getHomeURL();
		fhHomePage.waitForSearchPage();
		assertFalse("Admin data is present when it should not!",
				fhHomePage.getAdminTab() > 0);
	}
}
