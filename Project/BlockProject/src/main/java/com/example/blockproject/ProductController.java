package com.example.blockproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.math.BigDecimal;

public class ProductController {
    @FXML private TextField txtProductId;
    @FXML private TextField txtProductName;
    @FXML private TextArea txtDescription;
    @FXML private Spinner<Integer> spnQuantity;
    @FXML private TextField txtPrice;
    //Buttons
    @FXML private Button btnSave;
    @FXML private Button btnDelete;
    @FXML private Button btnClear;
    @FXML private TableView<Product> tblProducts;
    @FXML private TableColumn<Product, Integer> colId;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colDescription;
    @FXML private TableColumn<Product, Integer> colQuantity;
    @FXML private TableColumn<Product, BigDecimal> colPrice;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Im-the-1&^_^";

    @FXML
    public void initialize() {
        setupSpinner();
        setupTableColumns();
        loadProducts();

        // Add listener to table selection
        tblProducts.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        showProductDetails(newSelection);
                    }
                }
        );
    }

    private void setupSpinner() {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
        spnQuantity.setValueFactory(valueFactory);
        spnQuantity.setEditable(true);
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void handleSaveProduct() {
        if (!validateInputs()) {
            return;
        }

        String productId = txtProductId.getText().trim();
        String name = txtProductName.getText().trim();
        String description = txtDescription.getText().trim();
        int quantity = spnQuantity.getValue();
        BigDecimal price;
        try {
            price = new BigDecimal(txtPrice.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Invalid price format", Alert.AlertType.ERROR);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (productId.isEmpty()) {
                // Insert new product
                String sql = "INSERT INTO products (name, description, quantity, price) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, description);
                    pstmt.setInt(3, quantity);
                    pstmt.setBigDecimal(4, price);

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        ResultSet rs = pstmt.getGeneratedKeys();
                        if (rs.next()) {
                            txtProductId.setText(String.valueOf(rs.getInt(1)));
                        }
                        showAlert("Product added successfully!", Alert.AlertType.INFORMATION);
                    }
                }
            } else {
                // Update existing product
                String sql = "UPDATE products SET name = ?, description = ?, quantity = ?, price = ? WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setString(2, description);
                    pstmt.setInt(3, quantity);
                    pstmt.setBigDecimal(4, price);
                    pstmt.setInt(5, Integer.parseInt(productId));

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        showAlert("Product updated successfully!", Alert.AlertType.INFORMATION);
                    }
                }
            }
            loadProducts();
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleDeleteProduct() {
        String productId = txtProductId.getText().trim();
        if (productId.isEmpty()) {
            showAlert("Please select a product to delete", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setContentText("Are you sure you want to delete this product?");

        if (confirmation.showAndWait().get() == ButtonType.OK) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // First check if there are any transactions for this product (In this part we should ask the professor if we must let the product here or not)
                String checkSql = "SELECT COUNT(*) FROM transactions WHERE product_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
                    pstmt.setInt(1, Integer.parseInt(productId));
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        showAlert("Cannot delete product with existing transactions", Alert.AlertType.ERROR);
                        return;
                    }
                }

                // If no transactions exist, proceed with deletion
                String sql = "DELETE FROM products WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(productId));
                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        handleClearFields();
                        showAlert("Product deleted successfully!", Alert.AlertType.INFORMATION);
                        loadProducts();
                    }
                }
            } catch (SQLException e) {
                showAlert("Database error: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    private void loadProducts() {
        String sql = "SELECT * FROM products ORDER BY name";
        ObservableList<Product> products = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price")
                ));
            }
            tblProducts.setItems(products);

        } catch (SQLException e) {
            showAlert("Error loading products: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showProductDetails(Product product) {
        txtProductId.setText(String.valueOf(product.getId()));
        txtProductName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        spnQuantity.getValueFactory().setValue(product.getQuantity());
        txtPrice.setText(product.getPrice().toString());
    }

    @FXML
    private void handleClearFields() {
        txtProductId.clear();
        txtProductName.clear();
        txtDescription.clear();
        spnQuantity.getValueFactory().setValue(0);
        txtPrice.clear();
        tblProducts.getSelectionModel().clearSelection();
    }

    private boolean validateInputs() {
        if (txtProductName.getText().trim().isEmpty()) {
            showAlert("Product name is required", Alert.AlertType.WARNING);
            return false;
        }

        try {
            if (!txtPrice.getText().trim().isEmpty()) {
                BigDecimal price = new BigDecimal(txtPrice.getText().trim());
                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    showAlert("Price must be greater than 0", Alert.AlertType.WARNING);
                    return false;
                }
            } else {
                showAlert("Price is required", Alert.AlertType.WARNING);
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid price format", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertType.toString());
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToDashboard() {
        SceneManager.switchScene("Dashboard.fxml");
    }
}