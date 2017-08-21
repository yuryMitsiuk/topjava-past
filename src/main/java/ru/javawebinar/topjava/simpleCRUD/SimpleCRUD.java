package ru.javawebinar.topjava.simpleCRUD;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface SimpleCRUD {
    Meal createMeal(Meal meal);
    Meal getMeal(int id);
    void deleteMeal(int id);
    List<Meal> getAllMeals();
    Meal updateMeal(Meal meal);
}
