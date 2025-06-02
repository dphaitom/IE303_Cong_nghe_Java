import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Shoe Store");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLayout(new BorderLayout());

            ProductPanel productPanel = new ProductPanel();

            List<Product> products = List.of(
                    new Product("4DFWD PULSE SHOES", "Adidas", "This product is excluded from all promotional discounts and offers.", 160, new ImageIcon("src/assets/img1.png")),
                    new Product("FORUM MID SHOES", "Adidas", "This product is excluded from all promotional discounts and offers.", 100, new ImageIcon("src/assets/img2.png")),
                    new Product("SUPERNOVA SHOES", "Adidas", "NMD City Stock 2", 150, new ImageIcon("src/assets/img3.png")),
                    new Product("NMD CITY 2", "Adidas", "This product is excluded from all promotional discounts and offers.", 120, new ImageIcon("src/assets/img4.png")),
                    new Product("4DFWD PULSE SHOES", "Adidas", "This product is excluded from all promotional discounts and offers.", 160, new ImageIcon("src/assets/img5.png")),
                    new Product("FORUM MID SHOES", "Adidas", "This product is excluded from all promotional discounts and offers.", 100, new ImageIcon("src/assets/img6.png"))
            );

            productPanel.setProduct(products.get(0)); // Hiển thị sản phẩm đầu tiên

            ProductListPanel listPanel = new ProductListPanel(products, productPanel);
            JScrollPane scrollPane = new JScrollPane(listPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBorder(null);

            frame.add(productPanel, BorderLayout.WEST);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
