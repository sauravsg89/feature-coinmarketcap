package stepdefs;

import java.util.Map;

import utils.APIUtils;
import utils.GUIUtils;
import java.util.List;
import org.slf4j.Logger;
import java.util.ArrayList;
import utils.ConstantUtils;
import io.cucumber.java.After;
import org.slf4j.LoggerFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class StepDefs {
	public static final Logger LOGGER = LoggerFactory.getLogger(StepDefs.class);
	static RequestSpecification httpRequest = null;
	static Response response = null;
	WebDriver driver;
	static GUIUtils guiUtils;

	@Before(ConstantUtils.StrConstants.TAGS_GUI)
	public void initializeSettings() {
		driver = GUIUtils.initializeChromeBrowser();
	}

	@Given("^user has access to API$")
	public void user_has_access_to_API() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_GIVEN);
		httpRequest = APIUtils.checkUserAccesstoAPI(ConstantUtils.StrConstants.API_BASE_URI);
	}

	@When("user sends a GET request to cryptocurrency map call")
	public void getRequestCryptoMapCall() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_WHEN);
		response = APIUtils.getResponseBody(httpRequest, ConstantUtils.StrConstants.API_CRYPTOCURRENCY_MAP);
	}

	@Then("^the IDs retreived should be converted to Bolivian Boliviano$")
	public void convertCryptoCall() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_THEN);
		ArrayList<Object> listOfIDs = APIUtils.getListOfIDs(response);
		APIUtils.convertToCurrency(ConstantUtils.StrConstants.API_PRICE_CONVERSION, 
				listOfIDs, 
				ConstantUtils.StrConstants.STR_CURRENCY_BOB);
	}
	
	@When("user sends a GET request to cryptocurrency info call")
	public void getRequestCryptoInfoCall() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_WHEN);
		response = APIUtils.getResponseBody(httpRequest, ConstantUtils.StrConstants.API_CRYPTOCURRENCY_INFO);
	}
	
	@Then("user is able to Retrieve the Ethereum ID {int} technical documentation website")
	public void user_is_able_to_retrieve_the_ethereum_id_technical_documentation_website(Integer int1) {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_THEN);
		APIUtils.fetchTechnicalDocumentationWebsite(response);

	}
	
	@Then("user is able to verify the following Key and Value pairs")
	public void user_is_able_to_verify_the_following_key_and_value_pairs(DataTable keyValuePairs) {
		List<Map<String, String>> rows = keyValuePairs.asMaps();
		LOGGER.info(ConstantUtils.StrMessages.MSG_VERIFY_FIELDS_COUNT + rows.size());
		APIUtils.verifyInfoResponse(response, rows);
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_THEN);
	}
	
	@When("user sends a GET request to cryptocurrency info call for first ten currencies")
	public void getRequestCryptoInfoCallFirst10() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_WHEN);
		LOGGER.info(ConstantUtils.StrMessages.MSG_FETCH_FIRST_TEN_CURRENCIES);
		response = APIUtils.getResponseBody(httpRequest, ConstantUtils.StrConstants.API_CRYPTOCURRENCY_INFO_FIRST_10);
	}
	
	@When("the currencies having mineable tag are printed out")
	public void verifyMineableTag() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_WHEN);
		APIUtils.verifyMineableTagInfo(response);
	}
	

	@Given("user is on the webpage")
	public void user_is_on_the_webpage() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_GIVEN);
		guiUtils = new GUIUtils(driver);
	}

	@When("user selects Show rows dropdown value to 100")
	public void select_show_rows_dropdown() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_WHEN);
		GUIUtils.selectDropdown();
	}

	@Then("Verify that 100 rows are displayed")
	public void verify_row_count() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_THEN);
		GUIUtils.verifyRowCount();
	}

	@When("user clicks on Filter button")
	public void user_clicks_on_filter_button() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_WHEN);
		GUIUtils.openFilterDialog();
	}

	@When("user Filter records by MarketCap between 1B and 10B while Price between 101 and 1000 dollars")
	public void user_filter_records_by_market_cap_between_1b_and_10b_while_price_between_and() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_WHEN);
		GUIUtils.applyFilters();
	}
	@Then("Verify that records displayed on page are correct as per the filter applied")
	public void verify_that_records_displayed_on_page_are_correct_as_per_the_filter_applied() {
		LOGGER.info(ConstantUtils.StrMessages.MSG_INSIDE_THEN);
		GUIUtils.verifyFilteredRecords();
	}
	
	@After(ConstantUtils.StrConstants.TAGS_GUI)
	public void killSettings() {
		driver.quit();
	}
}
