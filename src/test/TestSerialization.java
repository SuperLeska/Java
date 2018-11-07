package test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builders.ProductBuilder;
import models.Product;
import serialization.Serialization;

public class TestSerialization {
	File jsonTest = new File("../jsonTest");
	File xmlTest = new File("../xmlTest");
	ArrayList<Product> products = null;
	
	@BeforeTest
	public void init() {
		Product product1 = new ProductBuilder("Apple").
				setPrice(10f).
				setType(Product.Types.FRUITS).
				setManufactureDate(LocalDate.of(2017, 10, 8)).
				setBarcode("1275944").
				setExpirationDate(LocalDate.of(2018, 1, 1)).
				build();

		Product product2 = new ProductBuilder("Danon").
				setPrice(25.25f).
				setType(Product.Types.MILKY).
				setManufactureDate(LocalDate.of(2017, 5, 5)).
				setBarcode("7221326").
				setExpirationDate(LocalDate.of(2018, 2, 3))
				.build();

		products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
	}
	
	
	private boolean equalsCollection(List<?> List, List<?> expected) {
		for(int i = 0; i < List.size(); i ++)
			if(!List.get(i).equals(expected.get(i)))
				return false;
		return true;
	}
	
	@Test(dataProvider = "jsonSerialization")
	public void jsonSerializationTest(List<?> List, List<?> expected) {
		assertTrue(equalsCollection(List, expected));
	}
	@DataProvider
	public Object[][] jsonSerialization() throws Exception{
		List<Product> expectedProducts;

		Serialization<Product> p = new serialization.JsonSerialize<>(Product.class);
		p.toFile(products, jsonTest);
		expectedProducts = p.fromFile(jsonTest);
		

		return new Object[][] {{products, expectedProducts}};
	}
	
	@Test(dataProvider = "xmlSerialization")
	public void xmlSerializationTest(List<?> List, List<?> expected) {
		assertTrue(equalsCollection(List, expected));
	}
	@DataProvider
	public Object[][] xmlSerialization() throws Exception{
		List<Product> expectedProducts;

		Serialization<Product> p = new serialization.XmlSerialize<Product>(Product.class);
		p.toFile(products, xmlTest);
		expectedProducts = p.fromFile(xmlTest);

		return new Object[][] {{products, expectedProducts}};
	}
}
