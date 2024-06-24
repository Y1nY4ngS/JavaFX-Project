package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static final String FILE_PATH = "database.txt";

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                products.add(Product.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(Product product) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(product.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
