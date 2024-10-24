package org.ManageHabits.servlets.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users")
public class UsersRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        resp.getWriter().write("Hello");
        resp.getWriter().close();
//        ControllerContainer.getUserController().registerUser(); //TODO переделать
    }
}
