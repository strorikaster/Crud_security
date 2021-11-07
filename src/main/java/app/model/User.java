package app.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "users")
    public class User implements UserDetails {

    String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotEmpty(message = "Empty values not allowed")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 character")
    private String username;
    @Column
    @NotEmpty(message = "Empty values not allowed")
    @Size(min = 4, max = 8, message = "Password should be between 4 and 8 character")
    private String password;
    @Column
    @NotEmpty(message = "Empty values not allowed")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 character")
    private String name;
    @Column
    @NotEmpty(message = "Empty values not allowed")
    private String surName;
    @Column
    @NotEmpty(message = "Empty values not allowed")
    @Email(message = "Not correct email entered")
    private String email;
    @Column
    @Min(value = 0, message = "Age must be greater than 0")
    private Integer age;
    @OneToMany
    @JoinColumn(name = "user_id")
            //(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    //@ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet();


    public User() {
    }

    public User(int id, String name, String surName, String email, Integer age, String username, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        //this.roles = roles;
        roles.add(new Role(id, role));
    }

    public boolean hasRole(int roleId) {
        if (null == roles|| 0 == roles.size()) {
            return false;
        }
        Optional<Role> findRole = roles.stream().filter(role -> roleId == role.getId()).findFirst();
        return findRole.isPresent();
    }

    public boolean hasRole(String roleName) {
        if (null == roles|| 0 == roles.size()) {
            return false;
        }
        Optional<Role> findRole = roles.stream().filter(role -> roleName.equals(role.getRole())).findFirst();
        return findRole.isPresent();
    }
}
