package sk.pocsik.daos;

import org.hibernate.Session;
import sk.pocsik.utils.HibernateUtil;

public class CustomDao {

    Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
