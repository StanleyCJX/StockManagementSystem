import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SMSCLI {
    private static ArrayList<Product> products = new ArrayList<>();
    private static int userIDCounter = 69;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Stock Management System (SMS)");
        System.out.println("Current Date & Time\t: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Group Members\t\t: Au Chi Le");
        System.out.println("\t\t\t: Lim Jin Zhao");
        System.out.println("\t\t\t: Stanley Chew Jun Xian");
        System.out.println("\t\t\t: Tee Chi Sheng");
        
        System.out.print("Enter your full name: ");
        String userName = scanner.nextLine();
        int userID = userIDCounter++;

        while (true) {
            try {
                System.out.print("Enter the number of products to add (or 0 to exit): ");
                int numProducts = Integer.parseInt(scanner.nextLine());
                
                if (numProducts < 0) {
                    System.out.println("Number of products cannot be negative. Please try again.\n");
                    continue;
                }
                
                if (numProducts == 0) break;

                for (int i = 0; i < numProducts; i++) {
                    System.out.println("\nSelect a product to add:");
                    System.out.println("1. Refrigerator");
                    System.out.println("2. TV");
                    System.out.println("3. Washing Machine");
                    System.out.print("Enter choice: ");
                    
                    int productChoice;
                    try {
                        productChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number (1-3).\n");
                        i--;
                        continue;
                    }

                    try {
                        if (productChoice == 1) {
                            addRefrigerator(scanner);
                        } else if (productChoice == 2) {
                            addTV(scanner);
                        } else if (productChoice == 3) {
                            addWashingMachine(scanner);
                        } else {
                            System.out.println("Invalid choice. Please enter a number between 1-3.\n");
                            i--;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage() + " Please try again.\n");
                        i--;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
            }
        }

        while (true) {
            System.out.println("\nMenu Options:");
            System.out.println("1. View Products");
            System.out.println("2. Add Stock");
            System.out.println("3. Deduct Stock");
            System.out.println("4. Discontinue Product");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            
            int menuChoice;
            try {
                menuChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-5).\n");
                continue;
            }

            try {
                if (menuChoice == 1) {
                    viewProducts();
                } else if (menuChoice == 2) {
                    modifyStock(scanner, true);
                } else if (menuChoice == 3) {
                    modifyStock(scanner, false);
                } else if (menuChoice == 4) {
                    discontinueProduct(scanner);
                } else if (menuChoice == 5) {
                    System.out.println("User ID: " + userID + " \t Name: " + userName);
                    System.out.println("Thank you for using the system");
                    break;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1-5.\n");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void addRefrigerator(Scanner scanner) {
        System.out.println("\n=== Add New Refrigerator ===");
        
        System.out.print("Enter Item Number: ");
        String itemNumber = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Quantity Available: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Price (RM): ");
        double price = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Enter Door Design: ");
        String doorDesign = scanner.nextLine();
        
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        
        System.out.print("Enter Capacity (Litres): ");
        double capacity = Double.parseDouble(scanner.nextLine());

        // Create and add the new refrigerator
        Refrigerator newFridge = new Refrigerator(itemNumber, name, quantity, price, doorDesign, color, capacity);
        products.add(newFridge);
        
        System.out.println("\nRefrigerator added successfully!");
        System.out.println("============================");
    }

    private static void addTV(Scanner scanner) {
        System.out.println("\n=== Add New TV ===");
        
        System.out.print("Enter Item Number: ");
        String itemNumber = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Quantity Available: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Price (RM): ");
        double price = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Enter Screen Type: ");
        String screenType = scanner.nextLine();
        
        System.out.print("Enter Resolution: ");
        String resolution = scanner.nextLine();
        
        System.out.print("Enter Display Size (inches): ");
        double displaySize = Double.parseDouble(scanner.nextLine());

        // Create and add the new TV
        TV newTV = new TV(itemNumber, name, quantity, price, screenType, resolution, displaySize);
        products.add(newTV);
        
        System.out.println("\nTV added successfully!");
        System.out.println("===================");
    }

    private static void addWashingMachine(Scanner scanner) {
        System.out.println("\n=== Add New Washing Machine ===");
        
        System.out.print("Enter Item Number: ");
        String itemNumber = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Quantity Available: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Price (RM): ");
        double price = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Enter Load Capacity (kg): ");
        double loadCapacity = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Enter Wash Program: ");
        String washProgram = scanner.nextLine();
        
        System.out.print("Enter Energy Rating: ");
        String energyRating = scanner.nextLine();

        // Create and add the new washing machine
        WashingMachine newWM = new WashingMachine(itemNumber, name, quantity, price, loadCapacity, washProgram, energyRating);
        products.add(newWM);
        
        System.out.println("\nWashing Machine added successfully!");
        System.out.println("=================================");
    }

    private static void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.\n");
        } else {
            for (Product p : products) {
                System.out.println(p);
                System.out.println();
            }
        }
    }

    private static void modifyStock(Scanner scanner, boolean isAdding) {
        System.out.print("Enter Item Number: ");
        String itemNumber = scanner.nextLine();
        
        boolean found = false;
        for (Product p : products) {
            if (p.getItemNumber().equals(itemNumber)) {
                found = true;
                System.out.print("Enter Quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                
                try {
                    if (isAdding) {
                        p.addStock(quantity);
                        System.out.println("Stock added successfully.\n");
                    } else {
                        p.deductStock(quantity);
                        System.out.println("Stock deducted successfully.\n");
                    }
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
        }
        
        if (!found) {
            System.out.println("Product not found.\n");
        }
    }

    private static void discontinueProduct(Scanner scanner) {
        System.out.print("Enter Item Number: ");
        String itemNumber = scanner.nextLine();
        
        boolean found = false;
        for (Product p : products) {
            if (p.getItemNumber().equals(itemNumber)) {
                p.setStatus(false);
                System.out.println("Product discontinued.\n");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Product not found.\n");
        }
    }
}