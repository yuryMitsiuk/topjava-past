package ru.javawebinar.topjava.service.UserTest;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends UserServiceTest {

}
