package utils;

import java.util.List;
import org.slf4j.Logger;
import junit.framework.Assert;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


public class GUIUtils {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(GUIUtils.class);
	static WaitUtils wait;
	private static String PAGE_URL = ConstantUtils.StrConstants.GUI_WEB_URL;
	private static WebDriver driver;

	@FindBy(xpath = "//div[@display='flex']/descendant::div[contains(@class, 'tu1guj')]")
	private static WebElement ShowRowsDropdown;

	@FindBy(xpath = "//button[text()='100']")
	private static WebElement DropdownValue100;

	@FindBy(xpath = "//tbody/descendant::tr")
	private static List<WebElement> FilteredRows;

	@FindBy(xpath = "//div[contains(@class, 'table-control-area')]/descendant::button[text()='Filters']")
	private static WebElement FiltersOption;

	@FindBy(xpath = "//button[text()='Add Filter']")
	private static WebElement AddFilterOption;

	@FindBy(xpath = "//button[text()='Market Cap']")
	private static WebElement MarketCapFilterOption;

	@FindBy(xpath = "//button[text()='Price']")
	private static WebElement PriceFilterOption;

	@FindBy(xpath = "//button[text()='$1B - $10B']")
	private static WebElement MarketCapFilterValue;

	@FindBy(xpath = "//button[text()='$101 - $1,000']")
	private static WebElement PriceFilterValue;

	@FindBy(xpath = "//button[text()='Apply Filter']")
	private static WebElement ApplyFilterOption;

	@FindBy(xpath = "//button[text()='Show results']")
	private static WebElement ShowResultsOption;

	@FindBy(xpath = "//tbody/descendant::tr/descendant::td[7]/descendant::p/descendant::span[2]")
	private static List<WebElement> AllMarketCapFilteredRows;

	@FindBy(xpath = "//tbody/descendant::tr/descendant::td[4]/descendant::div/descendant::a")
	private static List<WebElement> AllPriceFilteredRows;

	@SuppressWarnings("static-access")
	public GUIUtils(WebDriver driver) {
		this.driver = driver;
		driver.get(this.PAGE_URL);
		PageFactory.initElements(driver, this);
	}

