import builders.ProductBuilder;
import models.Product;
import serialization.Serialization;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws Exception {
        Product product2 = new Product("Mil0 ka", 30, Product.Types.SWEETS,
                LocalDate.of(2017,12,23), "12345",
                LocalDate.of(2019, 10, 10));

        Product product1 = new ProductBuilder("Danon").setPrice(30).setType(Product.Types.SWEETS)
                .setManufactureDate(LocalDate.of(2017,12,23)).setBarcode("5432432")
                .setExpirationDate(LocalDate.of(2019, 10, 10)).build();
        System.out.println(product1);
        //System.out.println(product.isOverdue());

        List<Product> pr = new ArrayList<>();
        pr.add(product1);
        pr.add(product2);

        Serialization<Product> serialization = new serialization.JsonSerialize<>(Product.class);
        serialization.toFile(pr, new File("../product.json"));
        //System.out.println(serialization.fromFile(new File("../product.json")));

        Serialization<Product> xml = new serialization.XmlSerialize<>(Product.class);
        xml.toFile(pr, new File("../product.xml"));
        //System.out.println(xml.fromFile(new File("../product.xml")));
    }
}
