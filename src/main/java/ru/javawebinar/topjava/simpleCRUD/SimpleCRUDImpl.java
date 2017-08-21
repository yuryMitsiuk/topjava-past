package ru.javawebinar.topjava.simpleCRUD;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCRUDImpl implements SimpleCRUD {
    private static Map<Integer, Meal> mealDB = new ConcurrentHashMap<>();
    private static AtomicInteger mealID = new AtomicInteger();

    @Override
    public Meal createMeal(Meal meal) {
        meal.setId(mealID.incrementAndGet());
        mealDB.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal getMeal(int id) {
        return mealDB.get(id);
    }

    @Override
    public void deleteMeal(int id) {
        mealDB.remove(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealDB.values());
    }

    @Override
    public Meal updateMeal(Meal meal) {
        mealDB.put(meal.getId(), meal);
        return mealDB.get(meal.getId());
    }
}
