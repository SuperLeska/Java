import builders.ProductBuilder;
import builders.WorkerBuilder;
import database.Database;
import models.Check;
import models.Paydesk;
import models.Product;
import models.Worker;
import serialization.Serialization;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
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
                setExpirationDate(LocalDate.of(2018, 2, 3)).
                build();

        //----------------------------------------------------------------------------------------------
        Worker worker1 = new WorkerBuilder(1).
                setFirstName("Volodya").
                setLastName("Jugyl").
                setSurName("Zalischuk").
                setBirthdate(LocalDate.of(1998, 1, 15)).build();

        Worker worker2 = new WorkerBuilder(2).
                setFirstName("Alyona").
                setLastName("Gunder").
                setSurName("Olegivna").
                setBirthdate(LocalDate.of(1997, 6, 17)).build();

        Worker worker3 = new WorkerBuilder(3).
                setFirstName("Mishya").
                setLastName("Onuskiv").
                setSurName("Andriyovych").
                setBirthdate(LocalDate.of(1997, 10, 7)).build();
        //----------------------------------------------------------------------------------------------
        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        workers.add(worker3);

        //System.out.println(product1.isOverdue());
        //System.out.println(product1.toString());
        //System.out.println(product2.toString());

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Check check1 = new Check(1);
        check1.addProduct(product1);
        check1.addProduct(product2);

        Check check2 = new Check(2);
        check2.addProduct(product1);
        check2.addProduct(product2);
        check2.addProduct(product1);
        check2.addProduct(product2);
        check2.addProduct(product2);
        check2.addProduct(product2);

        Paydesk paydesk1 = new Paydesk(1, worker1);
        System.out.println(paydesk1.issueCheck(check1, 200));
        System.out.println(paydesk1.issueCheck(check2, 200));

        Map<LocalDate, ArrayList<Check>> checkHistory = paydesk1.getHistory();
        List<Check> checklist = new ArrayList<>();
        checklist = checkHistory.get(LocalDate.now());

        Map<Product, Integer> mapProducts = new HashMap<Product, Integer>();
        for(Product prod : products) {
            int value = 5 + new Random(System.currentTimeMillis()).nextInt(25);
            mapProducts.put(prod, value);
        }

        Database db = new Database();
        Database.dropAllTables();
        Database.createAllTables();
        db.addNewProduct(product1, 5);
        db.addNewProduct(product2, 5);
        db.addProducts(mapProducts);
        db.addWorkers(workers);
        db.makePurchase(paydesk1, check1);
        db.makePurchase(paydesk1, check2);
    }
}