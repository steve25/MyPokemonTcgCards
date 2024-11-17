package sk.pocsik.daos;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sk.pocsik.models.Pokemon;
import sk.pocsik.models.User;

import java.util.List;

public class PokemonDao extends CustomDao {
    public List<Pokemon> getPokemonsByUser(User user) {
        try (Session session = this.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Pokemon> criteriaQuery = criteriaBuilder.createQuery(Pokemon.class);
            Root<Pokemon> pokemonRoot = criteriaQuery.from(Pokemon.class);

            Predicate userPredicate = criteriaBuilder.equal(pokemonRoot.get("user"), user);

            criteriaQuery.select(pokemonRoot).where(userPredicate);

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    public Pokemon getPokemonByIdAndUser(String apiId, User user) {
        try (Session session = this.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Pokemon> criteriaQuery = criteriaBuilder.createQuery(Pokemon.class);
            Root<Pokemon> pokemonRoot = criteriaQuery.from(Pokemon.class);

            Predicate idPredicate = criteriaBuilder.equal(pokemonRoot.get("apiId"), apiId);
            Predicate userPredicate = criteriaBuilder.equal(pokemonRoot.get("user"), user);

            criteriaQuery.select(pokemonRoot).where(idPredicate, userPredicate);

            return session.createQuery(criteriaQuery).uniqueResult();
        }
    }

    public void save(Pokemon pokemon) {
        try (Session session = this.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(pokemon);
            session.merge(pokemon);
            transaction.commit();
        }
    }

    public void remove (Pokemon pokemon) {
        try (Session session = this.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(pokemon);
            transaction.commit();
        }
    }
}
