package com.example.servicemanager.service.serviceimpl.SqlUtils;

import java.util.Map;

public class UserSelect {

    public String selectUser(Map<String, Object> map) {

        String phone = (String) map.get("phone");
        String pass = (String) map.get("pass");
        String fieleds = (String) map.get("fieleds");

        String sql = "select "+(fieleds.equals("")?"*":fieleds)+
                " from t_user where t_user.user_phone = "+phone+
                " and t_user.user_password ="+pass;
        System.out.println("SQl"+sql);
        return sql;
    }
}
