package com.example.servicemanager.service;


import com.example.servicemanager.entry.ReponceEnt.LoginRep;
import com.example.servicemanager.entry.ResultEntry.BaseResult;

public interface UserService {

    BaseResult login(LoginRep loginRep);

    BaseResult register(String phone, String password);
}
