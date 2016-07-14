package com.example.controller;

import com.example.model.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

/**
 * Created by saeki on 2016/07/14.
 */
@Controller
public class UserController {
    @Autowired
    UserRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@ModelAttribute("formModel")User formData, Model model) {
        model.addAttribute("msg","this is sample content.");
        Iterable<User> list = repository.findAll();
        model.addAttribute("datalist",list);

        return "home";
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @Transactional
    public String form(@ModelAttribute("formModel")User formData, Model model) {
        repository.saveAndFlush(formData);
        return "redirect:/";
    }


    @PostConstruct
    public void init(){
        User d1 = new User();
        d1.setName("Katsuo");
        d1.setAge(11);
        d1.setMail("katsu@ex");
        d1.setMemo("Hello i am katsuo");
        repository.saveAndFlush(d1);

        User d2 = new User();
        d2.setName("Wakame");
        d2.setAge(10);
        d2.setMail("waka@ex");
        d2.setMemo("Hello i am Wakame");
        repository.saveAndFlush(d2);

    }
}