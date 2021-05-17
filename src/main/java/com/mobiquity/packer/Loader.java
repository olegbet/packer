package com.mobiquity.packer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobiquity.exception.APIException;

public class Loader {
	
	public List<Package> load(String fileName) throws APIException, IOException {
		 String line = "";
		 String res = "";
	     BufferedReader reader = new BufferedReader(new FileReader(fileName));

	     Pattern p = Pattern.compile(Utils.PATTERN);
	     
	     List<Package> packages = new ArrayList();
	     
	     while ((line = reader.readLine()) != null) { 
	    	 
	    	 Package pack = new Package(); 
	    	 
	    	 String[] splitted = line.split(":");
	    	 
	    	 Double packageWeight = Double.parseDouble(splitted[0].replaceAll("\\s", ""));
	    	 
	    	 if(packageWeight.compareTo((double) Utils.MAX_WEIGHT)>0)
	    		 throw new APIException("Package weight "+packageWeight+" exceeds limit, max package weight is "+Utils.MAX_WEIGHT);
	    	 
	    	 pack.setWeight(packageWeight);
	    	 pack.setCost(0.0);
	    	 
	    	 Matcher m = p.matcher(line);
		     while(m.find()) 
		     {
		    	 
		    	 Integer index = Integer.valueOf(m.group("i"));
	             Double weight = Double.valueOf(m.group("w"));
	             
	             if(weight.compareTo((double) Utils.MAX_ITEM_WEIGHT)>0)
		    		 throw new APIException("Item weight "+weight+" exceeds limit, max item weight is "+Utils.MAX_ITEM_WEIGHT);
	             
	             Double cost = Double.valueOf(m.group("c")); 
	             
	             if(cost.compareTo((double) Utils.MAX_ITEM_COST)>0)
		    		 throw new APIException("Item cost "+cost+" exceeds limit, max item cost is "+Utils.MAX_ITEM_COST);
		    	 
	             Item item = new Item(index, weight, cost);
	             
	             pack.put(item);
	             
	             if(pack.getContentSize() > Utils.MAX_ITEMS)
	            	 throw new APIException("Item count "+pack.getContentSize()+" exceeds limit, max item count is "+Utils.MAX_ITEMS);
		     }
		     
		     packages.add(pack);
	     }
	     reader.close();

	  return packages;
	}
}
