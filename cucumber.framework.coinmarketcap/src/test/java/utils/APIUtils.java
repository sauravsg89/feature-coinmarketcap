package utils;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import junit.framework.Assert;
import java.util.LinkedHashMap;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.apache.http.HttpHeaders;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class APIUtils {
	public static final Logger LOGGER = LoggerFactory.getLogger(APIUtils.class);
	public static LinkedHashMap<String, LinkedHashMap<String, Object>> allCryptoInfoCallData = null;
	public static LinkedHashMap<String, Object> allCryptoInfoCallURLs = null;

	public static RequestSpecification checkUserAccesstoAPI(String baseURIVal) {
		RestAssured.baseURI = baseURIVal;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header(HttpHeaders.ACCEPT, "application/json");
		httpRequest.header("X-CMC_PRO_API_KEY", ConstantUtils.StrConstants.API_KEY);
		return httpRequest;
	}

	public static Response getResponseBody(RequestSpecification httpRequest, String fetchURL) {
		LOGGER.info(ConstantUtils.StrMessages.MSG_REQUEST_URL + fetchURL);
		Response response = httpRequest.request(Method.GET, fetchURL);
		String responseBody = response.getBody().asString();
		LOGGER.info("Response Body: " + responseBody);
		LOGGER.info(ConstantUtils.StrMessages.MSG_RESPONSE_STATUS_CODE + response.getStatusCode());
		LOGGER.info(ConstantUtils.StrMessages.MSG_RESPONSE_BODY + responseBody);
		Assert.assertEquals(response.getStatusCode(), 200);
		return response;
	}

	public static ArrayList<Object> getListOfIDs(Response response) {
		JsonPath jsonPath = response.jsonPath();
		String responseData = jsonPath.getString("data");
		LOGGER.info(ConstantUtils.StrMessages.MSG_RESPONSE_VALUE + responseData);
		List<LinkedHashMap<String, Object>> listofList = jsonPath.getList("data");
		ArrayList<Object> listOfIDs = new ArrayList();
		for (LinkedHashMap<String, Object> innerList : listofList) {
			if(innerList.get("name").toString().contentEquals("Bitcoin") 
					|| innerList.get("name").toString().contentEquals("Ethereum") 
					|| innerList.get("name").toString().contentEquals("Tether")) {
				LOGGER.info("ID From Response for " +innerList.get("name")
				+" is: " + innerList.get("id"));
				listOfIDs.add(innerList.get("id"));
			}
		}
		return listOfIDs;
	}
	
	public static void convertToCurrency(String requestURL, ArrayList<Object> listOfIDs, String currencyValue) {
		for(Object item:listOfIDs) {
			RequestSpecification httpRequest1 = APIUtils.checkUserAccesstoAPI(ConstantUtils.StrConstants.API_BASE_URI);
			String currentRequestURL = fetchPriceConversionURL(requestURL, (Integer) item, currencyValue);
			getResponseBody(httpRequest1, currentRequestURL);

		}
	}

	public static String fetchPriceConversionURL(String defaultRequestURL, int currentID, String currencyValue) {
		String requestURL = defaultRequestURL
				.replace(ConstantUtils.StrConstants.STR_ID_VALUE, currentID + "")
				.replace(ConstantUtils.StrConstants.STR_CURRENCY_VALUE, currencyValue);
		LOGGER.info("requestURL: " + requestURL);
		return requestURL;
	}

	public static void fetchTechnicalDocumentationWebsite(Response response) {
		JsonPath jsonPath = response.jsonPath();
		String responseData = jsonPath.getString("data");
		LOGGER.info(ConstantUtils.StrMessages.MSG_RESPONSE_VALUE + responseData);
		Map<Object, LinkedHashMap<String, LinkedHashMap<String, Object>>> mapOfMaps = jsonPath.getMap("data");
		allCryptoInfoCallData = mapOfMaps.get("1027");
		LOGGER.info("All Data: " + allCryptoInfoCallData);
		allCryptoInfoCallURLs = allCryptoInfoCallData.get("urls");
		LOGGER.info("All URLs: " + allCryptoInfoCallURLs);
		Object technicalDocWebsite = allCryptoInfoCallURLs.get("technical_doc");
		LOGGER.info("Technical Documentation Website: " + technicalDocWebsite);
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, Object>> getAllCryptoInfoCallData() {
		return allCryptoInfoCallData;
	}
	
	public static LinkedHashMap<String, Object> getAllCryptoInfoCallURLs() {
		return allCryptoInfoCallURLs;
	}

	public static void verifyInfoResponse(Response response, List<Map<String, String>> rows) {
		String expectedValue = null;
		String actualValue = null;

		for(Map<String, String> rowItem: rows) {
			if(rowItem.get("Key").contentEquals("technical_doc")) {
				Object technicalDocWebsite = getAllCryptoInfoCallURLs().get("technical_doc");
				expectedValue = rowItem.get("Value").trim();
				actualValue = technicalDocWebsite.toString().trim();
				LOGGER.info(ConstantUtils.StrMessages.MSG_VERIFY_TECHNICAL_WEBSITE);
				LOGGER.info(ConstantUtils.StrMessages.MSG_EXPECTED_VALUE + expectedValue);
				LOGGER.info(ConstantUtils.StrMessages.MSG_ACTUAL_VALUE + actualValue);
				Assert.assertEquals(ConstantUtils.StrMessages.MSG_VERIFY_TECHNICAL_WEBSITE, expectedValue, actualValue);
			}
			else if(rowItem.get("Key").contentEquals("logo")) {
				Object logoWebsite = getAllCryptoInfoCallData().get("logo");
				expectedValue = rowItem.get("Value").trim();
				actualValue = logoWebsite.toString().trim();
				LOGGER.info(ConstantUtils.StrMessages.MSG_VERIFY_LOGO_WEBSITE);
				LOGGER.info(ConstantUtils.StrMessages.MSG_EXPECTED_VALUE + expectedValue);
				LOGGER.info(ConstantUtils.StrMessages.MSG_ACTUAL_VALUE + actualValue);
				Assert.assertEquals(ConstantUtils.StrMessages.MSG_VERIFY_LOGO_WEBSITE, expectedValue, actualValue);
			}
			else if(rowItem.get("Key").contentEquals("symbol")) {
				Object logoWebsite = getAllCryptoInfoCallData().get("symbol");
				expectedValue = rowItem.get("Value").trim();
				actualValue = logoWebsite.toString().trim();
				LOGGER.info(ConstantUtils.StrMessages.MSG_VERIFY_SYMBOL);
				LOGGER.info(ConstantUtils.StrMessages.MSG_EXPECTED_VALUE + expectedValue);
				LOGGER.info(ConstantUtils.StrMessages.MSG_ACTUAL_VALUE + actualValue);
				Assert.assertEquals(ConstantUtils.StrMessages.MSG_VERIFY_SYMBOL, expectedValue, actualValue);
			}
			else if(rowItem.get("Key").contentEquals("date_added")) {
				Object logoWebsite = getAllCryptoInfoCallData().get("date_added");
				expectedValue = rowItem.get("Value").trim();
				actualValue = logoWebsite.toString().trim();
				LOGGER.info(ConstantUtils.StrMessages.MSG_VERIFY_DATE);
				LOGGER.info(ConstantUtils.StrMessages.MSG_EXPECTED_VALUE + expectedValue);
				LOGGER.info(ConstantUtils.StrMessages.MSG_ACTUAL_VALUE + actualValue);
				Assert.assertEquals(ConstantUtils.StrMessages.MSG_VERIFY_DATE, expectedValue, actualValue);
			}
			else if(rowItem.get("Key").contentEquals("tags")) {
				Object logoWebsite = getAllCryptoInfoCallData().get("tags");
				expectedValue = rowItem.get("Value").trim();
				actualValue = logoWebsite.toString().trim();
				LOGGER.info(ConstantUtils.StrMessages.MSG_VERIFY_TAGS);
				LOGGER.info(ConstantUtils.StrMessages.MSG_EXPECTED_VALUE + expectedValue);
				LOGGER.info(ConstantUtils.StrMessages.MSG_ACTUAL_VALUE + actualValue);
				if(actualValue.contains(expectedValue)) {
					Assert.assertTrue(ConstantUtils.StrMessages.MSG_VERIFY_TAGS, true);
				}
			}
		}
	}
	
	public static void verifyMineableTagInfo(Response response) {
		JsonPath jsonPath = response.jsonPath();
		String responseData = jsonPath.getString("data");
		LOGGER.info("Response: " + responseData);
		Map<Object, LinkedHashMap<String, LinkedHashMap<String, Object>>> mapOfMaps = jsonPath.getMap("data");
		Collection<LinkedHashMap<String, LinkedHashMap<String, Object>>> innerMapData = mapOfMaps.values();
		Iterator iterator = innerMapData.iterator();
		while(iterator.hasNext()) {
			LinkedHashMap<String, Object> currentData = (LinkedHashMap<String, Object>) iterator.next();
			String currentTags = currentData.get("tags").toString();
			LOGGER.info("currentTags: " + currentTags);
			if(currentTags.contains("mineable")) {
				LOGGER.info(ConstantUtils.StrMessages.MSG_CURRENCY_WITH_MINEABLE_TAG + currentData.get("name"));
			}
		}
	}
}
