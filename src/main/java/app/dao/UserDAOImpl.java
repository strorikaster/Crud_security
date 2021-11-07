package app.dao;

import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> index() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Transactional
    public void update(User updatedUser) {
          entityManager.merge(updatedUser);
          entityManager.flush();
    }

    @Transactional
    public void delete(int id) {
        User user = show(id);
        entityManager.remove(user);
        entityManager.flush();
    }
}
