package ru.javawebinar.topjava.service.MealTests;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.USER;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest {

    @Test
    public void getMealAndUserTest() {
        Meal actual = service.getMealAndUser(MEAL1_ID);
        MATCHER.assertEquals(MEAL1, actual);
        UserTestData.MATCHER.assertEquals(USER, actual.getUser());
    }
}
