package org.sooyoungkim.codingtest.controller;

import org.sooyoungkim.codingtest.pojo.Result;
import org.sooyoungkim.codingtest.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/data/currency")
public class PriceController {
	
	@Autowired
	PriceService priceService;

	@RequestMapping(value = "/")
	public Result getAllPrices () {
		try {
			return new Result(true, priceService.getMultiplePairs());
		} catch(Exception e) {
			return new Result(false, null);
		}
	}
	
	@RequestMapping(value = "/{coinName}")
	public Result getPriceOf(@PathVariable String coinName) {
		try {
			return new Result(true, priceService.getSinglePair(coinName));
		} catch(Exception e) {
			return new Result(false, null);
		}
	}

}
