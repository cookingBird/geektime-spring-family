package com.example.demo.demos.web.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, money.getAmountMajorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String colName) throws SQLException {
        return buildMoney(resultSet.getLong(colName));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return buildMoney(resultSet.getLong(i));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return buildMoney(callableStatement.getLong(columnIndex));
    }

    private Money buildMoney(Long price) {
        return Money.ofMinor(CurrencyUnit.of("CNY"), price);
    }
}
