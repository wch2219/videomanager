package com.example.servicemanager.mapp.SqlUtils;

import com.example.servicemanager.utils.Constant;
import org.springframework.util.StringUtils;

import java.util.Map;

public class UserSelect {

    public String selectUser(Map<String, Object> map) {

        Object user_id =  map.get(Constant.User_ID);
        String fieleds = (String) map.get(Constant.Fields);

        String sql = "select "+(StringUtils.isEmpty(fieleds)?"*":fieleds)+
                " from t_user where t_user.user_id = "+user_id;
        System.out.println("SQl"+sql);
        return sql;
    }
}
