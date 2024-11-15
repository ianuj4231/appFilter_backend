package com.fashion.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.fashion.exception.InvalidPriceException;


@Service
public class PriceService {
	  public Map<String, Double> validatePrices(Double minPrice, Double maxPrice) {
	        if (minPrice!=null && minPrice<= -1) {
	            throw new InvalidPriceException("minPrice cannot be negative or zero.");
	        }

	        if (maxPrice!=null && maxPrice<= -1) {
	            throw new InvalidPriceException("maxPrice cannot be negative or zero.");
	        }

	        if (minPrice!=null && maxPrice==null) {
	            maxPrice=Double.MAX_VALUE;
	        } else if (maxPrice!=null && minPrice==null) {
	            minPrice=0.00;
	        }

	        Map<String, Double> mp= new HashMap<>();
	        mp.put("minPrice",minPrice);
	        mp.put("maxPrice",maxPrice);

	        return mp;
	    }
    }