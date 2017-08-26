package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    Meal save(Meal Meal, int userID);

    // false if not found
    boolean delete(int id, int userID);

    // null if not found
    Meal get(int id, int userID);

    List<Meal> getAll(int userID);
}
