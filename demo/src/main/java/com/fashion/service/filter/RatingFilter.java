package com.fashion.service.filter;
import com.fashion.entity.Fashion;

public class RatingFilter implements FilterStrategy {
	private int rating;
	public  RatingFilter (int rating ){
			this.rating = rating;
	}
	
	@Override
	public boolean isMatches(Fashion obj) {
		if(  obj.getRating() == rating ) {
			return true;
		}
		
		else 		return false;
	}
	@Override
    public String toString() {
        return "rating=" + rating;
    }
}
