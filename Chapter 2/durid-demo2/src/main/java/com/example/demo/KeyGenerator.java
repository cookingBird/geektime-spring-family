package com.example.demo;

import lombok.SneakyThrows;

import static com.alibaba.druid.filter.config.ConfigTools.*;

public class KeyGenerator {
    static String password = "foo";

    @SneakyThrows
    public static void main(String[] args) {
        String[] arr = genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + encrypt(arr[0], password));
        System.out.println(decrypt(arr[1], encrypt(arr[0], password)));
    }
}
