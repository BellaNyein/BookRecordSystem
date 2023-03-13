package com.toppan.api.service.interfaces;

import com.toppan.model.CountryDTO;

public interface ICountryService {

	public CountryDTO getCountryByCode(String codeNo) throws Exception;
}
