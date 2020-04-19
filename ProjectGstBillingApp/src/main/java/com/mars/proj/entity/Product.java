package com.mars.proj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	// define fields
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="serialno")
		private int id;
		
		@Column(name="product_code")
		private String code;
		
		@Column(name="product_name")
		private String name;
		
		@Column(name="product_price")
		private double price;

		@Column(name="product_gst")
		private double gst;
		
			
		// define constructors
		
		public Product() {
			
		}
		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getGst() {
			return gst;
		}

		public void setGst(double gst) {
			this.gst = gst;
		}


		// define getter/setter
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Product(int id, String code, String name, double price, double gst) {
			super();
			this.id = id;
			this.code = code;
			this.name = name;
			this.price = price;
			this.gst = gst;
		}

		public Product(String code, String name, double price, double gst) {
			super();
			this.code = code;
			this.name = name;
			this.price = price;
			this.gst = gst;
		}

		@Override
		public String toString() {
	     
			return "Product [id=" + id + ", product_code=" + code + ", product_name=" + name + ", product_price=" + price + ", product_gst=" + gst + "]" ;
			
		}

}
