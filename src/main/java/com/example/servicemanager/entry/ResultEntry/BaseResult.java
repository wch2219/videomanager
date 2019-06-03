package com.example.servicemanager.entry.ResultEntry;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

public class BaseResult {
    public BaseResult() {
        super();
    }

    public BaseResult(int code, @NonNull String mess, @NotNull Object result) {
       this.code = code;
       this.mess = mess;
       this.result =result;
    }

    private int code;
    private String mess;
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
