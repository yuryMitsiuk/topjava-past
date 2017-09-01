package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private DbPopulator dbPopulator;

    @Autowired
    private MealService service;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(USER_MEAL_1, service.get(100002, USER_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(100005, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_3, ADMIN_MEAL_2), service.getAll(ADMIN_ID));
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL_2, ADMIN_MEAL_1),
                service.getBetweenDateTimes(LocalDateTime.of(2017, Month.AUGUST, 31, 8, 30),
                        LocalDateTime.of(2017, Month.AUGUST, 31, 15, 30), ADMIN_ID));
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_3, USER_MEAL_2, USER_MEAL_1), service.getAll(USER_ID));
    }

    @Test
    public void update() throws Exception {
        Meal updatedMeal = new Meal(100003, LocalDateTime.of(2017, Month.SEPTEMBER, 1, 15, 35), "Обед обновленный", 975);
        service.update(updatedMeal, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(updatedMeal, USER_MEAL_3, USER_MEAL_1), service.getAll(USER_ID));
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2017, Month.SEPTEMBER, 1, 9, 0), "Админ завтрак новый", 550);
        service.save(newMeal, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(newMeal, ADMIN_MEAL_3, ADMIN_MEAL_2, ADMIN_MEAL_1), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(100005, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(100003, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Meal newMeal =new Meal(100003, LocalDateTime.of(2017, Month.SEPTEMBER, 1, 15, 35), "Обед обновленный", 975);
        service.update(newMeal, ADMIN_ID);
    }

}