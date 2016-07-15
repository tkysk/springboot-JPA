package com.example.app;

import com.example.domain.model.User;
import com.example.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String home(@ModelAttribute("formModel") User formData, Model model) {
        model.addAttribute("msg", "this is sample content.");
        Iterable<User> list = repository.findAll();
//        Iterable<User> list = repository.findByIdIsNotNullOrderByIdDesc();
        model.addAttribute("datalist", list);

        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public String form(
            @ModelAttribute("formModel") @Validated User formData,
            BindingResult result,
            Model model
    ) {
        if (!result.hasErrors()) {
            repository.saveAndFlush(formData);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "sorry, error is occured...");
            model.addAttribute("datalist", repository.findAll());
            return "home";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(
            @PathVariable Long id,
//            @RequestParam Long id,
            @ModelAttribute User data,
            Model model
    ) {
        try {
            User currentData = repository.findById(id);
            model.addAttribute("formModel", currentData);
        } catch (NullPointerException e) {
        }

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional
    public String update(@ModelAttribute User data, Model model) {
        repository.saveAndFlush(data);
        return "redirect:/";
    }


    @PostConstruct
    public void init() {
        User d1 = new User();
        d1.setName("Katsuo");
        d1.setAge(11);
        d1.setMail("katsu@ex");
        d1.setPhone("123456");
        d1.setMemo("Hello i am katsuo");
        repository.saveAndFlush(d1);

        User d2 = new User();
        d2.setName("Wakame");
        d2.setAge(10);
        d2.setMail("waka@ex");
        d2.setPhone("1234561233");
        d2.setMemo("Hello i am Wakame");
        repository.saveAndFlush(d2);

    }
}
