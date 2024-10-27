package sk.pocsik.views.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class PokemonCardPanel extends JPanel {


    public PokemonCardPanel(PokemonCard pokemonCard) {
        this.setBackground(new Color((int) (Math.random() * 0x1000000)));

        System.out.println(pokemonCard.getImageUrl());
        try {
            URL url = URI.create(pokemonCard.getImageUrl()).toURL();
            BufferedImage image = ImageIO.read(url);
            HighQualityImageLabel imageLabel = new HighQualityImageLabel(image, 245, 342);
            this.add(imageLabel);
        } catch (IOException e) {
            System.err.println(e);
            JLabel label = new JLabel(pokemonCard.getName());
            label.setPreferredSize(new Dimension(245, 342));
            this.add(label);
        }
    }


}
