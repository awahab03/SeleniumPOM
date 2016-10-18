package com.governmentcio.iae.fh.pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.governmentcio.seleniumframework.pom.AbstractPageObject;

/**
 * This class stores all required element properties and methods to perform IAE
 * Federal Hierarchy search and filter
 * 
 * @author awahab@governmentcio.com
 * @version 1
 * @since 1
 * 
 */
public class HierarchyPageObjects extends AbstractPageObject {

	/**
	 * holds a wait time value of 20 seconds.
	 */
	private final int wait20 = 20;

	/**
	 * Xpath for USer Icon element on the Hierarchy home page.
	 */
	@FindBy(xpath = "//*[@id='iae-signin']/button/i")
	private WebElement userIcon;

	/**
	 * Xpath for SignOut link.
	 */
	@FindBy(xpath = "//*[@id='iae-signin']/div[2]/button")
	private WebElement signOutLink;

	/**
	 * Xpath for Hierarchy level display.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[1]/div[1]/div/span")
	private WebElement hierLevel;

	/**
	 * Xpath for Federal Hierarchy welcome element.
	 */
	@FindBy(xpath = "//*[@id='iae-signin']")
	private WebElement fhWelcome;

	/**
	 * Xpath for Search ediit box.
	 */
	@FindBy(xpath = "//*[@id='fh-search-field']")
	private WebElement fhSearchBox;

	/**
	 * Xpath for Search Button.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/div[1]/button[1]")
	private WebElement fhSearchBtn;

	/**
	 * Xpath for Search Table.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']")
	private WebElement fhSearchTable;

	/**
	 * Xpath for Filter button.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/div[1]/button[2]")
	private WebElement filterBtn; //

	/**
	 * Xpath for Filter box.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/div[1]/div")
	private WebElement filterBox; // *[@id="search-filter-status"]

	/**
	 * Xpath for Filter status select drop down.
	 */
	@FindBy(xpath = "//*[@id='search-filter-status']")
	private WebElement statusFilterSelect;

	/**
	 * hierarchy level select filter box
	 */
	@FindBy(xpath = "//*[@id='search-filter-hierarchy-level']")
	private WebElement hierLevelFilterSelect;

	/**
	 * Xpath for Hierarchy status select.
	 */
	@FindBy(xpath = "//*[@id='search-filter-hierarchy-level']/option")
	private WebElement hierLevelList;

	/**
	 * Xpath for Hierarchy status select.
	 */
	@FindBy(xpath = "//*[@id='search-filter-status']/option")
	private List<WebElement> statusList;

	/**
	 * Xpath for Hierarchy level from public view search results.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr[1]/td[4]")
	private WebElement hierLevelFromSearch;

	/**
	 * Xpath for Apply filter button.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/div[1]/div/div[3]/button[2]")
	private WebElement applyFilter;

	/**
	 * Xpath for Apply filter button in public view page.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/div[1]/div/div[2]/button[2]")
	private WebElement applyFilter2;

	/**
	 * Xpath for Reset Filter button.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/div[1]/div/div[3]/button[1]")
	private WebElement resetFilter;

	/**
	 * Xpath for hierarchy activeness color tag.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[1]/i")
	private WebElement colorTag;
	/**
	 * Xpath for hierarchy activeness color tag. returns a list
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[1]/i")
	private List<WebElement> activTag;

	/**
	 * Xpath for Reset Filter button.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[6]")
	private WebElement actionsDrop;

	/**
	 * Xpath for Reset Filter button.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[6]/ul/li")
	private List<WebElement> actionsDropList;

	/**
	 * Xpath for Reset Filter button.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/div[2]/div[2]/button[5]")
	private List<WebElement> nextLink;

	/**
	 * Xpath for Search Result size.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td")
	private List<WebElement> resultSize;

	/**
	 * Xpath for Name column of search result.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[1]")
	private List<WebElement> nameSize;

	/**
	 * Xpath for Name column of search result.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[4]/button")
	private List<WebElement> searchAttb; // *[@id="fh-search-results-table"]/tbody/tr/td[4]/button

	/**
	 * Xpath for Code column of search result.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[3]")
	private List<WebElement> codeSearchList;

	/**
	 * Xpath for short Name column of search result.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[2]")
	private List<WebElement> shortNameList;

	/**
	 * Xpath for search result count.
	 */
	@FindBy(xpath = "//*[@id='hierarchy']/div[2]/div/h2/span[1]")
	private WebElement searchCount;

