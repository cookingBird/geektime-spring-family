package com.example.demo.demos.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JdbcDemoApplication implements CommandLineRunner {
    private static String DB_URL = "jdbc:h2:mem:testdb";
    private static String USER_NAME = "SA";
    private static String PASS_WORD = "";

    public static void main(String[] args) {
        SpringApplication.run(JdbcDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Class.forName("com.h2.Driver");
        try (
                Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD)

        ) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE FOO (ID INT IDENTITY, BAR VARCHAR(64));"
                    + "INSERT INTO FOO (ID, BAR) VALUES (1, 'aaa');"
                    + "INSERT INTO FOO (ID, BAR) VALUES (2, 'bbb');");
            ResultSet resultSet = statement.executeQuery("select bar from FOO");
            while (resultSet.next()) {
                System.out.println("Hello " + resultSet.getString("bar"));
            }
        }
    }
}
