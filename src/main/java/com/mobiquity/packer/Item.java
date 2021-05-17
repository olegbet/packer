package com.mobiquity.packer;

public class Item {

	private int index;
	private Double weight;
	private Double cost;
	
	public Item(int index, Double weight, Double cost) {
		super();
		this.index = index;
		this.weight = weight;
		this.cost = cost;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
}
