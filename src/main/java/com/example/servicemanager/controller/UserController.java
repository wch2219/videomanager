package com.example.servicemanager.controller;

import com.example.servicemanager.entry.ReponceEnt.LoginRep;
import com.example.servicemanager.entry.ReponceEnt.RegisterReq;
import com.example.servicemanager.entry.ResultEntry.BaseResult;
import com.example.servicemanager.service.UserService;
import com.example.servicemanager.utils.Constant;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.net.InetAddress;
import java.util.*;

@RestController
@RequestMapping("/manager/user")
public class UserController extends BaseController{

    @Value("${spring.resources.static-locations}")
    private String uploadPath;

    @Value("${spring.mvc.static-path-pattern}")
    private String imagDownPath;

    private String Ip;
    @Resource
    private UserService service;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResult login(@RequestBody @Valid LoginRep rep, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            for (ObjectError allError : bindingResult.getAllErrors()) {

                return new BaseResult(Constant.PARAM_ERROR,allError.getDefaultMessage(),null);
            }
        }

        BaseResult user = service.login(rep);

        return user;
    }



    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public BaseResult register(@RequestBody @Valid RegisterReq req, HttpServletRequest request, BindingResult bindingResult){
        String code = (String) request.getSession().getAttribute(req.getPhone());
        if (bindingResult.hasErrors()) {
            return new BaseResult(Constant.PARAM_ERROR,bindingResult.getFieldError().getDefaultMessage(),null);
        }

        if (!req.getSmscode().equals(code)) {
            return new BaseResult(Constant.PARAM_ERROR,"验证码输入错误",code);
        }
        service.register(req.getPhone(),req.getPassword());
        return new BaseResult(Constant.SUCCESS,"成功",code);
    }
    @PostMapping("/getAuth")
    public BaseResult getAuthCode(@RequestParam("phone") String phone, HttpServletRequest request){
        if (StringUtils.isEmpty(phone)) {

            return new BaseResult(Constant.PARAM_ERROR,"手机号不能为空",null);
        }

        if (phone.length() !=11) {
            return new BaseResult(Constant.PARAM_ERROR,"手机号格式错误",null);
        }
        String randomCode = RandomStringUtils.randomNumeric(6);
        request.getSession().setAttribute(phone,randomCode);
        return new BaseResult(Constant.SUCCESS,"成功",randomCode);
    }


    @PostMapping("/upload")
    public BaseResult upPic(@RequestParam("files")MultipartFile[] files){
        List<String> url = new ArrayList<>();

        if (files.length == 0) {
            return new BaseResult(Constant.PARAM_ERROR,"空",null);
        }
        for (MultipartFile file : files) {
            File targetFile = null;
            String imgPath = "";// 返回存储路径
            String fileName = file.getOriginalFilename();// 获取文件名加后缀
            if (fileName != null && fileName != "") {
                String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());// 文件后缀
                fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;// 新的文件名
                // 先判断文件是否存在
                File dest = new File(uploadPath);
                // 如果文件夹不存在则创建
                if (!dest.exists() && !dest.isDirectory()) {
                    dest.mkdir();
                }
                targetFile = new File(dest, fileName);
                try {
                    file.transferTo(targetFile);
                    imgPath = uploadPath + fileName;
                    Ip = "http://"+InetAddress.getLocalHost().getHostAddress();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return new BaseResult(Constant.FILE_ERROR,"失败",null);
                }
            }
            imgPath = Ip+":8080/"+imagDownPath + fileName;
            url.add(imgPath);

        }


        return new BaseResult(Constant.SUCCESS,"成功",url);
    }
}
