package ru.crystal.springboot.CRUDBoot_312.service;

import ru.crystal.springboot.CRUDBoot_312.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsersList();

    User getUserById(int id);

    void save(User person);

    void update(User user);

    void delete(int id);
}

