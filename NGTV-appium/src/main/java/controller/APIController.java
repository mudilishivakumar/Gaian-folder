package controller;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIController {
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);

	private APIController(){}

	/**
	 * Method to make GET request
	 *
	 * @param url         - The URL to make request for
	 * @param headerList  - List of headers to add to the request
	 * @param queryParams - List of query params to add to the URL
	 * @return - Response
	 */
	public static Response makeGetRequest(String url, Headers headerList, Map<String, String> queryParams) {
		logger.debug("----------------------------------------------------------------- Inside makeGetRequest -----------------------------------------------------------------");
		RequestSpecification request = RestAssured.given();
		logger.debug("URL >> {}", url);
		if (queryParams != null) {
			logger.debug("List of query Params >>");
			for (Map.Entry<String, String> param : queryParams.entrySet()) {
				logger.debug("Key: [{}], Value: [{}]", param.getKey(), param.getValue());
				request = request.queryParam(param.getKey(), param.getValue());
			}
		}
		if (headerList != null) {
			logger.debug("Headers >> ");
			headerList.forEach(header -> logger.debug("Key: [{}], Value: [{}]", header.getName(), header.getValue()));
			return request.headers(headerList).when().get(url);
		} else
			return request.when().get(url);
	}

	/**
	 * Method to make POST request
	 *
	 * @param url           - The URL to make request for
	 * @param headerList    - List of headers to add to the request
	 * @param payloadString - The body to send to the API Endpoint
	 * @return - Response
	 */
	public static Response makePostRequest(String url, Headers headerList, String payloadString) {
		logger.debug("----------------------------------------------------------------- Inside makePostRequest -----------------------------------------------------------------");
		logger.debug("URL >> {}", url);
		if (headerList != null) {
			logger.debug("Headers >> ");
			headerList.forEach(header -> logger.debug("Key: [{}], Value: [{}]", header.getName(), header.getValue()));
			if (payloadString != null) {
				logger.debug("Payload >> {}", payloadString);
				return given().headers(headerList).body(payloadString).when().post(url);
			} else {
				return given().headers(headerList).when().post(url);
			}
		} else {
			if (payloadString != null) {
				logger.debug("Payload >> {}", payloadString);
				return given().body(payloadString).when().post(url);
			} else {
				return given().when().post(url);
			}
		}
	}

	/**
	 * Method to make POST request for x-www-form-urlencoded data
	 *
	 * @param url        - The URL to make request for
	 * @param headerList - List of headers to add to the request
	 * @param payloadMap - The body to send to the API Endpoint
	 * @return - Response
	 */
	public static Response makePostRequest(String url, Headers headerList, Map<String, String> payloadMap) {
		logger.debug("----------------------------------------------------------------- Inside makePostRequest -----------------------------------------------------------------");
		logger.debug("URL >> {}", url);

		RequestSpecification request = RestAssured.given().config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
						.encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				        .contentType(ContentType.URLENC.withCharset("UTF-8"));

		if (payloadMap != null) {
			logger.debug("Payload >>");
			for (Map.Entry<String, String> payloadEntry : payloadMap.entrySet()) {
				logger.debug("Key: {}, Value: {}", payloadEntry.getKey(), payloadEntry.getValue());
				request.formParam(payloadEntry.getKey(), payloadEntry.getValue());
			}
		}

		if (headerList != null) {
			logger.debug("Headers >> ");
			headerList.forEach(header -> logger.debug("Key: [{}], Value: [{}]", header.getName(), header.getValue()));
			return request.headers(headerList).when().post(url);
		} else {
			return request.when().post(url);
		}
	}

	/**
	 * Method to make Head Request
	 *
	 * @param url        - The URL to make request for
	 * @param headerList - List of headers to add to the request
	 * @return - Response
	 */
	public static Response makeHeadRequest(String url, Headers headerList) {
		logger.debug("----------------------------------------------------------------- Inside makeHeadRequest -----------------------------------------------------------------");
		logger.debug("URL >> {}", url);
		if (headerList != null) {
			logger.debug("Headers >> ");
			headerList.forEach(header -> logger.debug("Key: [{}], Value: [{}]", header.getName(), header.getValue()));
			return given().headers(headerList).when().head(url);
		} else
			return given().when().head(url);
	}

	/**
	 * Method to make PUT request
	 *
	 * @param url         - The URL to make request for
	 * @param headerList  - List of headers to add to the request
	 * @param queryParams - List of query params to add to the URL
	 * @return - Response
	 */
	public static Response makePutRequest(String url, Headers headerList, Map<String, String> queryParams) {
		logger.debug("----------------------------------------------------------------- Inside makePutRequest -----------------------------------------------------------------");
		logger.debug("URL >> {}", url);

		JSONObject requestParams = new JSONObject();
		for (Map.Entry<String, String> paramEntry : queryParams.entrySet()) {
			logger.debug("Key: [{}], Value: [{}]", paramEntry.getKey(), paramEntry.getValue());
			requestParams.put(paramEntry.getKey(), paramEntry.getValue());
		}

		if (headerList != null) {
			logger.debug("Headers >> ");
			headerList.forEach(header -> logger.debug("Key: [{}], Value: [{}]", header.getName(), header.getValue()));
			return given().headers(headerList).contentType(ContentType.JSON).body(requestParams.toString()).when().head(url);
		} else
			return given().contentType(ContentType.JSON).body(requestParams.toString()).when().put(url);
	}

}
