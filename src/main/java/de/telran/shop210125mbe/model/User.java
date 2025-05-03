package de.telran.shop210125mbe.model;

import org.springframework.context.support.BeanDefinitionDsl;

import java.util.Objects;

public class User {

    private Long userId;

    private String name;

    private String email;

    private String PhoneNumber;

    private String PasswordHash;

    private Role role;

    public User() {
    }

    public User(Long userId, String name, String email, String phoneNumber, String passwordHash, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        PhoneNumber = phoneNumber;
        PasswordHash = passwordHash;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(PhoneNumber, user.PhoneNumber) && Objects.equals(PasswordHash, user.PasswordHash) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, PhoneNumber, PasswordHash, role);
    }
}
