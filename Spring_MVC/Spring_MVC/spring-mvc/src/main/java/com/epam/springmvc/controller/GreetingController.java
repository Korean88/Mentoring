package com.epam.springmvc.controller;

import com.epam.springmvc.model.Meal;
import com.epam.springmvc.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created by Andrey Yun on 28.02.2016.
 */

@Controller
public class GreetingController {

    @Inject
    private MealService mealService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadIndex(ModelMap modelMap) {
        modelMap.addAttribute("title", "Main page");
        return "index";
    }

    @RequestMapping(value = "admin/all-items", method = RequestMethod.GET)
    public String loadAllItemsPage(ModelMap modelMap) {
        modelMap.put("title", "All items");
        Set<Meal> allMeals = mealService.fetchAllMeals();
        modelMap.put("allMeals", allMeals);
        return "admin-all";
    }

    @RequestMapping(value = "admin/add-item", method = RequestMethod.GET)
    public ModelAndView loadAddItemPage() {
        ModelAndView res = new ModelAndView("admin-add", "command", new Meal());
        res.addObject("title", "Add item");
        return res;
    }

    @RequestMapping(value = "admin/add-item", method = RequestMethod.POST)
    public String addItem(@ModelAttribute Meal meal, ModelMap modelMap, @RequestParam("file") MultipartFile file) {
        mealService.addMeal(meal, file);
        return "redirect:" + "/admin/all-items";
    }
}
