package com.toppan.api.dao.interfaces;

import java.util.List;

import com.toppan.model.BookRentDTO;
import com.toppan.model.PersonRentDTO;

public interface IBookRentDAO {

	public List<BookRentDTO> getTop3BookByCountryId(String countryId) throws Exception;
	
	public List<PersonRentDTO> getTop3RentPeople(String countryId, int bookId) throws Exception;
}
