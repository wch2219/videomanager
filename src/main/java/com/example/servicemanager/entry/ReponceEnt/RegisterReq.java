package com.example.servicemanager.entry.ReponceEnt;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class RegisterReq {
    @NotBlank( message = "手机号不能为空")

    private String phone;
    @Length(min = 6,message = "密码长度最低6位")
    @NotBlank(message = "密码不能为空")

    private String password;
    @NotBlank(message = "验证码不能为空")

    private String smscode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }
}
