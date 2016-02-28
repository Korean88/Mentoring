package com.mentoring.gradle.controller;

import com.mentoring.gradle.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by Andrey Yun on 28.02.2016.
 */

@Controller
public class GreetingController {

    private GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) throws IOException {
        modelMap.addAttribute("title", "Spring MVC");
        modelMap.addAttribute("greeting", greetingService.sayHello());
        return "index";
    }
}
