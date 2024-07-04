package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
public class FooService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void selectForUpdate() {
        String aLong = jdbcTemplate.queryForObject("select bar from foo where id = 1 for update", String.class);
        System.out.println("aLong= " + aLong);
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            log.error("InterruptedException", e);
        }
    }
}
