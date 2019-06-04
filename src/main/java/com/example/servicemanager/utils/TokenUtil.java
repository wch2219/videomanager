package com.example.servicemanager.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TokenUtil {
    public static boolean volidateToken(String token, HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null;
    }
}
