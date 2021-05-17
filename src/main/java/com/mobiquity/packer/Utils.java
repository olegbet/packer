package com.mobiquity.packer;

import java.util.Comparator;

public class Utils {
	final static int MAX_WEIGHT = 100;
	final static int MAX_ITEMS = 15;
	final static int MAX_ITEM_WEIGHT = 100;
	final static int MAX_ITEM_COST = 100;
	final static String PATTERN = "\\((?<i>\\d+)\\,(?<w>\\d+(\\.\\d{1,2})?)\\,€(?<c>\\d+(\\.\\d{1,2})?)\\)";
}
