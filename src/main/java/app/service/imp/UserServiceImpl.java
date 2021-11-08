package app.service.imp;

import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.model.User;
import app.repository.UserRepo;
import app.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public void UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //@Transactional("MySQLTransactionManager")
    @PostConstruct
    public void fillDataBase() {
        User user1  = new User("Alex", "root", "Alexey", "Zotov", "zotov@mail.ru",  Stream.of(new Role("ROLE_ADMIN")).collect(Collectors.toSet()));
        User user2 = new User("Ivan", "root", "Ivan", "Petrov", "petrov@mail.ru", Stream.of ((new Role("ROLE_ADMIN")), (new Role("ROLE_USER"))).collect(Collectors.toSet()));
        userRepo.addUser(user1);
        userRepo.addUser(user2);
    }

    @Override
    public void addUser(User user) {
        userRepo.addUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteUser(id);
    }

    @Override
    public void editUser(User user) {
        userRepo.editUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.getUserByUsername(username);
    }
}
