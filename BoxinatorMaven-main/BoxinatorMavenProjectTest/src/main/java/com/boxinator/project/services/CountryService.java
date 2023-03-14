package com.boxinator.project.services;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.boxinator.models.Country;
import com.boxinator.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepo;
	
	public ResponseEntity<List<Country>> getAllCountries() {
		List<Country> countryList = null;
		HttpStatus httpStatus = null;

		try {
			countryList = countryRepo.findAll();
			
			if (countryList.isEmpty()) {
				httpStatus = HttpStatus.NOT_FOUND;
			}else {
				httpStatus = HttpStatus.OK;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}		
		return new ResponseEntity<>(countryList, httpStatus);
	}
	
	public ResponseEntity<Country> getCountryById(Long id){
		HttpStatus httpStatus = null;
		Country returnCountry = null;

		try {
			if ((returnCountry = countryRepo.getReferenceById(id)) != null) {
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnCountry, httpStatus);
	}
	
	public ResponseEntity<Country> createCountry(Country newCountry) {
		HttpStatus httpStatus = null;
		Country returnCountry = null;
		Long countryId = newCountry.getCountryId();

		try {
			if (countryRepo.existsById(countryId) == false) {
				returnCountry = countryRepo.saveAndFlush(newCountry);
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnCountry, httpStatus);
	}
	
	public ResponseEntity<Country> updateCountry(Country newCountry, Long id) {
		Country returnCountry = null;
		HttpStatus httpStatus = null;
		try {
			if ((returnCountry = countryRepo.getReferenceById(id)) != null) {
				newCountry = (Country) HelperService.partialUpdate(returnCountry, newCountry);
				returnCountry = countryRepo.saveAndFlush(newCountry);
				httpStatus = HttpStatus.OK;
			}else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (BeansException e) {
			System.out.printf("Failed to copy values into user object...\nPrinting message...");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnCountry, httpStatus);
	}
	

	public ResponseEntity<Country> deleteCountry(Long id) {
		HttpStatus httpStatus = null;
		Country returnCountry = null;

		try {
			if((returnCountry = countryRepo.getReferenceById(id)) != null) {
				countryRepo.deleteById(id);
				httpStatus = HttpStatus.OK;
			}else {
				httpStatus = HttpStatus.BAD_GATEWAY;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnCountry, httpStatus);
	}
}
	
