package ru.javawebinar.topjava.service.MealTests;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaMealServiceTest extends MealServiceTest {
}
