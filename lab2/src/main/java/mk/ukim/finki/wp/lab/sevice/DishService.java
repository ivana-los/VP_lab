package mk.ukim.finki.wp.lab.sevice;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
    Optional<Dish> findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime);
    void delete(Long id);
}
