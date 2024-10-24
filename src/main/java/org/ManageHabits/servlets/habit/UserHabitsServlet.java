package org.ManageHabits.servlets.habit;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ManageHabits.servlets.ServletUtils;

import java.io.IOException;

// https://localhost:8080/user_habits/{user_id}
@WebServlet("/user_habits/*")
public class UserHabitsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        ControllerContainer.getHabitController().showHabits(); //TODO переделать
        try {
            String[] pathInfo = req.getPathInfo().split("/");
            int userId = Integer.parseInt(pathInfo[1]); // "123"
        } catch (NumberFormatException e) {
            ServletUtils.setJsonError("Invalid format of user ID", resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        ControllerContainer.getHabitController().createHabit(); //TODO переделать
        try {
            String pathInfo = req.getPathInfo();
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]);
        } catch (NumberFormatException e) {
            ServletUtils.setJsonError("Invalid format of user ID", resp);
        }
    }
}
