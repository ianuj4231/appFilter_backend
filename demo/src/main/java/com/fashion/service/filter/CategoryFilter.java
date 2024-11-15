package com.fashion.service.filter;

import com.fashion.entity.Fashion;

public class CategoryFilter   implements FilterStrategy{

	String categ;
	public CategoryFilter(String categ) {
		this.categ = categ;
	}
	
	@Override
	public boolean isMatches(Fashion obj) {
		
		if( obj.getCategory().equalsIgnoreCase(categ) ) {
			return true;
		}
		
		
		else return false;
	}

	
	@Override
    public String toString() {
        return "categ=" + categ;
    }
}