	public static WebDriver initializeChromeBrowser() {
		System.setProperty(ConstantUtils.StrConstants.STR_PROPERTY_WEBDRIVER,
				System.getProperty(ConstantUtils.StrConstants.STR_USER_DIRECTORY)
						+ ConstantUtils.StrConstants.STR_CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WaitUtils(driver);
		return driver;
	}

	public static void selectDropdown() {
		scrollAndClick(ShowRowsDropdown);
		DropdownValue100.click();
		wait.invokeImplicityWait(driver);
	}

	public static void scrollAndClick(WebElement element) {
		LOGGER.info(ConstantUtils.StrMessages.MSG_SCROLL_AND_CLICK);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.invokeImplicityWait(driver);
	}

	public static void pageScrollDown() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_PAGE_SCROLL_DOWN);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 300)", "");
	}
	
	public static void verifyRowCount() {
		int actualRowCount = getRowCount(FilteredRows);
		int expectedRowCount = 100;
		Assert.assertEquals(ConstantUtils.StrMessages.MSG_ASSERT_EXPECTED_AND_ACTUAL, expectedRowCount, actualRowCount);
		LOGGER.info(ConstantUtils.StrMessages.MSG_EXPECTED_AND_ACTUAL_EQUALS_100);
		wait.invokeImplicityWait(driver);
	}

	public static int getRowCount(List<WebElement> element) {
		return element.size();
	}

	public static void openFilterDialog() {
		scrollAndClick(FiltersOption);
		wait.invokeImplicityWait(driver);
		AddFilterOption.click();
		wait.invokeImplicityWait(driver);
		wait.staticWait(2000);
	}

	public static void applyFilters() {
		MarketCapFilterOption.click();
		wait.invokeImplicityWait(driver);
		MarketCapFilterValue.click();
		wait.invokeImplicityWait(driver);
		ApplyFilterOption.click();
		wait.staticWait(2000);
		PriceFilterOption.click();
		wait.invokeImplicityWait(driver);
		PriceFilterValue.click();
		wait.invokeImplicityWait(driver);
		ApplyFilterOption.click();
		wait.staticWait(2000);
		ShowResultsOption.click();
		wait.invokeImplicityWait(driver);
		pageScrollDown();
		wait.staticWait(1000);
		pageScrollDown();
		wait.staticWait(1000);
		pageScrollDown();
		wait.staticWait(1000);
		pageScrollDown();
		wait.staticWait(1000);
	}

	public static void verifyFilteredRecords() {
		verifyMarketCapFilteredRecords(AllMarketCapFilteredRows);
		verifyPriceFilteredRecords(AllPriceFilteredRows);
	}

	public static void verifyMarketCapFilteredRecords(List<WebElement> allRecords) {
		int count = allRecords.size();
		LOGGER.info(ConstantUtils.StrMessages.MSG_TOTAL_RECORDS_COUNT + count);
		boolean isInRange = true;
		for(WebElement currentVal: allRecords) {
			String valueOfRecord = currentVal.getText();
			LOGGER.info(ConstantUtils.StrMessages.MSG_CURRENT_RECORD_VALUE + valueOfRecord);
			valueOfRecord = valueOfRecord.replaceAll("[^a-zA-Z0-9]", "");
			long currentValToBeVerified = Long.parseLong(valueOfRecord);
			LOGGER.info(ConstantUtils.StrMessages.MSG_CURRENT_RECORD_VALUE_AFTER_PARSING + currentValToBeVerified);
			long minValue = 1000000000;
			long maxValue = 10 * minValue;
			LOGGER.info(ConstantUtils.StrMessages.MSG_MAX_VALUE + maxValue);
			if(!(currentValToBeVerified >= minValue && currentValToBeVerified <= (maxValue))) {
				isInRange = false;
				LOGGER.info(ConstantUtils.StrMessages.MSG_VALUE_NOT_IN_RANGE + currentValToBeVerified);
			}
		}
		if(isInRange) {
			LOGGER.info(ConstantUtils.StrMessages.MSG_FILTER_SUCCESS);
		}
		else {
			LOGGER.info(ConstantUtils.StrMessages.MSG_FILTER_FAILURE);
		}
		Assert.assertEquals(true, isInRange);
	}

	public static void verifyPriceFilteredRecords(List<WebElement> allRecords) {
		int count = allRecords.size();
		LOGGER.info(ConstantUtils.StrMessages.MSG_TOTAL_RECORDS_COUNT + count);
		boolean isInRange = true;
		for(WebElement currentVal: allRecords) {
			String valueOfRecord = currentVal.getText();
			LOGGER.info(ConstantUtils.StrMessages.MSG_CURRENT_RECORD_VALUE + valueOfRecord);
			valueOfRecord = valueOfRecord.substring(1);
			double currentValToBeVerified = Double.parseDouble(valueOfRecord);
			LOGGER.info(ConstantUtils.StrMessages.MSG_CURRENT_RECORD_VALUE_AFTER_PARSING + currentValToBeVerified);
			if(!(currentValToBeVerified >= 101 && currentValToBeVerified <= 1000)) {
				isInRange = false;
				LOGGER.info(ConstantUtils.StrMessages.MSG_VALUE_NOT_IN_RANGE + currentValToBeVerified);
			}
		}
		if(isInRange) {
			LOGGER.info(ConstantUtils.StrMessages.MSG_FILTER_SUCCESS);
		}
		else {
			LOGGER.info(ConstantUtils.StrMessages.MSG_FILTER_FAILURE);
		}
		Assert.assertEquals(true, isInRange);
	}

}
