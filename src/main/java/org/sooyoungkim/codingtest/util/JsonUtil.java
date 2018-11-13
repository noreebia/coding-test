package org.sooyoungkim.codingtest.util;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

	// Reads json file and returns json object
	public JSONObject readJsonFile(String fileName) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(fileName));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return (JSONObject) obj;
	}

	public JSONObject getPairInfoOnAllExchanges(JSONObject jsonObject, String pair) {
		JSONObject unfilteredPairInfo = (JSONObject) jsonObject.get(pair);

		JSONObject filteredPairInfo = new JSONObject();
		filteredPairInfo.put("bithumb", getPairInfoOnSingleExchange(unfilteredPairInfo, "bithumb"));
		filteredPairInfo.put("bitfinex", getPairInfoOnSingleExchange(unfilteredPairInfo, "bitfinex"));
		filteredPairInfo.put("korbit", getPairInfoOnSingleExchange(unfilteredPairInfo, "korbit"));
		filteredPairInfo.put("coinone", getPairInfoOnSingleExchange(unfilteredPairInfo, "coinone"));
		return filteredPairInfo;
	}

	private JSONObject getPairInfoOnSingleExchange(JSONObject jsonObject, String exchange) {
		JSONObject exchangeInfo = (JSONObject) jsonObject.get(exchange);
		JSONObject originPairAndLastPrice = new JSONObject();
		if (exchangeInfo != null) {
			originPairAndLastPrice = new JSONObject();
			originPairAndLastPrice.put("last", Double.parseDouble(((String) exchangeInfo.get("last"))));
			originPairAndLastPrice.put("originPair", (String) exchangeInfo.get("originPair"));
		} else {
			originPairAndLastPrice = null;
		}
		return originPairAndLastPrice;
	}
}
