package com.fashion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fashion.entity.Fashion;
import com.fashion.service.FashionService;
import com.fashion.service.PriceService;
import com.fashion.service.filter.BrandFilter;
import com.fashion.service.filter.CategoryFilter;
import com.fashion.service.filter.ColorFilter;
import com.fashion.service.filter.FilterStrategy;
import com.fashion.service.filter.PriceFilter;
import com.fashion.service.filter.RatingFilter;
import com.fashion.service.filter.SizeFilter;

@RequestMapping("/api/v1/fashion")
@RestController

public class FashionController {
		
	   @Autowired
	   private PriceService priceService;

		@Autowired
	    private FashionService fashionService;
		
	 @GetMapping("/filter")
	  public List<Fashion> filterProducts(
		   @RequestParam(required=false) String category,
           @RequestParam(required=false) Double minPrice,
           @RequestParam(required=false) Double maxPrice,
           @RequestParam(required=false) String size,
           @RequestParam(required=false) String color,
           @RequestParam(required=false) String brand,
           @RequestParam(required=false) Integer rating,
           @RequestParam(defaultValue="0") int page,
           @RequestParam(defaultValue="10") int pagesize,
           @RequestParam(defaultValue = "rating") String sortby,
           @RequestParam(defaultValue = "desc") String sortdirection

           )   {
		   
	        Map<String, Double> mp= priceService.validatePrices(minPrice, maxPrice);
	        minPrice = mp.get("minPrice");
	        maxPrice = mp.get("maxPrice");
	 
	        List<FilterStrategy> strategies = new ArrayList<>();
	        if (category!=null) strategies.add(new CategoryFilter(category));
	        if (minPrice!=null && maxPrice != null) strategies.add(new PriceFilter(minPrice, maxPrice));
	         if (size!=null) strategies.add(new SizeFilter(size));
	        if (color!=null) strategies.add(new ColorFilter(color));
	        if (brand!=null) strategies.add(new BrandFilter(brand));
	        if (rating!=null) strategies.add(new RatingFilter(rating));
	        List<Fashion> filteredProducts = fashionService.filterObjects(strategies, page, pagesize, sortby, sortdirection);
	        System.out.println("filtered_products---" + filteredProducts);
	        return filteredProducts;
	 }
	
	
	 @GetMapping("/clear_cache")
	    public String clearCache() {
	        fashionService.clearCache();
	        return "cache cleared successfully";
	    }
}
 