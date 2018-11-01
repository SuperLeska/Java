package models;

import java.time.LocalDate;

public class Product {

    public enum Types { BAKED , FISH, MEAT, MILKY, DETERGENTS,
        DRINK, SWEETS, FRUITS, GROATS}

    private String name;
    private float price;
    private Types type;
    private LocalDate date;
    private String barcode;
    private LocalDate termin;

    public Product() {
        name = null;
        price = 0;
        type = null;
        date = null;
        barcode = null;
        termin = null;
    }

    public Product(String name, float price, Types type, LocalDate date, String barcode, LocalDate termin) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.date = date;
        this.barcode = barcode;
        this.termin = termin;
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

    public void setType(String type) {
        this.type = Types.valueOf(type);
    }

    public Types getType() {
        return this.type;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setTermin(LocalDate termin) {
        this.termin = termin;
    }

    public LocalDate getTermin() {
        return this.termin;
    }

    public boolean isOverdue() {
        return LocalDate.now().compareTo(termin) > 0;
    }

    @Override
    public String toString() {
        return "Name: " + name + ".\n" +
                "Price: " + price + ".\n" +
                "Type: " + type + ".\n" +
                "Date: " + date + ".\n" +
                "Barcode: " + barcode + ".\n" +
                "Termin: " + termin + ".\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Float.floatToIntBits(price);
        result = prime * result + ((termin == null) ? 0 : termin.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object other) {
        Product product = (Product)other;
        if(!name.equals(product.getName())) return false;
        if(price != product.getPrice()) return false;
        if(type != product.getType()) return false;
        if(!date.equals(product.getDate())) return false;
        if(!barcode.equals(product.getBarcode())) return false;
        if(!termin.equals(product.getTermin())) return false;
        return true;
    }
}
