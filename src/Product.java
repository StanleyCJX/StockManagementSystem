public abstract class Product {
    private String itemNumber;
    private String name;
    private int quantityAvailable;
    private double price;
    private boolean status;

    public Product() {
        this.status = true;
    }

    public Product(String itemNumber, String name, int quantityAvailable, double price) {
        setItemNumber(itemNumber);
        setName(name);
        setQuantityAvailable(quantityAvailable);
        setPrice(price);
        this.status = true;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        if (itemNumber == null || itemNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Item number cannot be empty");
        }
        this.itemNumber = itemNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = name;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        if (quantityAvailable < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantityAvailable = quantityAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getInventoryValue() {
        return price * quantityAvailable;
    }

    public void addStock(int quantity) {
        if (!status) {
            throw new IllegalStateException("Cannot add stock to a discontinued product");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to add must be positive");
        }
        quantityAvailable += quantity;
    }

    public void deductStock(int quantity) {
        if (!status) {
            throw new IllegalStateException("Cannot deduct stock from a discontinued product");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to deduct must be positive");
        }
        if (quantity > quantityAvailable) {
            throw new IllegalArgumentException("Insufficient stock available");
        }
        quantityAvailable -= quantity;
    }

    @Override
    public String toString() {
        String statusString = getStatus() ? "Active" : "Discontinued";
        return String.format(
                "Item number\t\t: %s \n"
                + "Product name\t\t: %s \n"
                + "Quantity available\t: %d \n"
                + "Price (RM)\t\t: %.2f \n"
                + "Inventory value (RM)\t: %.2f \n"
                + "Product status\t\t: %s",
                getItemNumber(),
                getName(),
                getQuantityAvailable(),
                getPrice(),
                getInventoryValue(),
                statusString);
    }
}