package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: ");
            Arrays.asList(appCtx.getBeanDefinitionNames()).forEach(System.out::println);

            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println("Create new user:");
            adminUserController.create(new User(null, "alex", "email", "password", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "bob", "email", "password", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "bob", "bob@email", "password", Role.ROLE_ADMIN));
            System.out.println("Get all users:");
            adminUserController.getAll().forEach(System.out::println);
            System.out.println("Get user by email:");
            System.out.println(adminUserController.getByMail("email").toString());
            System.out.println("Update user:");
            adminUserController.update(new User(1, "userNameUpdate", "emailUpdate", "passwordUpdate", Role.ROLE_ADMIN), 1);
            System.out.println(adminUserController.get(1));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            System.out.println("Get all meals for Authorized user with id = 1");
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("Create new meal for Authorized user");
            mealRestController.create(new Meal(LocalDateTime.of(2017, Month.MAY, 30, 10, 0), "Завтрак", 500, adminUserController.get(1).getId()));
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("Update meal id = 7 for Authorized user");
            mealRestController.update(new Meal(7, adminUserController.get(1).getId(), LocalDateTime.of( 2017, Month.JULY, 30, 10, 0), "Breakfast", 550), 7);
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("Delete meals for Authorized user");
            mealRestController.delete(7);
            mealRestController.delete(3);
            mealRestController.delete(2);
            mealRestController.delete(1);
            mealRestController.getAll().forEach(System.out::println);

            adminUserController.delete(1);
            adminUserController.delete(2);
            adminUserController.delete(3);
            System.out.println("Get all users:");
            adminUserController.getAll().forEach(System.out::println);

        }
    }
}
