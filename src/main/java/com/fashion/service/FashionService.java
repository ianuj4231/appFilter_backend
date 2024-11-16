package com.fashion.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
//import java.util.UUID;
import org.springframework.stereotype.Service;
import com.fashion.entity.Fashion;
import com.fashion.exception.ServiceLayerException;
import com.fashion.service.filter.FilterStrategy;

@Service
public class FashionService {

	
    private List<Fashion> fashionList = new ArrayList<>(); 
 	private String[] categories = {"dresses", "shoes", "belts"};
 	private String[] colors ={"red", "blue", "green", "black", "white"};
 	private String[] sizes = {"S", "M", "XXL",  "L", "XL"};
 	private String[] brands = {"h&m", "woodland", "armaniexchange", "one8", "mayuri" ,  "bata" };

    
    
    public FashionService() {
    			createInMemoryData();
	 }
    public  void  createInMemoryData() {
	    for (int i = 0; i < 50; i++) { 
	    		Fashion fashionobj = new Fashion(
	    			    categories[i % categories.length],
	    				  (i+1)*10.0
	    				  ,
	    				  sizes[i % sizes.length],
	    				   colors[i % colors.length],
	    				   brands[i % brands.length],
	    				   (i % 5) + 1 
	    				);
	    	    
	    		if(fashionobj.getCategory()=="dresses") {
	    			  System.out.println("created_fashion_item: " + fashionobj.toString());
	    		}
	    		
	    		  fashionList.add(fashionobj);
	    		  
	    }
//	    System.out.println("totoal no of  fashion items" + fashionList.size()); 
 }

    @Cacheable(value = "fashionItems", key = "#strategies.toString() + '_' + #page + '_' + #pagesize + '_' + #sortby + '_' + #sortdirection")
    public List<Fashion> filterObjects(List<FilterStrategy> strategies, int page, int pagesize, String sortby, String sortdirection) {
		try {
			System.out.println(strategies.toString() + "  "+ page + " "+ pagesize+ " "+ sortby+ " "+ sortdirection);

			List<Fashion> filteredlist = new ArrayList<>();
	        for(Fashion obj: fashionList  ) {
	        			boolean ismatchvar = true;
	        			for(FilterStrategy strategy: strategies) {
	        							 if(!strategy.isMatches(obj)) {
	        								 ismatchvar = false;		
	        								 break;
	        							 } 
	        			}  
	        			if(ismatchvar) {
	        				 filteredlist.add(obj);
	        	        }
	        }
	        
			int startindex = Math.min( page*pagesize, filteredlist.size() ) ;
			int endindex = Math.min(  (page+1)* pagesize,  filteredlist.size());
		    List<Fashion> paginatedlist = filteredlist.subList(startindex, endindex);

	        if(sortby.equalsIgnoreCase("rating")){
	        	 if(sortdirection.equalsIgnoreCase("desc")) {
	        		 paginatedlist.sort(Comparator.comparingInt(Fashion::getRating).reversed());

	  	       }
	  	       else {
	  	    	 paginatedlist.sort(Comparator.comparingInt(Fashion::getRating));
	  	       }	
	        }
	        else  if(sortby.equalsIgnoreCase("price")) {
	        	       if(sortdirection.equalsIgnoreCase("desc")) {
	        	    	   paginatedlist.sort(Comparator.comparingDouble(Fashion::getPrice).reversed());

	        	       }
	        	       else {
	        	    	   paginatedlist.sort(Comparator.comparingDouble(Fashion::getPrice));

	        	       }
	        }
	        
	        return new ArrayList<>(paginatedlist); 			
			
		} catch (Exception e) {
			throw new ServiceLayerException( "internal server error", e );
			
		}
		
        }
    
   
    @CacheEvict(value = "fashionItems", allEntries = true)
    public void clearCache() {
       System.out.println("cache clearing function called");
    }
}