	/**
	 * Xpath for end date table data.
	 */
	@FindBy(xpath = "//*[@id='fh-search-results-table']/tbody/tr/td[5]")
	private List<WebElement> endate;

	/**
	 * Xpath for admin tab
	 */
	@FindBy(xpath = "//*[@id='fh-app-container']/div[1]/div/a[3]")
	private List<WebElement> adminTabList;

	/**
	 * URL for Federal Hierarchy Home page.
	 */
	private static final String GSA_IAE_FH_Homepage_URL =
			"" + "https://minc.fh.micropaas.io";

	/**
	 * 
	 * @param driver
	 *          Constructor. Creates Attachment page object. Accepts WebDriver
	 *          argument.
	 */
	public HierarchyPageObjects(WebDriver driver) {
		super(driver);
	}

	/**
	 * Clicks user icon figure on FH home page.
	 */
	public void clickUserIcon() {
		userIcon = waitUntil(userIcon, wait20);
		if (null != userIcon) {
			userIcon.click();
		}
	}

	/**
	 * Clicks Sign Out Link.
	 * 
	 * @throws NoSuchElementException
	 */
	public void clickSignOutLink() throws NoSuchElementException {
		signOutLink = waitUntil(signOutLink, wait20);
		signOutLink.click();
	}

	/**
	 * Enters an item in to search edit box.
	 * 
	 * @param name
	 */
	public final void enterSearchItem(final String name) {
		fhSearchBox.clear();
		fhSearchBox.sendKeys(name);
	}

	/**
	 * Clicks search button.
	 */
	public final void clickSearch() {
		fhSearchBtn = waitUntil(fhSearchBtn, wait20);
		fhSearchBtn.sendKeys(Keys.ENTER);
	}

	/**
	 * sets a default wait time of 5 seconds.
	 */
	public final void wait5sec() {
		pageWait(5000);
	}

	/**
	 * Refreshes the web page.
	 */
	public final void refreshPage() {
		WebDriver driver = getDriver();
		driver.navigate().refresh();
	}

	/**
	 * wait for search page to be displayed.
	 */
	public final void waitForSearchPage() {
		WebDriver driver = getDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(getDriver(), wait20);
		wait.until(ExpectedConditions.elementToBeClickable(fhSearchBtn));
		wait.until(ExpectedConditions.attributeContains(fhSearchBox, "id",
				"fh-search-field"));
		wait.until(ExpectedConditions.visibilityOfAllElements(nameSize));
		wait.until(ExpectedConditions.visibilityOf(nextLink.get(0)));
	}

	/**
	 * wait for expected hierLevel to be displayed.
	 */
	public final void waitForHierLevel() {
		WebDriverWait wait = new WebDriverWait(getDriver(), wait20);
		wait.until(ExpectedConditions.textToBePresentInElement(hierLevelFromSearch,
				"Level 3"));
	}

	/**
	 * wait for search text to be displayed.
	 */
	public final void waitForSearchResults() {
		WebDriverWait wait = new WebDriverWait(getDriver(), wait20);
		wait.until(ExpectedConditions.invisibilityOfAllElements(nextLink));
	}

