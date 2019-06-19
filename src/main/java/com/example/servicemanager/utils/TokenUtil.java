package com.example.servicemanager.utils;

import com.example.servicemanager.entry.ResultEntry.Tokendb;
import com.example.servicemanager.mapp.Tokenmapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TokenUtil {
    @Resource
    private  Tokenmapp mTokenmapp;

    public  boolean volidateToken(String token, HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Tokendb userId = mTokenmapp.getUserId(token);
        if (userId == null) {

            return false;
        }else {
            try {
                String expirTime = userId.getExpirTime();
                long currTime = System.currentTimeMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parse = null;
                parse = dateFormat.parse(expirTime);
                long time = parse.getTime();

                if (time<=currTime) {

                    return false;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }



            return true;
        }

    }
}
