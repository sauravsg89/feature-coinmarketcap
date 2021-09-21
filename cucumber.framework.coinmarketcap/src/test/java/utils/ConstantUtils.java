package utils;

public class ConstantUtils {
	
	public class StrConstants{
		public static final String API_BASE_URI = "https://pro-api.coinmarketcap.com/";
		public static final String API_KEY = "5121415b-e694-4ba0-bdf0-7ae333b50241";
		public static final String API_SEARCH_MOVIE = "?type=SEARCH_TYPE&s=SEARCH_STRING";
		public static final String API_CRYPTOCURRENCY_MAP = "v1/cryptocurrency/map";
		public static final String API_PRICE_CONVERSION ="v1/tools/price-conversion?amount=1000&id=ID_VALUE&convert=CURRENCY_VALUE";
		public static final String API_CRYPTOCURRENCY_INFO ="v1/cryptocurrency/info?id=1027";
		public static final String API_CRYPTOCURRENCY_INFO_FIRST_10 ="v1/cryptocurrency/info?id=1,2,3,4,5,6,7,8,9,10";
		public static final String API_WEBSITE_TECHNICAL_DOC ="[https://github.com/ethereum/wiki/wiki/White-Paper]";
		public static final String API_WEBSITE_LOGO ="https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png";

		public static final String STR_ID_VALUE = "ID_VALUE";
		public static final String STR_CURRENCY_VALUE = "CURRENCY_VALUE";
		public static final String STR_CURRENCY_BOB = "BOB";

		public static final String STR_SEPARATOR = "&";
		public static final String STR_PROPERTY_WEBDRIVER = "webdriver.chrome.driver";
		public static final String STR_USER_DIRECTORY = "user.dir";
		public static final String STR_CHROME_DRIVER = "/chromedriver";
		public static final String GUI_WEB_URL = "https://coinmarketcap.com/";
		public static final String TAGS_GUI = "@GUI";
		public static final String TAGS_API = "@API";
	}
	
	public class StrMessages{
		public static final String MSG_INSIDE_GIVEN = "----------------------------------INSIDE GIVEN----------------------------------";
		public static final String MSG_INSIDE_WHEN = "----------------------------------INSIDE WHEN----------------------------------";
		public static final String MSG_INSIDE_THEN = "----------------------------------INSIDE THEN----------------------------------";
		public static final String MSG_REQUEST_URL = "Request URL: ";
		public static final String MSG_RESPONSE_STATUS_CODE = "Response Status Code: ";
		public static final String MSG_RESPONSE_BODY = "Response Body is =>  ";
		public static final String MSG_AVAILABLE_RESULTS = "Available Results: ";
		public static final String MSG_VERIFY_FIELDS_COUNT = "Total Number of Fields to Verify: ";
		public static final String MSG_FETCH_FIRST_TEN_CURRENCIES = "Fetching First 10 Currencies via Cryptocurrency Info Call";
		public static final String MSG_SCROLL_AND_CLICK = "----------------------------------Scroll and Click----------------------------------";
		public static final String MSG_PAGE_SCROLL_DOWN = "----------------------------------Page Scroll Down----------------------------------";
		public static final String MSG_ASSERT_EXPECTED_AND_ACTUAL = "Asserting Expected and Actual Row Count";
		public static final String MSG_EXPECTED_AND_ACTUAL_EQUALS_100 = "Expected and Actual Row Count Equals 100";
		public static final String MSG_TOTAL_RECORDS_COUNT = "Total Number of records: ";
		public static final String MSG_CURRENT_RECORD_VALUE = "Value of Current Record: ";
		public static final String MSG_CURRENT_RECORD_VALUE_AFTER_PARSING = "Value of Current Record after Parsing: ";
		public static final String MSG_VALUE_NOT_IN_RANGE = "Value of Record Not In Specified Range: ";
		public static final String MSG_MAX_VALUE = "Max Value: ";
		public static final String MSG_EXPECTED_VALUE = "Expected Value: ";
		public static final String MSG_ACTUAL_VALUE = "Actual Value: ";
		public static final String MSG_RESPONSE_VALUE = "Response Value: ";
		public static final String MSG_CURRENCY_WITH_MINEABLE_TAG = "Currency with Mineable Tags: ";
		public static final String MSG_VERIFY_TECHNICAL_WEBSITE = "-----Verifying Technical Documentation Website-----";
		public static final String MSG_VERIFY_LOGO_WEBSITE = "-----Verifying Logo Website-----";
		public static final String MSG_VERIFY_SYMBOL = "-----Verifying Symbol-----";
		public static final String MSG_VERIFY_DATE = "-----Verifying Date Added-----";
		public static final String MSG_VERIFY_TAGS = "-----Verifying Tags-----";
		public static final String MSG_FILTER_SUCCESS = "The records displayed on page are CORRECT as per the filter applied";
		public static final String MSG_FILTER_FAILURE = "The records displayed on page are INCORRECT as per the filter applied";

	}

}
