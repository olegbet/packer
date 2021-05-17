package com.mobiquity.packer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.mobiquity.exception.APIException;

public class Packer {

  //Method to define package content
  private static String processPackage(Double weight, List<Item> items) {
	  String result = "";
	  
	  items = items.stream().sorted(Comparator.comparing(Item::getCost).reversed()).collect(Collectors.toList());
	  List<Package> resultPackages = new ArrayList<>();
	  
	  for(Item i : items) {
		  
		  Package pack = new Package();
		  
		  Double currentWeight = i.getWeight();
		  Double currentCost = i.getCost();
		  if(currentWeight <= weight && currentWeight <= Utils.MAX_WEIGHT) {
			  pack.put(i);
			  pack.setWeight(i.getWeight());
			  pack.setCost(i.getCost());
			  
			  for(Item j : items) {
				  if(j.equals(i))continue;
				  Double nextWeight = j.getWeight();
				  Double nextCost = j.getCost();
				  if(pack.getWeight() + nextWeight <= weight && 
						  pack.getWeight() + nextWeight <= Utils.MAX_WEIGHT) {
					  pack.put(j);
					  pack.setWeight(pack.getWeight() + j.getWeight());
					  pack.setCost(pack.getCost() + j.getCost());
				  }
			  }
			  resultPackages.add(pack);
		  } 
	  }
	  
	  if(resultPackages.size() > 0) {
		  
		  resultPackages = resultPackages.stream().sorted(Comparator.comparing(Package::getWeight)).collect(Collectors.toList());
		  
		  Package  maxPack = resultPackages.stream().max(Comparator.comparing(Package::getCost))
				  .orElseThrow(NoSuchElementException::new);
		  if(maxPack != null)
			  result = maxPack.getContent().stream().map((p)->String.valueOf(p.getIndex())).collect(Collectors.joining(","));
	  	}else result = "-";
	  return result;
  }
  
  public static String pack(String filePath) throws APIException, IOException {
	  Loader loader = new Loader();
	  List<Package> packages = loader.load(filePath);
	 
	  return packages.stream().map((p)->processPackage(p.getWeight(),p.getContent())).collect(Collectors.joining("\n"));
	  
	}
}
