package app.repository;

import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    public void update(User updatedUser) {
          entityManager.merge(updatedUser);
          entityManager.flush();
    }

    public void delete(int id) {
        User user = show(id);
        entityManager.remove(user);
        entityManager.flush();
    }
}
