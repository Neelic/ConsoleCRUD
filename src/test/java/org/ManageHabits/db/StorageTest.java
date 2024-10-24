package org.ManageHabits.db;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.ManageHabits.TestUtils.*;
import static org.ManageHabits.TestUtils.DB_PASSWORD;
import static org.junit.Assert.*;

public class StorageTest {

    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DB_IMAGE)
            .withReuse(true)
            .withDatabaseName(DB_NAME)
            .withExposedPorts(DB_PORT)
            .withUsername(DB_USERNAME)
            .withPassword(DB_PASSWORD);

    @BeforeClass
    public static void beforeClass() {
        postgreSQLContainer.start();
        Storage.setConf(new StorageConf(
                postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword())
        );
    }

    @AfterClass
    public static void afterClass() {
        postgreSQLContainer.stop();
    }

    @Test
    public void testReadConfig() {
        Storage storage = Storage.getInstance();

        assertEquals(postgreSQLContainer.getJdbcUrl(), storage.getJdbcUrl());
        assertEquals(postgreSQLContainer.getUsername(), storage.getUsername());
        assertEquals(postgreSQLContainer.getPassword(), storage.getPassword());
    }

    @Test
    public void testGetConnection() throws SQLException {
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        assertNotNull(connection);
        assertEquals(postgreSQLContainer.getJdbcUrl(), connection.getMetaData().getURL());
        assertEquals(postgreSQLContainer.getUsername(), connection.getMetaData().getUserName());
        connection.close();
    }

    @Test
    public void testSetUpMigrations() throws SQLException {
        Connection c = Storage.getInstance().getConnection();

        Statement stmtUser = c.createStatement();
        Statement stmtHabit = c.createStatement();
        Statement stmtFrequency = c.createStatement();
        ResultSet rsUsers = stmtUser.executeQuery("SELECT COUNT(*) FROM protected.users");
        ResultSet rsHabits = stmtHabit.executeQuery("SELECT COUNT(*) FROM protected.habits");
        ResultSet rsFrequencies = stmtFrequency.executeQuery("SELECT COUNT(*) FROM protected.frequencies");

        assertTrue(rsUsers.next());
        assertTrue(rsHabits.next());
        assertTrue(rsFrequencies.next());

        assertEquals(2, rsUsers.getInt(1));
        assertEquals(3, rsHabits.getInt(1));
        assertEquals(3, rsFrequencies.getInt(1));
    }
}