package com.toppan.api.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toppan.api.dao.interfaces.ICountryDAO;
import com.toppan.model.CountryDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository("CountryDAO")
public class CountryDAO implements ICountryDAO {

	@Autowired
	protected EntityManager em;
	
	@Override
	public CountryDTO getCountryByCode(String codeNo) throws Exception {
		CountryDTO countryDTO = null;
		try {
			Query q = em.createNativeQuery("SELECT id, name, code FROM Country WHERE code = '" + codeNo + "'");
			Object[] obj = (Object[])  q.getSingleResult();
			if (obj != null) {
				countryDTO = new CountryDTO();
				long id = (long) obj[0];
				countryDTO.setId((int) id);
				countryDTO.setName((String) obj[1]);
				countryDTO.setCode((String) obj[2]);
			}
		} catch(Exception e) {
			return null;
		}
		
		return countryDTO;
	}

}
