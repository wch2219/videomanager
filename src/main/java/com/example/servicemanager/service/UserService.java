package com.example.servicemanager.service;


import com.example.servicemanager.entry.ReponceEnt.LoginRep;
import com.example.servicemanager.entry.ResultEntry.BaseResult;

import java.util.Map;

public interface UserService {

    BaseResult login(Map<String,Object> loginRep);

    BaseResult register(Map<String,Object> map);
}
