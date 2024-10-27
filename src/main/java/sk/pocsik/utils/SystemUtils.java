package sk.pocsik.utils;

import lombok.Getter;

import java.awt.*;

public abstract class SystemUtils {
    private static double dpi;
    private static double scaleFactor;
    @Getter
    private static double cropFactor;

    public static void setScaleFactor() {
        dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        scaleFactor = dpi / 96.0f;
        cropFactor = 1.0 / scaleFactor;

//        System.setProperty("sun.java2d.uiScale", Double.toString(scaleFactor));
    }

}
