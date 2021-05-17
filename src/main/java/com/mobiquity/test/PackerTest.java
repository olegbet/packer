package com.mobiquity.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

public class PackerTest {

	String exampleOutput = "";
	
	@Before
	public void setUp() throws Exception {
	
		BufferedReader reader = new BufferedReader(new FileReader("src/main/test/resources/example_output"));
		String line = "";
		while ((line = reader.readLine()) != null) { 
			exampleOutput += line + "\n";	
		}
		exampleOutput = exampleOutput.substring(0, exampleOutput.length()-1);
		
	}

	@Test
	public void test() throws APIException, IOException {
		assertEquals(Packer.pack("src/main/test/resources/example_input"),exampleOutput);
	}

}
