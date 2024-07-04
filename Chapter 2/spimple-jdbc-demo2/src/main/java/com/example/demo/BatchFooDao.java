package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
@Repository
public class BatchFooDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BatchFooDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void batchInsert() {
        jdbcTemplate.batchUpdate("INSERT INTO foo (bar) VALUES (?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, "bar-" + i);
                    }

                    @Override
                    public int getBatchSize() {
                        return 2;
                    }
                });

        ArrayList<Foo> fooArrayList = new ArrayList<>();
        fooArrayList.add(Foo.builder().id(100L).bar("b-100").build());
        fooArrayList.add(Foo.builder().id(101L).bar("b-101").build());

        namedParameterJdbcTemplate
                .batchUpdate("INSERT INTO foo (id, bar) VALUES (:id, :bar)",
                        SqlParameterSourceUtils.createBatch(fooArrayList));
    }
}
