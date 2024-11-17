package sk.pocsik.views.main;

import sk.pocsik.services.AuthService;
import sk.pocsik.services.PokemonService;
import sk.pocsik.utils.UserInfo;
import sk.pocsik.views.add.AddView;
import sk.pocsik.views.authenticate.LoginView;

import javax.swing.*;

public class UserPanel extends JPanel {
    private final AuthService authService;
    private final MainView mainView;
    private final PokemonService pokemonService;


    public UserPanel(MainView mainView, AuthService authService, PokemonService pokemonService) {
        this.mainView = mainView;
        this.authService = authService;
        this.pokemonService = pokemonService;

        this.init();
    }

    private void init() {
        JLabel userNameLabel = new JLabel("Welcome: " + UserInfo.getUser().getUsername());
        JButton logoutButton = new JButton("Logout");
        JButton searchButton = new JButton("Add cards");

        searchButton.addActionListener(e -> {
            new AddView(pokemonService, mainView);
            mainView.dispose();
        });

        logoutButton.addActionListener(e -> {
            authService.logout();
            new LoginView(authService);
            mainView.dispose();
        });

        this.add(searchButton);
        this.add(userNameLabel);
        this.add(logoutButton);
    }


}
