public class WashingMachine extends Product {
    private double loadCapacity;
    private String washProgram;
    private String energyRating;

    public WashingMachine(String itemNumber, String name, int quantityAvailable, double price,
                         double loadCapacity, String washProgram, String energyRating) {
        super(itemNumber, name, quantityAvailable, price);
        setLoadCapacity(loadCapacity);
        setWashProgram(washProgram);
        setEnergyRating(energyRating);
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        if (loadCapacity <= 0) {
            throw new IllegalArgumentException("Load capacity must be positive");
        }
        this.loadCapacity = loadCapacity;
    }

    public String getWashProgram() {
        return washProgram;
    }

    public void setWashProgram(String washProgram) {
        if (washProgram == null || washProgram.trim().isEmpty()) {
            throw new IllegalArgumentException("Wash program cannot be empty");
        }
        this.washProgram = washProgram;
    }

    public String getEnergyRating() {
        return energyRating;
    }

    public void setEnergyRating(String energyRating) {
        if (energyRating == null || energyRating.trim().isEmpty()) {
            throw new IllegalArgumentException("Energy rating cannot be empty");
        }
        this.energyRating = energyRating;
    }

    @Override
    public String toString() {
        String statusString = getStatus() ? "Active" : "Discontinued";
        return String.format(
            "Item number\t\t: %s \n" +
            "Product name\t\t: %s \n" +
            "Load Capacity (kg)\t: %.2f \n" +
            "Wash Program\t\t: %s \n" +
            "Energy Rating\t\t: %s \n" +
            "Quantity available\t: %d \n" +
            "Price (RM)\t\t: %.2f \n" +
            "Inventory value (RM)\t: %.2f \n" +
            "Product status\t\t: %s",
            getItemNumber(),
            getName(),
            getLoadCapacity(),
            getWashProgram(),
            getEnergyRating(),
            getQuantityAvailable(),
            getPrice(),
            getInventoryValue(),
            statusString
        );
    }
}