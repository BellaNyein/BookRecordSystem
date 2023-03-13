package com.toppan.api.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.toppan.api.message.ResponseMessage;
import com.toppan.api.service.interfaces.IBookRentService;
import com.toppan.api.service.interfaces.ICountryService;
import com.toppan.model.BookRentDTO;
import com.toppan.model.CountryDTO;



@RestController
public class BookController {

	 @Autowired
	 private IBookRentService bookRentService;
	 
	 @Autowired
	 private ICountryService countryService;
	 
	 ResponseMessage responseMessage = new ResponseMessage();

	@RequestMapping(value = "/getTop3ReadBooks", method = RequestMethod.GET)
	public ResponseEntity<Object> getTop3ReadBooks(@RequestParam(name = "country_code") String countryCode) {
        // validate country code
        if (!isValidCountryCode(countryCode)) {
        	responseMessage.setMessage("invalid parameter");
        	responseMessage.setStatus("400 Bad Request");
        	return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        // retrieve top 3 rented books in the country
        List<BookRentDTO> top3Books = null;
        CountryDTO countryDTO = null;
		try {
			countryDTO = countryService.getCountryByCode(countryCode);
			top3Books = bookRentService.getTop3BookByCountryId(String.valueOf(countryDTO.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}

        // check if results exist
        if (top3Books == null || top3Books.isEmpty()) {
        	responseMessage.setMessage("No results");
        	responseMessage.setStatus("404 Not Found");
        	return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        // return results
        return new ResponseEntity<>(top3Books, HttpStatus.OK);
    }

    private boolean isValidCountryCode(String countryCode) {
    	String[] validCountryCodes = {"SG", "MY", "US"}; // Add any additional valid country codes here
        return Arrays.asList(validCountryCodes).contains(countryCode);
    }

}
