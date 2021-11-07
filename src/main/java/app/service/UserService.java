package app.service;

import app.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User show(int id);
    void save(User user);
    void update(User updatedUser);
    void delete(int id);
}
