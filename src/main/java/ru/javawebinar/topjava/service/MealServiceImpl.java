package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userID) {
        return repository.save(meal, userID);
    }

    @Override
    public void delete(int id, int userID) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.delete(id, userID), id);
    }

    @Override
    public Meal get(int id, int userID) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.get(id, userID), id);
    }

    @Override
    public Meal update(Meal meal, int userID) {
        ValidationUtil.checkNotFound(meal.getUserID().equals(userID), "Other user's food.");
        return save(meal, userID);
    }

    @Override
    public List<Meal> getAll(int userID) {
        return repository.getAll(userID);
    }
}