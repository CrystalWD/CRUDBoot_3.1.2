package ru.crystal.springboot.CRUDBoot_312.controller;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.crystal.springboot.CRUDBoot_312.model.User;
import ru.crystal.springboot.CRUDBoot_312.service.UserService;
import ru.crystal.springboot.CRUDBoot_312.util.UserValidator;


@Controller
@RequestMapping("/person")
public class UserController {
    private final UserService userServiceImp;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userServiceImp, UserValidator userValidator) {
        this.userServiceImp = userServiceImp;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String getPersons(Model model) {
        model.addAttribute("persons", userServiceImp.getPersonsList());
        return "list";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable int id, Model model) {
        model.addAttribute("personById", userServiceImp.getPersonById(id));
        return "single";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") User user) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "new";
        userServiceImp.save(user);
        return "redirect:/person";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", userServiceImp.getPersonById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult,
                         @PathVariable int id) {
        if (bindingResult.hasErrors())
            return "edit";
        userServiceImp.update(id, user);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userServiceImp.delete(id);
        return "redirect:/person";
    }
}
