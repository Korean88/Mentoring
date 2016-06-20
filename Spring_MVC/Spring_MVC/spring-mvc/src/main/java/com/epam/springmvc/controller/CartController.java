package com.epam.springmvc.controller;

import com.epam.springmvc.model.Cart;
import com.epam.springmvc.model.Meal;
import com.epam.springmvc.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

/**
 * Created by Andrey on 15.05.2016.
 */
@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    @Inject
    private MealService mealService;

    @ModelAttribute("cart")
    public Cart createCart() {
        return new Cart();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCartView() {
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("title", "Shopping Cart");
        modelAndView.addObject("user", UserUtil.getPrincipal());
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteMealFromCart(@ModelAttribute("cart") Cart cart, @PathVariable int id) {
        if (cart.getAllMeals().size() != 0) {
            Meal meal = cart.getAllMeals().stream().filter(m -> m.getId() == id).findFirst().get();
            cart.getAllMeals().remove(meal);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String addMealToCart(@ModelAttribute("cart") Cart cart, @PathVariable int id) {
        Meal meal = mealService.findById(id);
        if (meal != null) {
            cart.getAllMeals().add(meal);
        }
        return "redirect:/cart";
    }
}
