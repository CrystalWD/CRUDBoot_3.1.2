package ru.crystal.springboot.CRUDBoot_312.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.crystal.springboot.CRUDBoot_312.model.User;
import ru.crystal.springboot.CRUDBoot_312.repository.UserRepository;

@Component
public class UserValidator implements Validator {
    private final UserRepository personRepository;

    @Autowired
    public UserValidator(UserRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (personRepository.findById(user.getId()).isPresent()) {
            errors.rejectValue("id", "", "This id already taken");
        }
    }
}
