package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.List;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("getAll meals for user with id {}", AuthorizedUser.id());
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get meal");
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        ValidationUtil.checkNew(meal);
        return service.save(meal, AuthorizedUser.id());
    }

    public Meal update(Meal meal, int id) {
        log.info("update {}, id {}", meal, id);
        ValidationUtil.checkIdConsistent(meal, id);
        return service.update(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        log.info("delete meal id {}", id);
        service.delete(id, AuthorizedUser.id());
    }

}