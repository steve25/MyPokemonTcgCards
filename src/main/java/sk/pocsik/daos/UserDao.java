package sk.pocsik.daos;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sk.pocsik.models.User;

import java.util.List;

public class UserDao extends CustomDao {


    public void save(User user) {
        try (Session session = this.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            session.merge(user);
            transaction.commit();
        }
    }

    public List<User> getAll() {
        try (Session session = this.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);

            criteriaQuery.select(userRoot);

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public User findUserByName(String username) {
        try (Session session = this.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot).where(criteriaBuilder.and
                    (criteriaBuilder.equal(userRoot.get("username"), username))
            );

            List<User> users = session.createQuery(criteriaQuery).getResultList();

            if (users.isEmpty()) {
                return null;
            }

            return users.getFirst();
        }
    }
}
