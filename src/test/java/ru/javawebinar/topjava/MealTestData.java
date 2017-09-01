package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

public class MealTestData {

    public static final Meal USER_MEAL_1 = new Meal(100002, LocalDateTime.of(2017, Month.AUGUST, 30, 9, 0), "Завтрак", 550);
    public static final Meal USER_MEAL_2 = new Meal(100003, LocalDateTime.of(2017, Month.AUGUST, 30, 14, 0), "Обед", 1000);
    public static final Meal USER_MEAL_3 = new Meal(100004, LocalDateTime.of(2017, Month.AUGUST, 30, 19, 0), "Ужин", 250);

    public static final Meal ADMIN_MEAL_1 = new Meal(100005, LocalDateTime.of(2017, Month.AUGUST, 31, 9, 0), "Админ завтрак", 250);
    public static final Meal ADMIN_MEAL_2 = new Meal(100006, LocalDateTime.of(2017, Month.AUGUST, 31, 14, 0), "Админ обед", 1150);
    public static final Meal ADMIN_MEAL_3 = new Meal(100007, LocalDateTime.of(2017, Month.AUGUST, 31, 19, 0), "Админ ужин", 750);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            ((expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())))
    );

}
