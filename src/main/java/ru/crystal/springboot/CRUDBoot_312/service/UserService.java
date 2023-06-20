package ru.crystal.springboot.CRUDBoot_312.service;

import ru.crystal.springboot.CRUDBoot_312.model.User;

import java.util.List;

public interface UserService {
    List<User> getPersonsList();

    User getPersonById(int id);

    void save(User person);

    void update(int id, User user);

    void delete(int id);
}

