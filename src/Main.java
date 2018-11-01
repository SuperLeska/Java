import models.Product;

import java.time.LocalDate;

public class Main {
    public static void main(String args[]) {
        Product product = new Product("Milka", 30, Product.Types.SWEETS,
                LocalDate.of(2018,12,23), "12345",
                LocalDate.of(2019, 10, 10));

        System.out.println(product);
        System.out.println(product.isOverdue());
    }
}
