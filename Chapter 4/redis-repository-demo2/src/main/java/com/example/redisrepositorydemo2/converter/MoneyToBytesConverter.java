package com.example.redisrepositorydemo2.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.charset.StandardCharsets;

@WritingConverter
public class MoneyToBytesConverter implements Converter<Money, byte[]> {
    @Override
    public byte[] convert(Money source) {
        String val = Long.toString(source.getAmountMajorLong());
        return val.getBytes(StandardCharsets.UTF_8);
    }
}
