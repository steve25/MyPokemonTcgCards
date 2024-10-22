package sk.pocsik;

import sk.pocsik.views.authenticate.LoginView;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        float dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        float scaleFactor = dpi / 96.0f;
        System.setProperty("sun.java2d.uiScale", Float.toString(scaleFactor));

        new LoginView();
    }
}