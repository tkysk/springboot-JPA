package com.example.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by saeki on 2016/07/15.
 */
@Service
public class AccessCount {
    private int counter = 0;
    private int max = 10;

    @Autowired
    public AccessCount(ApplicationArguments args) {
        List<String> files = args.getNonOptionArgs();
        try {
            max = Integer.parseInt(files.get(0));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public int count() {
        counter++;
        counter = counter > max ? 0 : counter;
        return counter;
    }
}
