package com.fashion.service.filter;
import com.fashion.entity.Fashion;

public interface FilterStrategy {
		  boolean isMatches (Fashion obj);
}
