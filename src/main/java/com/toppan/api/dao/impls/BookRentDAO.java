package com.toppan.api.dao.impls;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.toppan.api.dao.interfaces.IBookRentDAO;
import com.toppan.model.BookRentDTO;
import com.toppan.model.PersonRentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


@Repository("BookRentDAO")
public class BookRentDAO implements IBookRentDAO {

	@Autowired
	protected EntityManager em;
	
	@Override
	public List<BookRentDTO> getTop3BookByCountryId(String countryId) throws Exception {
		List<BookRentDTO> bookRentList = null;
		try {
			String queryStr = "select b.id as bookid, b.name as book, a.name as author from books b inner join author_books ab "
					+ " on b.id = ab.book_id inner join authors a on a.id = ab.author_id "
					+ " inner join book_rents br on b.id = br.book_id inner join people p "
					+ " on p.id = br.person_id where p.country_id = '" + countryId
					+ "' group by b.id, a.id order by count(b.id) desc limit 3";

			Query q = em.createNativeQuery(queryStr);
			List<Object[]> results = q.getResultList();
			if (results.size() > 0) {
				bookRentList = new ArrayList<BookRentDTO>();
				for (Object[] obj : results) {
					BookRentDTO b = new BookRentDTO();
					b.setBookId((int) obj[0]);
					b.setBook((String) obj[1]);
					b.setAuthor((String) obj[2]);
					bookRentList.add(b);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return bookRentList;

	}


	@Override
	public List<PersonRentDTO> getTop3RentPeople(String countryId, int bookId) throws Exception {
		List<PersonRentDTO> peopleRentList = null;
		try {
			String queryStr = "select p.id, p.name from people p inner join book_rents br on p.id = br.person_id "
								+ " inner join books b on b.id = br.book_id "
								+ " where p.country_id = '" + countryId + "' and b.id = '" + bookId + "' group by p.id order by count(p.id) desc "
								+ " limit 3";
			Query q = em.createNativeQuery(queryStr);
			List<Object[]> results = q.getResultList();
			if (results.size() > 0) {
				peopleRentList = new ArrayList<>();
				for (Object[] obj : results) {
					PersonRentDTO p = new PersonRentDTO();
					p.setId((int) obj[0]);
					p.setName((String) obj[1]);
					peopleRentList.add(p);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return peopleRentList;
	}

}
