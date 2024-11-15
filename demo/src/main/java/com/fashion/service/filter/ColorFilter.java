package com.fashion.service.filter;

import com.fashion.entity.Fashion;

public class ColorFilter  implements FilterStrategy{
   String color;
	public ColorFilter( String color ) {
		   this.color = color;
	}
	
	
	@Override
	public boolean isMatches(Fashion obj) {
	
		if( obj.getColor().equalsIgnoreCase(color)) {
					return true;
		}
		
		else 	return false;
	}
	
	@Override
    public String toString() {
        return "color=" + color;
    }
	
}
