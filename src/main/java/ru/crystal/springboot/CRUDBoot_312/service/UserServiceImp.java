package ru.crystal.springboot.CRUDBoot_312.service;



import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crystal.springboot.CRUDBoot_312.model.User;
import ru.crystal.springboot.CRUDBoot_312.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserRepository personRepository;


    @Autowired
    UserServiceImp(UserRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<User> getUsersList() {
        return personRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        Optional<User> byId = personRepository.findById(id);
        return byId.orElseThrow(()-> new EntityNotFoundException("User not found. ID: "+ id));
    }


    @Transactional
    @Override
    public void save(User user) {
        personRepository.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        personRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        personRepository.delete(getUserById(id));
    }
}
