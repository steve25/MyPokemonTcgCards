package sk.pocsik;

import sk.pocsik.utils.SystemUtils;
import sk.pocsik.views.authenticate.LoginView;

public class Main {
    public static void main(String[] args) {
        SystemUtils.setScaleFactor();

        new LoginView();
    }
}