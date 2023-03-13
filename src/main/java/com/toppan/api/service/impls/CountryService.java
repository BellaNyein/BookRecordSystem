package com.toppan.api.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toppan.api.dao.interfaces.ICountryDAO;
import com.toppan.api.service.interfaces.ICountryService;
import com.toppan.model.CountryDTO;

@Service(value = "CountryService")
public class CountryService implements ICountryService {

	@Autowired
	private ICountryDAO countryDAO;
	
	@Override
	public CountryDTO getCountryByCode(String codeNo) throws Exception {
		CountryDTO countryDTO = null;
		try {
			countryDTO = countryDAO.getCountryByCode(codeNo);
		} catch(Exception e) {
			return null;
		}
		return countryDTO;
	}

}
