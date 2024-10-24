package org.ManageHabits.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ManageHabits.repository.dto.UserDTO;
import org.ManageHabits.service.UserService;
import org.ManageHabits.servlets.ServletUtils;

import java.io.IOException;

public class UserController {

    private final UserService userService;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void authUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            UserDTO user = mapper.readValue(ServletUtils.getJsonBody(req), UserDTO.class);

            userService.loginUser(user);
            resp.setStatus(HttpServletResponse.SC_OK);

            req.getSession().setAttribute("email", user.getEmail());
            req.getSession().setAttribute("id", user.getId());
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletUtils.setJsonError(e.getMessage(), resp);
        } catch (JsonProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            UserDTO user = mapper.readValue(ServletUtils.getJsonBody(req), UserDTO.class);

            userService.registerUser(user);
            resp.setStatus(HttpServletResponse.SC_OK);

            req.getSession().setAttribute("email", user.getEmail());
            req.getSession().setAttribute("id", user.getId());
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        } catch (JsonProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            UserDTO user = mapper.readValue(ServletUtils.getJsonBody(req), UserDTO.class);

            userService.updateUser(user);
            resp.setStatus(HttpServletResponse.SC_OK);

            req.getSession().setAttribute("email", user.getEmail());
            req.getSession().setAttribute("id", user.getId());
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        } catch (JsonProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Integer id = (Integer) req.getSession().getAttribute("id");
            if (id == null) {
                throw new IllegalArgumentException("User is not logged in");
            }

            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            userService.deleteUser(userDTO);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        }
    }
}
