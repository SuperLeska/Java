package models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.time.LocalDate;

public class Product {

    public enum Types { BAKED , FISH, MEAT, MILKY, DETERGENTS,
        DRINK, SWEETS, FRUITS, GROATS}

    private String name;
    private float price;
    private Types type;
    @JsonSerialize(using = serialization.dateSerializer.JsonLocalDateSerializer.class)
    @JsonDeserialize(using = serialization.dateSerializer.JsonLocalDateDeserializer.class)
    private LocalDate manufactureDate;
    private String barcode;
    @JsonSerialize(using = serialization.dateSerializer.JsonLocalDateSerializer.class)
    @JsonDeserialize(using = serialization.dateSerializer.JsonLocalDateDeserializer.class)
    private LocalDate expirationDate;

    public Product() {
        name = null;
        price = 0;
        type = null;
        manufactureDate  = null;
        barcode = null;
        expirationDate = null;
    }

    public Product(String name, float price, Types type, LocalDate manufactureDate, String barcode, LocalDate expirationDate) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.manufactureDate = manufactureDate;
        this.barcode = barcode;
        this.expirationDate = expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Types getType() {
        return this.type;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getManufactureDate() {
        return this.manufactureDate;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    @JsonIgnore
    public boolean isOverdue() {
        return LocalDate.now().compareTo(expirationDate) > 0;
    }

    @Override
    public String toString() {
        return "Name: " + name + ".\n" +
                "Price: " + price + ".\n" +
                "Type: " + type + ".\n" +
                "Date: " + manufactureDate + ".\n" +
                "Barcode: " + barcode + ".\n" +
                "Termin: " + expirationDate + ".\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
        result = prime * result + ((manufactureDate == null) ? 0 : manufactureDate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Float.floatToIntBits(price);
        result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        Product product = (Product)other;
        if(!name.equals(product.getName())) return false;
        if(price != product.getPrice()) return false;
        if(type != product.getType()) return false;
        if(!manufactureDate.equals(product.getManufactureDate())) return false;
        if(!barcode.equals(product.getBarcode())) return false;
        if(!expirationDate.equals(product.getExpirationDate())) return false;
        return true;
    }
}
