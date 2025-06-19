public class TV extends Product {
    private String screenType;
    private String resolution;
    private double displaySize;

    public TV(String itemNumber, String name, int quantityAvailable, double price,
              String screenType, String resolution, double displaySize) {
        super(itemNumber, name, quantityAvailable, price);
        setScreenType(screenType);
        setResolution(resolution);
        setDisplaySize(displaySize);
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        if (screenType == null || screenType.trim().isEmpty()) {
            throw new IllegalArgumentException("Screen type cannot be empty");
        }
        this.screenType = screenType;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        if (resolution == null || resolution.trim().isEmpty()) {
            throw new IllegalArgumentException("Resolution cannot be empty");
        }
        this.resolution = resolution;
    }

    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        if (displaySize <= 0) {
            throw new IllegalArgumentException("Display size must be positive");
        }
        this.displaySize = displaySize;
    }

    @Override
    public String toString() {
        String statusString = getStatus() ? "Active" : "Discontinued";
        return String.format(
                "Item number\t\t: %s \n"
                + "Product name\t\t: %s \n"
                + "Screen type\t\t: %s \n"
                + "Resolution\t\t: %s \n"
                + "Display size\t\t: %.1f \n"
                + "Quantity available\t: %d \n"
                + "Price (RM)\t\t: %.2f \n"
                + "Inventory value (RM)\t: %.2f \n"
                + "Product status\t\t: %s",
                getItemNumber(),
                getName(),
                getScreenType(),
                getResolution(),
                getDisplaySize(),
                getQuantityAvailable(),
                getPrice(),
                getInventoryValue(),
                statusString);
    }
}