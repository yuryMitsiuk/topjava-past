package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.simpleCRUD.SimpleCRUDImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private SimpleCRUDImpl simpleCRUD;
    private List<Meal> meals;

    @Override
    public void init() throws ServletException {
        meals = MealsUtil.getMeals();
        simpleCRUD = new SimpleCRUDImpl();
        meals.stream().forEach(meal->simpleCRUD.createMeal(meal));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to MealSerlet");
        String action = req.getServletPath();
        LOG.debug(action);
        switch (action) {
            case "/delete": deleteMeal(req, resp);   break;
            case "/meal"  : showMealForm(req, resp); break;
            case "/new"   : addNewMeal(req, resp);   break;
            case "/update": updateMeal(req, resp);   break;
            case "/get"   : getMeal(req, resp);      break;
            default       : listMeals(req, resp);    break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    private void listMeals(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealWithExceeds = MealsUtil.getMealWithExceed(simpleCRUD.getAllMeals(), 2000);
        req.setAttribute("mealWithExceeds", mealWithExceeds);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    private void deleteMeal(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        simpleCRUD.deleteMeal(id);
        resp.sendRedirect("meals");
    }

    private void showMealForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("meal.jsp").forward(req, resp);
    }

    private void addNewMeal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        simpleCRUD.createMeal(createMealWithParameter(req.getParameter("description"),
                LocalDateTime.parse(req.getParameter("datetime")), Integer.parseInt(req.getParameter("calories"))));
        listMeals(req, resp);
    }

    private void updateMeal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        simpleCRUD.updateMeal(new Meal(Integer.parseInt(req.getParameter("id")), LocalDateTime.parse(req.getParameter("datetime")),
                req.getParameter("description"),Integer.parseInt(req.getParameter("calories"))));
        resp.sendRedirect("meals");
    }

    private void getMeal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Meal meal = simpleCRUD.getMeal(Integer.parseInt(req.getParameter("id")));
        RequestDispatcher dispatcher = req.getRequestDispatcher("meal.jsp");
        req.setAttribute("meal", meal);
        dispatcher.forward(req, resp);
    }

    private Meal createMealWithParameter(String description, LocalDateTime dateTime, int calories) {
        Meal meal = new Meal();
        meal.setCalories(calories);
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        return meal;
    }

}
