package com.toppan.api.dao.interfaces;

import com.toppan.model.CountryDTO;

public interface ICountryDAO {

	public CountryDTO getCountryByCode(String codeNo) throws Exception;
}
