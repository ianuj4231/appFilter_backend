package com.fashion.service.filter;

import com.fashion.entity.Fashion;

public class SizeFilter   implements FilterStrategy{

	private String size;
	public SizeFilter( String size ) {
						this.size  = size;
	}
	
	@Override
	public boolean isMatches(Fashion obj) {
		if(obj.getSize().equalsIgnoreCase (size) ) {
			return true;
		}
		else return false;
	}
	@Override
    public String toString() {
        return "size=" + size;
    }   

}
