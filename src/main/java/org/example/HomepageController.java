package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class HomepageController {

    @FXML
    private TableView<Product> menu_tableView;
    @FXML
    private TableColumn<Product, String> menu_col_product;

    @FXML
    private TableColumn<Product, Integer> menu_col_quantity;

    @FXML
    private TableColumn<Product, Double> menu_col_price;
    @FXML
    private GridPane menu_grid;
    @FXML
    private ScrollPane menu_scroll;
    @FXML
    private Label menu_total;
    @FXML
    private Button menu_payBtn;
    @FXML
    private Button menu_removeBtn;
    @FXML
    private Button menu_recipeBtn;

    private ObservableList<Product> products;

    private Product product;

    @FXML
    private Label prod_cost;

    @FXML
    private Label prod_name;

    @FXML
    private Spinner<Integer> prod_spinner;


    public void initialize() {
        products = FXCollections.observableArrayList();

        menu_col_product.setCellValueFactory(new PropertyValueFactory<>("name"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));


        products = FXCollections.observableArrayList(
                new Product("CPU: AMD", 200.00, 1),
                new Product("CPU: Intel", 150.00, 1)
        );

        menu_tableView.setItems(products);

        menu_removeBtn.setOnAction(event -> handleRemoveButtonAction());
    }

    private void loadProductsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("database.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0].split(": ")[1];
                double price = Double.parseDouble(parts[1].split(" ")[1]);
                Product product = new Product(name, price, 1);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        updateTotal();
    }

    private void handleRemoveButtonAction() {
        Product selectedProduct = menu_tableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            products.remove(selectedProduct);
            updateTotal();
        }
    }

    @FXML
    private void handleAddButtonAction() {

        Product newProduct = new Product("New Product", 100.0, 1);
        addProduct(newProduct);
    }

    private void updateTotal() {
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        menu_total.setText(String.format("%.2f$", total));
    }
}