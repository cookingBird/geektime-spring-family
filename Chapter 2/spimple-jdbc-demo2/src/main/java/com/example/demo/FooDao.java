package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class FooDao {
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    public FooDao(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    public void insertData() {
        Arrays.asList("a", "b").forEach(s ->
                jdbcTemplate.update("insert into foo (bar) values (?)", s)
        );

        HashMap<String, String> map = new HashMap<>();
        map.put("bar", "c");
        Number id = simpleJdbcInsert.executeAndReturnKey(map);
        log.warn("id of c : {}", id);
    }

    public void listData() {
        log.info("Count: {}", jdbcTemplate.queryForObject("select count(*) from foo", Long.class));

        List<String> list = jdbcTemplate.queryForList("select bar from foo", String.class);

        list.forEach(s -> log.info("bar: {}", s));

        List<Foo> fooList = jdbcTemplate.query("select * from foo", new RowMapper<Foo>() {

            @Override
            public Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Foo.builder()
                        .id(rs.getLong(1))
                        .bar(rs.getString(2))
                        .build();
            }
        });

        fooList.forEach(foo -> log.info("foo: {}", foo));

    }

}
