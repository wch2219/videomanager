package com.example.servicemanager.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public class EncryptUtil {

    public static String encrypt(String str){
        // 加密
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("password");
        String newPassword = textEncryptor.encrypt(str);

        return newPassword;

    }

    public static String decrypt(String str){
        // 解密
        BasicTextEncryptor textEncryptor2 = new BasicTextEncryptor();
        textEncryptor2.setPassword("password");
        String oldPassword = textEncryptor2.decrypt(str);

        return oldPassword;
    }

    public static void main(String[] args) {
        System.out.println("加密："+encrypt("123456789"));
        System.out.println("解密："+decrypt(encrypt("123456")));

    }

}
