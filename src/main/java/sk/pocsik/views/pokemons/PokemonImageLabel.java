package sk.pocsik.views.pokemons;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PokemonImageLabel extends JLabel {
    private final BufferedImage image;
    private final int targetWidth;
    private final int targetHeight;


    public PokemonImageLabel(BufferedImage img, int width, int height) {
        this.image = img;
        this.targetWidth = width;
        this.targetHeight = height;
        setPreferredSize(new Dimension(targetWidth, targetHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the scaled image
            g2d.drawImage(image.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH),
                    0, 0, this);
        }
    }
}
