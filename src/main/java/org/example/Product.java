package org.example;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost, int quantity) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + "," + cost;
    }

    public static Product fromString(String str) {
        String[] parts = str.split(",");
        return new Product(parts[0], Double.parseDouble(parts[1]), 1);
    }

    public double getPrice() {
        return cost;
    }
}
