package org.sooyoungkim.codingtest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.sooyoungkim.codingtest.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

	@Value("${filename}")
	String fileName;

	@Autowired
	JsonUtil jsonUtil;

	// Gets information of five pairs that include KRW. Ex) EOS_KRW, BTG_KRW, LTC_KRW, XRP_KRW, BCH_KRW
	public JSONObject getMultiplePairs() {

		JSONObject fileContents = jsonUtil.readJsonFile(fileName);

		List<String> validPairs = new ArrayList<>();
		for (Iterator iterator = fileContents.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String fiat = key.split("_")[1];
			if (fiat.equals("KRW")) {
				validPairs.add(key);
			}
		}

		JSONObject pairs = new JSONObject();
		for (int i = 0; i < 5; i++) {
			pairs.put(validPairs.get(i), getSinglePair(validPairs.get(i).split("_")[0]));
		}
		return pairs;
	}

	// Gets information of only a single pair
	public JSONObject getSinglePair(String coinName) {
		String pair = coinName + "_KRW"; // converts "BTC" to "BTC_KRW"
		JSONObject fileContents = jsonUtil.readJsonFile(fileName);
		return jsonUtil.getPairInfoOnAllExchanges(fileContents, pair);
	}

}
