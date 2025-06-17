package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

@Component
public class NorthwindApplication implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;

    @Override
    public void run(String... args) throws Exception {

        // fire up scanner for user input
        Scanner nwScanner = new Scanner(System.in);

        boolean appRunning = true;

        while (appRunning) {

            // display menu and prompt user for input
            System.out.println("\n🛍️ Product Manager Menu");
            System.out.println("1.) List Products");
            System.out.println("2.) Add Products");
            System.out.println("0. Exit");
            System.out.print("👉 Choose an option: ");
            String choice = nwScanner.nextLine().trim();

            switch (choice) {
                case "1":

                    // list products
                    List<Product> products = productDao.getAll();

                    // header
                    System.out.println("\n🛒 List of Products");
                    System.out.println("═".repeat(60));
                    System.out.printf("%-5s %-30s %-10s %-10s\n", "ID", "Name", "Category", "Price");
                    System.out.println("-".repeat(60));

                    for (Product p : products) {
                        System.out.printf("%-5d %-30s %-10d $%-10.2f\n",
                                p.getProductId(),
                                p.getName(),
                                p.getCategoryId(),
                                p.getPrice());
                    }
                    break;
                case "2":

                    // add a product
                    System.out.print("🆕 Enter product name: ");
                    String name = nwScanner.nextLine();

                    System.out.print("🏷️ Enter category ID: ");
                    int categoryID = Integer.parseInt(nwScanner.nextLine());

                    System.out.print("💵 Enter price: ");
                    double price = Double.parseDouble(nwScanner.nextLine());

                    productDao.add(new Product(0, name, categoryID, price));
                    System.out.println("\n✅ Product added!");
                    break;
                case "0":

                    // exiting app
                    appRunning = false;
                    System.out.println("\n👋 Goodbye!");
                    break;
                default:

                    // user validation
                    System.out.println("\n❗ Invalid option, try again.\n");
            }
        }
    }
}