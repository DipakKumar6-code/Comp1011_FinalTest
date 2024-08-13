// Author: Dipak Bhul
// Student Number: 200564268
package com.example.comp1011finalexamsummer2024.Controllers;

import com.example.comp1011finalexamsummer2024.Model.Customer;
import com.example.comp1011finalexamsummer2024.Model.Product;
import com.example.comp1011finalexamsummer2024.ParseJson.ParseJsonCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.stream.Collectors;

public class TableViewController {

    @FXML
    private Label saleLabel;

    @FXML
    private Label msrpLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label rowsInTableLabel;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, Double> totalPurchaseColumn;

    @FXML
    private ListView<String> purchaseListView; // Changed type to String for formatted display

    @FXML
    private ImageView imageView;

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize TableView columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("totalPurchases"));

        // Load customers from JSON
        String filePath = "/com/example/comp1011finalexamsummer2024/Data/customers.json";
        List<Customer> customers = ParseJsonCustomer.loadCustomerData(filePath);

        if (customers != null) {
            customerList = FXCollections.observableArrayList(customers);
            tableView.setItems(customerList);
            updateRowsInTableLabel();
        } else {
            System.err.println("No customers loaded from JSON file.");
        }

        // Add a listener to handle selection changes
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleCustomerSelection();
        });
    }

    private void updateRowsInTableLabel() {
        int rowCount = tableView.getItems().size();
        rowsInTableLabel.setText("Rows returned: " + rowCount);
    }

    private void handleCustomerSelection() {
        Customer selectedCustomer = tableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            // Populate purchaseListView with formatted product information
            ObservableList<String> purchaseItems = FXCollections.observableArrayList();
            for (Product product : selectedCustomer.getPurchases()) {
                String formattedProduct = product.getName() + "-$" + String.format("%.2f", product.getSalePrice());
                purchaseItems.add(formattedProduct);
            }
            purchaseListView.setItems(purchaseItems);

            // Update labels with purchase summary
            double totalRegularPrice = selectedCustomer.getPurchases().stream()
                    .mapToDouble(Product::getRegularPrice)
                    .sum();
            double totalSalePrice = selectedCustomer.getPurchases().stream()
                    .mapToDouble(Product::getSalePrice)
                    .sum();
            double totalSavings = totalRegularPrice - totalSalePrice;

            msrpLabel.setText(String.format("$%.2f", totalRegularPrice));
            saleLabel.setText(String.format("$%.2f", totalSalePrice));
            savingsLabel.setText(String.format("$%.2f", totalSavings));
        }
    }

    @FXML
    private void top10Customers() {
        if (customerList != null) {
            List<Customer> top10 = customerList.stream()
                    .sorted((c1, c2) -> Double.compare(c2.getTotalPurchases(), c1.getTotalPurchases()))
                    .limit(10)
                    .collect(Collectors.toList());

            tableView.setItems(FXCollections.observableArrayList(top10));
            updateRowsInTableLabel();
        }
    }

    @FXML
    private void customersSavedOver5() {
        if (customerList != null) {
            List<Customer> filteredCustomers = customerList.stream()
                    .filter(Customer::savedFiveOrMore)
                    .collect(Collectors.toList());

            tableView.setItems(FXCollections.observableArrayList(filteredCustomers));
            updateRowsInTableLabel();
        }
    }

    @FXML
    private void loadAllCustomers() {
        String FilePath = "/com/example/comp1011finalexamsummer2024/Data/customers.json";
        List<Customer> customers = ParseJsonCustomer.loadCustomerData(FilePath);

        if (customers != null) {
            customerList = FXCollections.observableArrayList(customers);
            tableView.setItems(customerList);
            updateRowsInTableLabel();
        } else {
            System.err.println("No customers loaded from JSON file.");
        }
    }
}


