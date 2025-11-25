package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.sevice.ChefService;
import mk.ukim.finki.wp.lab.sevice.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Dish> dishes = dishService.listDishes();
        model.addAttribute("dishes_list", dishes);
        return "listDishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", null);
        model.addAttribute("formAction", "/dishes/add");
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Optional<Dish> dish = dishService.findById(id);
        if (dish.isEmpty()) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish.get());
        model.addAttribute("formAction", "/dishes/edit/" + id);
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/chef/{chefId}")
    public String listDishesByChef(@PathVariable Long chefId, Model model) {
        Chef chef = chefService.findById(chefId);
        model.addAttribute("chef", chef);
        model.addAttribute("dishes_list", chef.getDishes());
        return "listDishes";
    }

    @PostMapping("/add/{chefId}")
    public String addDishToChef(@PathVariable Long chefId, @RequestParam String dishId) {
        chefService.addDishToChef(chefId, dishId);
        return "redirect:/dishes/chef/" + chefId;
    }

}