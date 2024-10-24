package org.ManageHabits.servlets.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ManageHabits.controller.ControllerContainer;

import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ControllerContainer.getUserController().authUser(req, resp); //TODO переделать
    }
}
