package org.ManageHabits;

import java.io.ByteArrayInputStream;

public class TestUtils {
    public static String DB_IMAGE = "postgres:14.8-alpine3.18";
    public static int DB_PORT = 5432;
    public static String DB_USERNAME = "user";
    public static String DB_PASSWORD = "12345";
    public static String DB_NAME = "habits_db";

    public static void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
