import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private final int cornerRadius;
    private Color borderColor = null;
    private int borderWidth = 0;

    public RoundedPanel(int radius) {
        this.cornerRadius = radius;
        setOpaque(false);
    }

    public void setRoundedBorder(Color color, int width) {
        this.borderColor = color;
        this.borderWidth = width;
        repaint();
    }

    public void clearRoundedBorder() {
        this.borderColor = null;
        this.borderWidth = 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        graphics.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderColor != null && borderWidth > 0) {
            Graphics2D graphics = (Graphics2D) g.create();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(borderColor);
            graphics.setStroke(new BasicStroke(borderWidth));
            graphics.drawRoundRect(borderWidth / 2, borderWidth / 2,
                    getWidth() - borderWidth, getHeight() - borderWidth,
                    cornerRadius, cornerRadius);
            graphics.dispose();
        }
    }
}