package com.example.app;

/**
 * Created by saeki on 2016/07/15.
 */

import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.domain.repository.UserRepositoryComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController2 {

    @Autowired
    UserRepository repository;

    @Autowired
    private UserRepositoryComponent component;

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Controller2");
        model.addAttribute("msg", "Sample data");
        model.addAttribute("datalist", component.getAll());
        return "home2";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(Model model) {
        model.addAttribute("title", "find");
        model.addAttribute("msg", "Sample data");
        model.addAttribute("value", "");
        model.addAttribute("datalist", component.getAll());
        return "find";
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String searchResult(Model model, HttpServletRequest request) {
        String param = request.getParameter("fstr");
        if (param == "")
            return "redirect:/find";
        else {
            model.addAttribute("title", "find result");
            model.addAttribute("msg", "[" + param + "]の検索結果");
            model.addAttribute("value", param);
            model.addAttribute("datalist", component.find(param));
            return "find";
        }
    }

    @RequestMapping(value = "/page/{num}", method = RequestMethod.GET)
    public String page(@PathVariable Integer num, Model m) {
        Page<User> page = component.getUserInPage(num);
        m.addAttribute("pagenumber",num);
        m.addAttribute("datalist",page);
        return "home2";
    }

}
