import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel {
    private final JLabel imageLabel;
    private final JLabel nameLabel;
    private final JLabel priceLabel;
    private final JLabel brandLabel;
    private final JTextArea descriptionArea;

    public ProductPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350, 600));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20));

        // Hình ảnh
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(imageLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        // Đường kẻ ngang
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        add(separator);
        add(Box.createRigidArea(new Dimension(0, 10)));

        // Tên sản phẩm
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(nameLabel);

        // Giá
        priceLabel = new JLabel();
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(priceLabel);

        // Hãng
        brandLabel = new JLabel();
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(brandLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        // Mô tả
        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setFont(new Font("Arial", Font.BOLD, 14));
        descriptionArea.setForeground(Color.GRAY);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(descriptionArea);
    }

    public void setProduct(Product product) {
        imageLabel.setIcon(product.getImageIcon(300, 240));
        nameLabel.setText(product.getName());
        priceLabel.setText("$" + String.format("%.2f", product.getPrice()));
        brandLabel.setText(product.getBrand());
        descriptionArea.setText(product.getDescription());
    }
}