	/**
	 * wait for red tag to be displayed in filter for inactive hierarchy.
	 */
	public final void waitForRedtag() {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), wait20);
			wait.until(ExpectedConditions.attributeContains(colorTag, "class",
					"fa fa-times-circle-o red-text"));
		} catch (TimeoutException t) {
			throw t;
		}
	}

	/**
	 * Validating Hierarchy name searched.
	 * 
	 * @return
	 */
	public final String getNameString() {
		String actualNameSearch;
		if (!nameSize.isEmpty()) {
			actualNameSearch = nameSize.get(0).getText();
		} else {
			return null;
		}
		return actualNameSearch;
	}

	/**
	 * This method validates short name in search.
	 * 
	 * @return
	 */
	public final String getShortName() {
		String shortName;
		if (!shortNameList.isEmpty()) {
			shortName = shortNameList.get(0).getText();
		} else {
			shortName = null;
		}
		return shortName;
	}

	/**
	 * Validates code in search.
	 * 
	 * @return
	 */
	public final String getOrgCode() {
		String code;
		if (!codeSearchList.isEmpty()) {
			code = codeSearchList.get(0).getText();
		} else {
			code = null;
		}
		return code;
	}

	/**
	 * Click filter button.
	 */
	public final void clickFilterButton() {
		WebDriver driver = getDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(getDriver(), wait20);
		wait.until(ExpectedConditions.elementToBeClickable(filterBtn));

		filterBtn.click();
	}

	/**
	 * gets the filter drop down field.
	 * 
	 *
	 */
	public final void clickLevelFilterBox() {
		hierLevelFilterSelect.click();
	}

	/**
	 * clicks on status field to select status.
	 */
	public final void clickStatusDrop() {
		statusFilterSelect.click();
	}

	/**
	 * Selects Active from filter.
	 */
	public final void selectActiveStatus() {

		statusList.get(1).click();
	}

	/**
	 * Selects Hierarchy level from filter.
	 */
	public final void selectHierLevel() {
		hierLevelFilterSelect.sendKeys("3");
	}

	/**
	 * Selects Inactive from filter.
	 */
	public final void selectInactiveStatus() {
		statusList.get(3).click();
	}

	/**
	 * Selects Inactive from filter.
	 */
	public final void selectPendingEnDate() {
		statusList.get(2).click();
	}

	/**
	 * Clicks Apply Filter button.
	 */
	public final void clickApplyFilter() {
		applyFilter.click();
	}

	/**
	 * Clicks Apply Filter button.
	 */
	public final void clickApplyFilterOnPublicView() {
		applyFilter2.click();
	}

	/**
	 * Gets verifying element for hierarchy level filter in Public view
	 * 
	 * @return
	 */
	public final String getHierLevelInPublicView() {
		return hierLevelFromSearch.getText();
	}

	/**
	 * returns the size of element for the active status of hierarchies.
	 * 
	 * @return
	 */
	public final int getColorTag() {
		return activTag.size();
	}

	/**
	 * returns the size for actions drop buttons.
	 * 
	 * @return
	 */
	public final int getActions() {
		return actionsDropList.size();
	}

	/**
	 * returns the size for admin tab.
	 * 
	 * @return
	 */
	public final int getAdminTab() {
		return adminTabList.size();
	}

	/**
	 * Verify filtering of Active hierarchy.
	 */
	public final String getActiveVerifier() {
		String activeMark;
		if (!endate.isEmpty()) {
			activeMark = endate.get(0).getText();
		} else {
			activeMark = null;
		}
		return activeMark;
	}

	/**
	 * Verify filtering of Inactive hierarchy.
	 * 
	 * @throws InterruptedException
	 * @return
	 */
	public final String getInactiveVerifier() throws InterruptedException {
		String inactiveValidator;
		if (!endate.isEmpty()) {
			inactiveValidator = colorTag.getAttribute("class");
		} else {
			return null;
		}
		return inactiveValidator;
	}

	/**
	 * Clicks on Actions drop button on the search results.
	 */
	public final void clickActionsDrop() {
		actionsDrop.click();
	}

	/**
	 * validates the presence of Org with pending end date.
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public final String getPendingEnDateValidator() throws InterruptedException {
		String pendingStatus = null;
		if (!endate.isEmpty()) {
			String colorTAG = colorTag.getAttribute("class");
			String nDate = endate.get(0).getText();
			if (!nDate.equals("-") && colorTAG.equals("fa fa-circle green-text")) {
				pendingStatus = endate.get(0).getText();
			} else {
				return null;
			}
		}
		return pendingStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.governmentcio.seleniumframework.pom.PageObject#getHomeURL()
	 */
	@Override
	public String getHomeURL() {
		return GSA_IAE_FH_Homepage_URL;
	}

}
