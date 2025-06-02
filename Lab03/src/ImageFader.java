import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFader {
    private final JLabel label;
    private final ImageIcon newImage;

    public ImageFader(JLabel label, ImageIcon newImage) {
        this.label = label;
        this.newImage = newImage;
    }

    public void start() {
        Timer timer = new Timer(30, null);
        final float[] alpha = {1.0f};

        timer.addActionListener(e -> {
            alpha[0] -= 0.1f;
            if (alpha[0] <= 0) {
                label.setIcon(newImage);
                timer.stop();
                fadeIn();
            } else {
                label.setIcon(createTransparentIcon(newImage, alpha[0]));
            }
        });
        timer.start();
    }

    private void fadeIn() {
        Timer timer = new Timer(50, null);
        final float[] alpha = {0.0f};

        timer.addActionListener(e -> {
            alpha[0] += 0.1f;
            if (alpha[0] >= 1.0f) {
                label.setIcon(newImage);
                timer.stop();
            } else {
                label.setIcon(createTransparentIcon(newImage, alpha[0]));
            }
        });
        timer.start();
    }

    private ImageIcon createTransparentIcon(ImageIcon icon, float alpha) {
        Image img = icon.getImage();
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage buffered = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = buffered.createGraphics();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.drawImage(img, 0, 0, null);
        g2.dispose();

        return new ImageIcon(buffered);
    }
}
