package com.mars.proj.entity;

public class Item {
	private Product prod;
	private int quantity;
	public Product getProd() {
		return prod;
	}
	public void setProd(Product prod) {
		this.prod = prod;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Item(Product prod, int quantity) {
		super();
		this.prod = prod;
		this.quantity = quantity;
	}
	public Item()
	{
		super();
	}

}
