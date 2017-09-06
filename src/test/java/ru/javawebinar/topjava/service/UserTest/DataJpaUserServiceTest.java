package ru.javawebinar.topjava.service.UserTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.Arrays;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getUserWithMealsTest() {
        User actual = service.getUserWithMeals(UserTestData.ADMIN_ID);
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, actual);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.ADMIN_MEAL1, MealTestData.ADMIN_MEAL2),
                actual.getMeals());
    }

}
