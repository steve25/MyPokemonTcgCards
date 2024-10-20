package sk.pocsik.view.addcardview;

import sk.pocsik.models.PokemonCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class PokemonCardView extends JPanel {
    public PokemonCardView(PokemonCard pokemonCard) {
        this.setPreferredSize(new Dimension(150, 200));
        this.setBackground(new Color((int) (Math.random() * 0x1000000)));
        JLabel nameLabel = new JLabel();
        try {
            URL url = new URL(pokemonCard.getImageUrl());
            Image image = ImageIO.read(url);
            Image resizedImage = image.getScaledInstance(150, 200, Image.SCALE_DEFAULT);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            nameLabel.setIcon(imageIcon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.add(nameLabel);
    }
}
