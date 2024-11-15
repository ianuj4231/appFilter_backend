package com.fashion.service.filter;

import com.fashion.entity.Fashion;

public class PriceFilter   implements FilterStrategy{

	private Double minprice;
	private Double maxprice;
		
	
	public PriceFilter( Double minprice, Double maxprice) {
						this.minprice = minprice;
						this.maxprice = maxprice;
	}
	
	@Override
	public boolean isMatches(Fashion obj) {
		
		
	if(  obj.getPrice()>=minprice &&  obj.getPrice() <= maxprice) {
			return  true;
	}
	else return false;
	}
	
	@Override
    public String toString() {
        return  "minprice= "+ minprice + "maxprice= " + maxprice;
    }
}
