package org.ConsoleCRUD.db;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class StorageConf {

    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/src/main/resources/liquibase.properties";

    public static StorageConf loadConfig() {
        StorageConf conf = null;
        try {
            FileReader is = new FileReader(CONFIG_PATH);
            Properties props = new Properties();

            props.load(is);
            is.close();

            String jdbcUrl = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String schemaName = props.getProperty("liquibase.liquibaseSchemaName");


            conf = new StorageConf(jdbcUrl, username, password, schemaName);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

        return conf;
    }

    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final String schemaName;

    public StorageConf(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.schemaName = null;
    }

    public StorageConf(String jdbcUrl, String username, String password, String schemaName) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.schemaName = schemaName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSchemaName() {
        return schemaName;
    }
}
