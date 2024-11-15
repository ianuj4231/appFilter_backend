package com.fashion.service.filter;

import com.fashion.entity.Fashion;

public class BrandFilter   implements FilterStrategy{

    private String brand;

	public BrandFilter( String inputbrand ) {
					this.brand = inputbrand;
	}
	
	@Override
	public boolean isMatches(Fashion obj) {
		if(  !(obj.getBrand().equalsIgnoreCase(brand))) {
			return false;			
		}
		else return true;
	}
	@Override
    public String toString() {
        return "brand=" + brand;
    }
}
