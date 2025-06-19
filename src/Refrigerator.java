public class Refrigerator extends Product {
    private String doorDesign;
    private String color;
    private double capacity;

    public Refrigerator(String itemNumber, String name, int quantityAvailable, double price,
                        String doorDesign, String color, double capacity) {
        super(itemNumber, name, quantityAvailable, price);
        setDoorDesign(doorDesign);
        setColor(color);
        setCapacity(capacity);
    }

    public String getDoorDesign() {
        return doorDesign;
    }

    public void setDoorDesign(String doorDesign) {
        if (doorDesign == null || doorDesign.trim().isEmpty()) {
            throw new IllegalArgumentException("Door design cannot be empty");
        }
        this.doorDesign = doorDesign;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be empty");
        }
        this.color = color;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        String statusString = getStatus() ? "Active" : "Discontinued";
        return String.format(
                "Item number\t\t: %s \n"
                + "Product name\t\t: %s \n"
                + "Door design\t\t: %s \n"
                + "Color\t\t\t: %s \n"
                + "Capacity (in Litres)\t: %.2f \n"
                + "Quantity available\t: %d \n"
                + "Price (RM)\t\t: %.2f \n"
                + "Inventory value (RM)\t: %.2f \n"
                + "Product status\t\t: %s",
                getItemNumber(),
                getName(),
                getDoorDesign(),
                getColor(),
                getCapacity(),
                getQuantityAvailable(),
                getPrice(),
                getInventoryValue(),
                statusString);
    }
}