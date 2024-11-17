package sk.pocsik.views.base;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public BaseFrame(String title, int sizeX, int sizeY, int exitAction) {
        this.setTitle(title);
        this.setSize(sizeX, sizeY);
        this.setDefaultCloseOperation(exitAction);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
