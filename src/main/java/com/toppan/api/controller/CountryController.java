package com.toppan.api.controller;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.toppan.api.message.ResponseMessage;
import com.toppan.api.service.interfaces.ICountryService;
import com.toppan.model.CountryDTO;

@RestController
public class CountryController {
	
	@Autowired
	private ICountryService countryService;
	
	ResponseMessage responseMessage = new ResponseMessage();
	
	@RequestMapping(value = "/getRandomCountry", method = RequestMethod.GET)
	public ResponseEntity<Object> getRandomCountry() {
		String[] arr = {"SG", "MY", "US"};
        int randIdx = ThreadLocalRandom.current().nextInt(arr.length);
        String randomElem = arr[randIdx];
        CountryDTO countryDTO = null;
		try {
			countryDTO = countryService.getCountryByCode(randomElem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		
		if (countryDTO == null) {
			responseMessage.setMessage("No results");
			responseMessage.setStatus("404 Not Found");
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
		
		// return results
		return new ResponseEntity<>(countryDTO, HttpStatus.OK);
	}

}
