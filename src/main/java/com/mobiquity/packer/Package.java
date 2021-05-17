package com.mobiquity.packer;

import java.util.ArrayList;
import java.util.List;

public class Package {
	List<Item> content;
	private Double weight;
	private Double cost;
	
	public Package() {
		super();
		content = new ArrayList();
	}

	public List<Item> getContent() {
		return content;
	}

	public void setContent(List<Item> content) {
		this.content = content;
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

	public int getContentSize() {
		
		return content.size();
	}

	public void put(Item item) {
		content.add(item);
		
	}
	
}
