package com.example.app;

import com.example.domain.model.User;
import com.example.domain.repository.UserRepositoryComponent;
import com.example.domain.service.AccessCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by saeki on 2016/07/15.
 */
@RestController
public class UserRestController {
    @Autowired
    private UserRepositoryComponent data;

    @Autowired
    private AccessCount service;

    @RequestMapping("/rest")
    public List<User> restAll() {
        return data.getAll();
    }

    @RequestMapping("/rest/{num}")
    public User restFind(@PathVariable int num) {
        return data.get(num);
    }

    @RequestMapping("/count")
    public int count(){
        return service.count();
    }
}
