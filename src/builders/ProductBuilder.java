package builders;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Product;
import models.Product.Types;

public class ProductBuilder {
	private static final String NAME_PATTERN = "^[a-zA-Z0-9]{1,15}( [a-zA-Z0-9]{1,15})?$";
	private static final String BARCODE_PATTERN = "^[0-9]{7}$";
	
	private String name;
	private float price;
	private Types type;
	private LocalDate manufactureDate;
	private String barcode;
	private LocalDate expirationDate;
	
	public ProductBuilder(String name) {
		this.name = name;
	}
	
	public ProductBuilder setPrice(float price) {
		this.price = price;
		return this;
	}
	
	public ProductBuilder setType(Types type) {
		this.type = type;
		return this;
	}
	
	public ProductBuilder setManufactureDate(LocalDate manufactureDate) {
		this.manufactureDate = manufactureDate;
		return this;
	}
	
	public ProductBuilder setBarcode(String barcode) {
		this.barcode = barcode;
		return this;
	}
	
	public ProductBuilder setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
		return this;
	}
	
	
	public Product build() {
		Product product = new Product();
		
		Pattern namePattern = Pattern.compile(NAME_PATTERN);
		Pattern barcodePattern = Pattern.compile(BARCODE_PATTERN);
		
		Matcher nameMatch = namePattern.matcher(this.name);
		Matcher barcodeMatch = barcodePattern.matcher(this.barcode);
	
		if(!(nameMatch.matches()))
			throw new IllegalArgumentException("Enter correct name.");
		if(price <= 0 )
			throw new IllegalArgumentException("Enter correct price.");
		if(manufactureDate.compareTo(LocalDate.now()) > 0)
			throw new IllegalArgumentException("Enter correct manufactureDate.");
		if(expirationDate.compareTo(manufactureDate) < 0)
			throw new IllegalArgumentException("Enter correct expirationDate.");
		if(!(barcodeMatch.matches()))
			throw new IllegalArgumentException("Enter correct barcode.");
		
		
		product.setName(name);
		product.setPrice(price);
		product.setType(type);
		product.setManufactureDate(manufactureDate);
		product.setBarcode(barcode);
		product.setExpirationDate(expirationDate);

		return product;
	}
	
}