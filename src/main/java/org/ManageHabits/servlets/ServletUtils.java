package org.ManageHabits.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {
    public static void setJsonError(String message, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.write("{\"error\": \"" + message + "\"}");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static String getJsonBody(HttpServletRequest request) {

        StringBuilder jb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            return null;
        }

        return jb.toString();
    }

    public static boolean isAuthUser(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
        Integer idSession = (Integer) req.getSession().getAttribute("id");
        if (idSession == null) {
            return false;
        }

        return id == idSession;
    }
}
