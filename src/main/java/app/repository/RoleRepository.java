package app.repository;

import app.model.Role;
import java.util.List;

    public interface RoleRepository {
        Role getRoleByName(String name);

        Role getRoleById(Long id);

        List<Role> allRoles();

        Role getDefaultRole();
    }

