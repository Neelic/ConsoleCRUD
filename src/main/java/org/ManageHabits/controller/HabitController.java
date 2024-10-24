package org.ManageHabits.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import netscape.javascript.JSObject;
import org.ManageHabits.repository.dto.HabitDTO;
import org.ManageHabits.repository.dto.UserDTO;
import org.ManageHabits.screen.ScreenContainer;
import org.ManageHabits.service.HabitService;
import org.ManageHabits.servlets.ServletUtils;

import java.io.IOException;
import java.util.List;

public class HabitController {

    private final HabitService habitService;
    private final ScreenContainer screenContainer;
    private final ObjectMapper mapper = new ObjectMapper();

    public HabitController(HabitService habitService, ScreenContainer screenContainer) {
        this.habitService = habitService;
        this.screenContainer = screenContainer;
    }

    private static boolean isAuthUser(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
        if (!ServletUtils.isAuthUser(req, resp, id)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletUtils.setJsonError("User is not logged in", resp);
            return false;
        }

        return true;
    }

    public void createHabit(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {

        if (!isAuthUser(req, resp, id)) {
            return;
        }

        try {
            HabitDTO habit = mapper.readValue(ServletUtils.getJsonBody(req), HabitDTO.class);
            UserDTO user = new UserDTO();
            user.setId(id);

            habitService.createHabit(user, habit);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        } catch (JsonProcessingException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void getHabit(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {

        if (!isAuthUser(req, resp, id)) {
            return;
        }

        try {
            UserDTO user = new UserDTO();
            user.setId(id);

            List<HabitDTO> habits = habitService.getHabits(user);
            HabitDTO habit = habits.get((Integer) mapper.readValue(ServletUtils.getJsonBody(req), JSObject.class).getMember("id"));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(mapper.writeValueAsString(habit));
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        }
    }

    public void getHabits(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {

        if (!isAuthUser(req, resp, id)) {
            return;
        }

        try {
            UserDTO user = new UserDTO();
            user.setId(id);
            List<HabitDTO> habits = habitService.getHabits(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(mapper.writeValueAsString(habits));
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        }
    }

    public void updateHabit(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {

        if (!isAuthUser(req, resp, id)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletUtils.setJsonError("User is not logged in", resp);
            return;
        }

        try {
            UserDTO user = new UserDTO();
            user.setId(id);
            HabitDTO habit = mapper.readValue(ServletUtils.getJsonBody(req), HabitDTO.class);
            habitService.changeHabit(user, habit);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        }
    }

    public void deleteHabit(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
        if (!isAuthUser(req, resp, id)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletUtils.setJsonError("User is not logged in", resp);
            return;
        }

        try {
            UserDTO user = new UserDTO();
            user.setId(id);
            habitService.deleteHabit(user, mapper.readValue(ServletUtils.getJsonBody(req), HabitDTO.class));
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        }
    }

    public void showHabitsStat(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
        if (!isAuthUser(req, resp, id)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletUtils.setJsonError("User is not logged in", resp);
            return;
        }

        try {
            UserDTO user = new UserDTO();
            user.setId(id);
            List<HabitDTO> habits = habitService.getHabits(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(mapper.writeValueAsString(habits));
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ServletUtils.setJsonError(e.getMessage(), resp);
        }
    }

}
