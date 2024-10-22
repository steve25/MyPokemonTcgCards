package sk.pocsik.utils;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.config.ConfigurationException;
import sk.pocsik.models.User;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = build();

    private static SessionFactory build() {
        Dotenv dotenv = Dotenv.load();

        String dbUrl = dotenv.get("DB_URL");
        String dbUsername = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");
        System.out.println(dbUrl);

        try {
            Configuration conf = new Configuration();
            conf.addAnnotatedClass(User.class);
            conf.setProperty("hibernate.connection.url", dbUrl);
            conf.setProperty("hibernate.connection.username", dbUsername);
            conf.setProperty("hibernate.connection.password", dbPassword);

            return conf.buildSessionFactory();
        } catch (ConfigurationException e) {
            throw new ExceptionInInitializerError("Failed to configure: " + e.getMessage());
        }
    }
}
