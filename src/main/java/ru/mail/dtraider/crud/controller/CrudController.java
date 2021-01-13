package ru.mail.dtraider.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.mail.dtraider.crud.model.User;
import ru.mail.dtraider.crud.service.UserService;


@Controller

public class CrudController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public ModelAndView getEditPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("editUsers");
        modelAndView.addObject("allUsers", userService.getUsers());
        return modelAndView;
    }

    @PostMapping(value = "/delete")
    public ModelAndView getDelete(@RequestParam long idUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.deleteUser(idUser);
        return modelAndView;
    }
    @GetMapping("/create")
public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createUser");
        modelAndView.addObject("userAdd", new User());
        return modelAndView;

}
    @PostMapping(value = "/add")
    public ModelAndView getAdd(@ModelAttribute("userAdd") User theUser) {
        ModelAndView modelAndView = new ModelAndView();
        userService.createUser(theUser);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping(value = "/preupdate")
    public ModelAndView getUpdate(@RequestParam long idUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        User user = userService.readUser(idUser);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public ModelAndView getUpdatePost(@ModelAttribute("user") User theUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.updateUser(theUser.getId(), theUser.getName(), theUser.getLastName(), theUser.getAge());
        return modelAndView;
    }

}