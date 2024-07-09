package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        log.info("after insertRecord: {}\n", getCount());
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class, propagation = Propagation.REQUIRES_NEW)
    public void insertThenRollback() throws RollbackException {
        log.info("before insertThenRollback: {} \n", getCount());
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }


    @Transactional(rollbackFor = RollbackException.class, propagation = Propagation.REQUIRES_NEW)
    public void insertThenRollback(boolean isLog) throws RollbackException {
        if (isLog) {
            log.info("before insertThenRollback: {}", getCount());
        }
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        log.info("before invokeInsertThenRollback: {}", getCount());
        try {
            insertThenRollback(false);
        }
        catch (Exception e) {
        }
        log.info("after invokeInsertThenRollback: {}\n", getCount());
    }

    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRES_NEW)
    public void rollbackForRuntimeException() {
        log.info("before rollbackForTest: {}\n", getCount());
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('DDD')");
        throw new RuntimeException();
    }

    public long getCount() {
        return (long) jdbcTemplate.queryForList("SELECT COUNT(*) FROM FOO")
                .get(0)
                .get("COUNT(*)");
    }
}
