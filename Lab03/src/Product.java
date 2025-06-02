import javax.swing.*;

public class Product {
    private final String name;
    private final String brand;
    private final String description;
    private final double price;
    private final ImageIcon image;

    public Product(String name, String brand, String description, double price, ImageIcon image) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public ImageIcon getImageIcon(int width, int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }
    public ImageIcon getOriginalImage() {
        return image;
    }
}
