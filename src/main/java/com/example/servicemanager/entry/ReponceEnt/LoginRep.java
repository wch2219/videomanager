package com.example.servicemanager.entry.ReponceEnt;

import ch.qos.logback.core.joran.spi.NoAutoStart;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginRep {

    @NotBlank(message = "手机号不能为空")

    private String phone;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,message = "密码长度最少为6")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPsaaword() {
        return password;
    }

    public void setPsaaword(String psaaword) {
        this.password = psaaword;
    }
}
