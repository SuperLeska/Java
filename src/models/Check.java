package models;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Check {
	private int idCheck;
	private LocalDate date;
	private LocalTime time;
	private float total;
	private ArrayList<Product> products = null;

	public Check(int id) throws ClassNotFoundException, SQLException {
		this.idCheck = id;
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.total = 0;
		this.products = new ArrayList<Product>();
	}

	public int getIdCheck() {
		return this.idCheck;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return this.time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public ArrayList<Product> getProducts() {
		return this.products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
		total += product.getPrice();
		this.products.add(product);
	}

	public int getProductCount(Product product) {
		return (int)products.stream().filter(s -> s.getName().equals(product.getName())).count();
	}

	@Override
	public String toString() {
		String allProducts = "";
		for(Product p : this.products) {
			if(getProductCount(p) > 1 && allProducts.contains(p.getName()))
				allProducts = allProducts.replace(p.getName() + ":     " +
						p.getPrice(), p.getName() + ": " + getProductCount(p) + " * " + p.getPrice());
			else
				allProducts += p.getName() + ":     " + p.getPrice() + "\n";
		}
		return	"Check #" + idCheck + "\n" + allProducts + "\nTo pay: " + total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idCheck;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + Float.floatToIntBits(total);
		return result;
	}

	@Override
	public boolean equals(Object other) {
		Check check = (Check)other;
		if(idCheck != check.getIdCheck()) return false;
		if(!date.equals(check.getDate())) return false;
		if(!time.equals(check.getTime())) return false;
		if(total != check.getTotal()) return false;
		if(!products.equals(check.getProducts())) return false;
		return true;
	}
}