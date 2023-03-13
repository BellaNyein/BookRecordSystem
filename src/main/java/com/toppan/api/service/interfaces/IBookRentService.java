package com.toppan.api.service.interfaces;

import java.util.List;

import com.toppan.model.BookRentDTO;

public interface IBookRentService {

	public 	List<BookRentDTO> getTop3BookByCountryId(String countryId) throws Exception;
}
