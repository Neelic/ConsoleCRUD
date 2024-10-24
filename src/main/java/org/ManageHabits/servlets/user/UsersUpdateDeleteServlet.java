package org.ManageHabits.servlets.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ManageHabits.controller.ControllerContainer;
import org.ManageHabits.servlets.ServletUtils;

import java.io.IOException;

@WebServlet(value = "/users/*")
public class UsersUpdateDeleteServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String[] pathInfo = req.getPathInfo().split("/");
            int id = Integer.parseInt(pathInfo[1]);
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String name = req.getParameter("name");

            ControllerContainer.getUserController().updateUser(req, resp); //TODO переделать
        } catch (NumberFormatException e) {
            ServletUtils.setJsonError("Invalid format of user ID", resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String[] pathInfo = req.getPathInfo().split("/");
            int id = Integer.parseInt(pathInfo[1]);
            ControllerContainer.getUserController().deleteUser(); //TODO переделать
        } catch (NumberFormatException e) {
            ServletUtils.setJsonError("Invalid format of user ID", resp);
        }
    }
}
