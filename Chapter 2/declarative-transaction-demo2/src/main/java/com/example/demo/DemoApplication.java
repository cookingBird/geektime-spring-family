package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
    @Resource
    FooServiceImpl fooService;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        try {
            fooService.insertThenRollback();
        }
        catch (Exception e) {

        };
        try {
            fooService.invokeInsertThenRollback();
        }
        catch (Exception e) {

        };
        try {
            fooService.rollbackForRuntimeException();
        }
        catch (Exception e) {

        }finally {
            log.info("Finally Count:{}", fooService.getCount());
        }
    }


}
