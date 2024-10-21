package sk.pocsik.views.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class PokemonCardView extends JPanel {


    public PokemonCardView(PokemonCard pokemonCard) {
        this.setBackground(new Color((int) (Math.random() * 0x1000000)));

        System.out.println(pokemonCard.getImageUrl());
        try {
            URL url = URI.create(pokemonCard.getImageUrl()).toURL();
            BufferedImage image = ImageIO.read(url);

            // Create a HighQualityImageLabel to display the image
            HighQualityImageLabel imageLabel = new HighQualityImageLabel(image, 200, 350);
            this.add(imageLabel); // Add image label to the panel
        } catch (IOException e) {
            System.err.println(e);

            JLabel label = new JLabel(pokemonCard.getName());
            label.setPreferredSize(new Dimension(200, 350));
            this.add(label); // Add image label to the panel

        }
    }


}
