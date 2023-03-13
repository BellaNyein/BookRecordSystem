package com.toppan.api.service.impls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toppan.api.dao.interfaces.IBookRentDAO;
import com.toppan.api.service.interfaces.IBookRentService;
import com.toppan.model.BookRentDTO;
import com.toppan.model.PersonRentDTO;

@Service(value = "BookRentService")
public class BookRentService implements IBookRentService {

	@Autowired
	private IBookRentDAO bookRentDAO;

	@Override
	public List<BookRentDTO> getTop3BookByCountryId(String countryId) throws Exception {
		List<BookRentDTO> bookRentList = null;
		try {
			bookRentList = bookRentDAO.getTop3BookByCountryId(countryId);
			if (bookRentList != null) {
				for (BookRentDTO b : bookRentList) {
					List<PersonRentDTO> peopleRentList = bookRentDAO.getTop3RentPeople(countryId, b.getBookId());
					if (peopleRentList != null) {
						b.setPersonRentList(peopleRentList);
					}
				}
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return bookRentList;
	}

}
