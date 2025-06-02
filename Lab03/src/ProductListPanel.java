import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ProductListPanel extends JPanel {
    private final ProductPanel productPanel;
    private RoundedPanel selectedCard = null;

    public ProductListPanel(List<Product> products, ProductPanel productPanel) {
        this.productPanel = productPanel;
        setLayout(new GridLayout(2, 3, 15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20));

        boolean first = true;
        for (Product product : products) {
            RoundedPanel card = createProductCard(product);
            add(card);

            if (first) {
                productPanel.setProduct(product);
                card.setRoundedBorder(new Color(100, 149, 237), 2); // pastel blue
                selectedCard = card;
                first = false;
            }
        }
    }

    private RoundedPanel createProductCard(Product product) {
        RoundedPanel card = new RoundedPanel(20);
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(new Color(245, 245, 245));

        // Cắt bớt tên và mô tả nếu quá dài
        JLabel nameLabel = new JLabel(ellipsis(product.getName(), 26));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel descLabel = new JLabel(ellipsis(product.getDescription(), 30));
        descLabel.setFont(new Font("Arial", Font.BOLD, 14));
        descLabel.setForeground(Color.GRAY);

        JLabel imgLabel = new JLabel(product.getImageIcon(240, 200));
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel brandLabel = new JLabel(product.getBrand());
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        brandLabel.setHorizontalAlignment(SwingConstants.LEFT);
        brandLabel.setVerticalAlignment(SwingConstants.TOP); // Căn chữ lên trên

        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 25));
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setVerticalAlignment(SwingConstants.TOP); // Căn chữ lên trên

// Gộp lại trong một panel để dễ canh chỉnh
        JPanel brandPricePanel = new JPanel(new BorderLayout());
        brandPricePanel.setOpaque(false);
        brandPricePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        brandPricePanel.add(brandLabel, BorderLayout.WEST);
        brandPricePanel.add(priceLabel, BorderLayout.EAST);


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textPanel.add(nameLabel);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(descLabel);



        card.add(textPanel, BorderLayout.NORTH);
        card.add(imgLabel, BorderLayout.CENTER);
        card.add(brandPricePanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                productPanel.setProduct(product);

                if (selectedCard != null) {
                    selectedCard.clearRoundedBorder();
                }

                card.setRoundedBorder(new Color(100, 149, 237), 2);
                selectedCard = card;
            }
        });

        return card;
    }

    // Hàm cắt chuỗi nếu vượt quá độ dài cho phép
    private String ellipsis(String text, int maxChars) {
        return text.length() <= maxChars ? text : text.substring(0, maxChars - 3) + "...";
    }
}
