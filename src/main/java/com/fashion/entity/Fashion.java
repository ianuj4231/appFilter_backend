package com.fashion.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Fashion implements Serializable{
	private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;
    

	
	   private String size;
	    private String color; 
	    private String brand;  
	    private int rating;
	    
	    private String category;
	    private double price;
	  
	    
	    public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Fashion(String category, double price, String size, String color, String brand, int rating) {
	        this.id =  UUID.randomUUID().toString();
	        this.category = category; 
	        this.price = price;
	        this.size = size;
	        this.color = color;
	        this.brand = brand;
	        this.rating = rating;
	    }

		@Override
		public String toString() {
			return "Fashion [size=" + size + ", color=" + color + ", brand=" + brand + ", rating=" + rating + ", id="
					+ id + ", category=" + category + ", price=" + price + "]";
		}
	    
}
