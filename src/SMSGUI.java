import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class SMSGUI extends Application {
    private static ArrayList<Product> products = new ArrayList<>();
    private String currentUserID;
    private String currentUserName;

    public static void main(String[] args) {
        Application.launch(SMSGUI.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        // User login dialog
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("User Login");
        nameDialog.setHeaderText("Welcome to SMS");
        nameDialog.setContentText("Please enter your full name:");

        Optional<String> result = nameDialog.showAndWait();
        result.ifPresent(name -> {
            currentUserName = name;
            currentUserID = generateUserID(name);
        });

        if (currentUserName == null) {
            currentUserName = "Guest";
            currentUserID = "guest";
        }

        primaryStage.setTitle("Stock Management System (SMS)");

        // Welcome message
        Label welcomeLabel = new Label("Welcome to the Stock Management System (SMS)");
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        Label dateLabel = new Label("Current Date & Time: " + 
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        Label membersLabel = new Label("Group Members:\nAu Chi Le\nLim Jin Zhao\nStanley Chew Jun Xian\nTee Chi Sheng");

        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(welcomeLabel, dateLabel, membersLabel);

        // Menu Buttons
        Button addProductButton = new Button("Add Product");
        addProductButton.setStyle("-fx-font-size: 14px; -fx-min-width: 120px;");
        
        Button manageStockButton = new Button("Manage Stock");
        manageStockButton.setStyle("-fx-font-size: 14px; -fx-min-width: 120px;");
        
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 14px; -fx-min-width: 120px;");
        exitButton.setOnAction(e -> {
            Alert exitAlert = new Alert(Alert.AlertType.INFORMATION);
            exitAlert.setTitle("Session Summary");
            exitAlert.setHeaderText("Thank you for using SMS");
            exitAlert.setContentText("User: " + currentUserName + "\nID: " + currentUserID);
            exitAlert.showAndWait().ifPresent(response -> primaryStage.close());
        });

        HBox menuLayout = new HBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(addProductButton, manageStockButton, exitButton);

        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(mainLayout, menuLayout);

        addProductButton.setOnAction(e -> openAddProductWindow());
        manageStockButton.setOnAction(e -> openStockManagementWindow());

        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String generateUserID(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "guest";
        }
        
        if (name.contains(" ")) {
            String[] names = name.split(" ");
            if (names.length >= 2) {
                return names[0].substring(0, 1).toUpperCase() + names[names.length - 1];
            }
        }
        return "guest";
    }

    private void openAddProductWindow() {
        Stage addProductStage = new Stage();
        addProductStage.setTitle("Add Product");

        Label productLabel = new Label("Select Product Type");
        productLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button addRefrigeratorButton = createProductButton("Add Refrigerator");
        Button addTVButton = createProductButton("Add TV");
        Button addWashingMachineButton = createProductButton("Add Washing Machine");

        addRefrigeratorButton.setOnAction(e -> addProductGUI("Refrigerator"));
        addTVButton.setOnAction(e -> addProductGUI("TV"));
        addWashingMachineButton.setOnAction(e -> addProductGUI("Washing Machine"));

        VBox addProductLayout = new VBox(20);
        addProductLayout.setAlignment(Pos.CENTER);
        addProductLayout.setPadding(new Insets(20));
        addProductLayout.getChildren().addAll(productLabel, addRefrigeratorButton, addTVButton, addWashingMachineButton);

        Scene addProductScene = new Scene(addProductLayout, 300, 300);
        addProductStage.setScene(addProductScene);
        addProductStage.show();
    }

    private Button createProductButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 12px; -fx-min-width: 150px;");
        return button;
    }

    private void addProductGUI(String productType) {
        Stage addProductStage = new Stage();
        addProductStage.setTitle("Add " + productType);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Common fields
        TextField itemNumberField = new TextField();
        TextField nameField = new TextField();
        TextField quantityField = new TextField();
        TextField priceField = new TextField();

        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quantityField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                priceField.setText(oldValue);
            }
        });

        grid.add(new Label("Item Number:"), 0, 0);
        grid.add(itemNumberField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Quantity:"), 0, 2);
        grid.add(quantityField, 1, 2);
        grid.add(new Label("Price (RM):"), 0, 3);
        grid.add(priceField, 1, 3);

        int row = 4;
        if (productType.equals("Refrigerator")) {
            TextField doorDesignField = new TextField();
            TextField colorField = new TextField();
            TextField capacityField = new TextField();

            capacityField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    capacityField.setText(oldValue);
                }
            });

            grid.add(new Label("Door Design:"), 0, row++);
            grid.add(doorDesignField, 1, row-1);
            grid.add(new Label("Color:"), 0, row++);
            grid.add(colorField, 1, row-1);
            grid.add(new Label("Capacity (Liters):"), 0, row++);
            grid.add(capacityField, 1, row-1);
        } else if (productType.equals("TV")) {
            TextField screenTypeField = new TextField();
            TextField resolutionField = new TextField();
            TextField displaySizeField = new TextField();

            displaySizeField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    displaySizeField.setText(oldValue);
                }
            });

            grid.add(new Label("Screen Type:"), 0, row++);
            grid.add(screenTypeField, 1, row-1);
            grid.add(new Label("Resolution:"), 0, row++);
            grid.add(resolutionField, 1, row-1);
            grid.add(new Label("Display Size (inches):"), 0, row++);
            grid.add(displaySizeField, 1, row-1);
        } else if (productType.equals("Washing Machine")) {
            TextField loadCapacityField = new TextField();
            TextField washProgramField = new TextField();
            TextField energyRatingField = new TextField();

            loadCapacityField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    loadCapacityField.setText(oldValue);
                }
            });

            grid.add(new Label("Load Capacity (kg):"), 0, row++);
            grid.add(loadCapacityField, 1, row-1);
            grid.add(new Label("Wash Program:"), 0, row++);
            grid.add(washProgramField, 1, row-1);
            grid.add(new Label("Energy Rating:"), 0, row++);
            grid.add(energyRatingField, 1, row-1);
        }

        Button submitButton = new Button("Add " + productType);
        submitButton.setStyle("-fx-font-size: 14px;");
        grid.add(submitButton, 1, row);

        submitButton.setOnAction(e -> {
            try {
                if (itemNumberField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Item number is required");
                }
                if (nameField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Product name is required");
                }
                if (quantityField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Quantity is required");
                }
                if (priceField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Price is required");
                }

                String itemNumber = itemNumberField.getText();
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (productType.equals("Refrigerator")) {
                    TextField doorDesignField = (TextField) grid.getChildren().get(9);
                    TextField colorField = (TextField) grid.getChildren().get(11);
                    TextField capacityField = (TextField) grid.getChildren().get(13);
                    
                    products.add(new Refrigerator(itemNumber, name, quantity, price, 
                        doorDesignField.getText(), colorField.getText(), Double.parseDouble(capacityField.getText())));
                } else if (productType.equals("TV")) {
                    TextField screenTypeField = (TextField) grid.getChildren().get(9);
                    TextField resolutionField = (TextField) grid.getChildren().get(11);
                    TextField displaySizeField = (TextField) grid.getChildren().get(13);
                    
                    products.add(new TV(itemNumber, name, quantity, price, 
                        screenTypeField.getText(), resolutionField.getText(), Double.parseDouble(displaySizeField.getText())));
                } else if (productType.equals("Washing Machine")) {
                    TextField loadCapacityField = (TextField) grid.getChildren().get(9);
                    TextField washProgramField = (TextField) grid.getChildren().get(11);
                    TextField energyRatingField = (TextField) grid.getChildren().get(13);
                    
                    products.add(new WashingMachine(itemNumber, name, quantity, price, 
                        Double.parseDouble(loadCapacityField.getText()), washProgramField.getText(), energyRatingField.getText()));
                }

                showAlert(Alert.AlertType.INFORMATION, "Success", productType + " added successfully!");
                addProductStage.close();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });

        Scene scene = new Scene(grid);
        addProductStage.setScene(scene);
        addProductStage.show();
    }

    private void openStockManagementWindow() {
        Stage stockManagementStage = new Stage();
        stockManagementStage.setTitle("Stock Management");

        Label titleLabel = new Label("Stock Management Options");
        titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button viewProductsButton = createManagementButton("View Products");
        Button addStockButton = createManagementButton("Add Stock");
        Button deductStockButton = createManagementButton("Deduct Stock");
        Button discontinueProductButton = createManagementButton("Discontinue Product");

        viewProductsButton.setOnAction(e -> viewProductsGUI());
        addStockButton.setOnAction(e -> modifyStockGUI(true));
        deductStockButton.setOnAction(e -> modifyStockGUI(false));
        discontinueProductButton.setOnAction(e -> discontinueProductGUI());

        VBox stockManagementLayout = new VBox(15);
        stockManagementLayout.setAlignment(Pos.CENTER);
        stockManagementLayout.setPadding(new Insets(20));
        stockManagementLayout.getChildren().addAll(titleLabel, viewProductsButton, 
            addStockButton, deductStockButton, discontinueProductButton);

        Scene stockManagementScene = new Scene(stockManagementLayout, 300, 300);
        stockManagementStage.setScene(stockManagementScene);
        stockManagementStage.show();
    }

    private Button createManagementButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 12px; -fx-min-width: 150px;");
        return button;
    }

    private void viewProductsGUI() {
        Stage viewStage = new Stage();
        viewStage.setTitle("All Products");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-family: monospace;");

        if (products.isEmpty()) {
            textArea.setText("No products available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Product p : products) {
                sb.append(p.toString()).append("\n\n");
            }
            textArea.setText(sb.toString());
        }

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 600, 400);
        viewStage.setScene(scene);
        viewStage.show();
    }

    private void modifyStockGUI(boolean isAdding) {
        Stage modifyStage = new Stage();
        modifyStage.setTitle(isAdding ? "Add Stock" : "Deduct Stock");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        TextField itemNumberField = new TextField();
        TextField quantityField = new TextField();

        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quantityField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        grid.add(new Label("Item Number:"), 0, 0);
        grid.add(itemNumberField, 1, 0);
        grid.add(new Label("Quantity:"), 0, 1);
        grid.add(quantityField, 1, 1);

        Button submitButton = new Button(isAdding ? "Add Stock" : "Deduct Stock");
        submitButton.setStyle("-fx-font-size: 14px;");
        grid.add(submitButton, 1, 2);

        submitButton.setOnAction(e -> {
            try {
                String itemNumber = itemNumberField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                
                boolean found = false;
                for (Product p : products) {
                    if (p.getItemNumber().equals(itemNumber)) {
                        if (isAdding) {
                            p.addStock(quantity);
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Stock added successfully!");
                        } else {
                            p.deductStock(quantity);
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Stock deducted successfully!");
                        }
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Product not found!");
                } else {
                    modifyStage.close();
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });

        Scene scene = new Scene(grid);
        modifyStage.setScene(scene);
        modifyStage.show();
    }

    private void discontinueProductGUI() {
        Stage discontinueStage = new Stage();
        discontinueStage.setTitle("Discontinue Product");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        TextField itemNumberField = new TextField();
        grid.add(new Label("Item Number:"), 0, 0);
        grid.add(itemNumberField, 1, 0);

        Button submitButton = new Button("Discontinue");
        submitButton.setStyle("-fx-font-size: 14px;");
        grid.add(submitButton, 1, 1);

        submitButton.setOnAction(e -> {
            try {
                String itemNumber = itemNumberField.getText();
                boolean found = false;
                
                for (Product p : products) {
                    if (p.getItemNumber().equals(itemNumber)) {
                        p.setStatus(false);
                        found = true;
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Product discontinued successfully!");
                        break;
                    }
                }
                
                if (!found) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Product not found!");
                } else {
                    discontinueStage.close();
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });

        Scene scene = new Scene(grid);
        discontinueStage.setScene(scene);
        discontinueStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}