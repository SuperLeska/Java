package test;

import java.time.LocalDate;

import models.Product;
import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

public class TestProduct {
    @Test(dataProvider = "overdueProvider")
    public void overdueTest(Product product, boolean b) {
        assertEquals(product.isOverdue(), b);
    }

    @DataProvider
    public Object[][] overdueProvider(){
        Product a = new Product("Milka", 100.50f, Product.Types.MILKY, LocalDate.of(2002, 1, 31), "4521376", LocalDate.of(2017, 10, 11));
        Product b = new Product("Hlib", 50.50f, Product.Types.BAKED, LocalDate.of(2002, 1, 31), "4521376", LocalDate.of(2020, 1, 26));
        return new Object[][] { { a, true } , { b, false } };
    }

}