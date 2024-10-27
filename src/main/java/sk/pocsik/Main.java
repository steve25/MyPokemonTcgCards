package sk.pocsik;

import sk.pocsik.services.AuthService;
import sk.pocsik.views.authenticate.LoginView;

public class Main {
    public static void main(String[] args) {
//        SystemUtils.setScaleFactor();
        AuthService authService = new AuthService();

        new LoginView(authService);
    }
}