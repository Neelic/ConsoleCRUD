package org.ConsoleCRUD.db;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Storage {

    private static Storage INSTANCE;
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final String schemaName;

    protected Storage() {

        StorageConf conf = StorageConf.loadConfig();
        jdbcUrl = conf.getJdbcUrl();
        username = conf.getUsername();
        password = conf.getPassword();
        schemaName = conf.getSchemaName();

        try {
            setUpMigrations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Storage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Storage();
        }
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    private void setUpMigrations() throws SQLException {

        Connection c = getConnection();
        Liquibase liquibase;
        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            liquibase = new Liquibase(
                    "start_migration.yaml",
                    new ClassLoaderResourceAccessor(),
                    database
            );

            liquibase.update();
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        } finally {
            if (c != null) {
                try {
                    c.rollback();
                    c.close();
                } catch (SQLException e) {
                    //nothing to do
                }
            }
        }
    }
}
