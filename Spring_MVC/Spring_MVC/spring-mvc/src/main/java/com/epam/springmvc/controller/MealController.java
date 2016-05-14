package com.epam.springmvc.controller;

import com.epam.springmvc.exception.BusinessException;
import com.epam.springmvc.model.Meal;
import com.epam.springmvc.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.Set;

/**
 * Created by Andrey Yun on 28.02.2016.
 */

@Controller
public class MealController {

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
        res.addObject("title", "Add meal");
        return res;
    }

    @RequestMapping(value = "admin/add-item", method = RequestMethod.POST)
    public String addItem(@ModelAttribute Meal meal, @RequestParam("file") MultipartFile file) {
        mealService.addMeal(meal, file);
        return "redirect:" + "/admin/all-items";
    }

    @RequestMapping(value = "admin/edit/{id}", method = RequestMethod.GET)
    public ModelAndView loadEditItemPage(@PathVariable String id) {
        Meal meal = mealService.findById(Integer.parseInt(id));
        ModelAndView res = new ModelAndView("admin-edit", "command", meal);
        res.addObject("meal", meal);
        res.addObject("title", "Edit meal");
        return res;
    }

    @RequestMapping(value = "admin/edit/{id}", method = RequestMethod.POST)
    public String editItem(@ModelAttribute Meal meal, @PathVariable String id,
                           @RequestParam("file") MultipartFile file) {
        if (mealService.editMeal(Integer.parseInt(id), meal, file)) {
            return "redirect:/admin/all-items";
        } else {
            throw new BusinessException("Failed to edit meal with id " + id);
        }
    }

    @RequestMapping(value = "admin/delete/{id}", method = RequestMethod.GET)
    public String deleteMeal(@PathVariable String id) {
        if ("16".equals(id)) throw new BusinessException("Test Error Page!!!");
        if (mealService.deleteMeal(Integer.parseInt(id))) {
            return "redirect:/admin/all-items";
        } else {
            throw new BusinessException("Could not delete meal with id " + id);
        }
    }

    @ExceptionHandler(BusinessException.class)
    public ModelAndView showErrorPage(BusinessException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("title", "Error");
        modelAndView.addObject("exception", ex);
        return modelAndView;
    }

    @RequestMapping(value = "cn/{id}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseBody
    public Meal listMeal(@PathVariable String id) {
        return mealService.findById(Integer.parseInt(id));
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("title", "Login");
        return modelAndView;
    }
}
