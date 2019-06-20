package com.example.servicemanager.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    String pathIn="D:\\down\\微信_slices\\mipmap-xxxhdpi";//某个文件目录
    String pathOut="D:\\down\\微信_slices\\copy\\";//要复制到的地方
    /*
     * 读取指定路径下的文件名和目录名
     */
    public void getFileList() {
        File file = new File(pathIn);
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                copy(file.getAbsolutePath()+"\\"+fileName,pathOut+i+".png");
                System.out.println("文件：" + fileName);
            }
            if (fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                System.out.println("目录：" + fileName);
            }
        }
    }

    private static void copy(String url1, String url2)  {
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(url1));
            FileOutputStream out = new FileOutputStream(new File(url2));
            byte[] buff = new byte[512];
            int n = 0;
            System.out.println("复制文件：" + "\n" + "源路径：" + url1 + "\n" + "目标路径：" + url2);
            while ((n = in.read(buff)) != -1) {
                out.write(buff, 0, n);
            }
            out.flush();
            in.close();
            out.close();
            System.out.println("复制完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ReadFile rf = new ReadFile();
        rf.getFileList();
    }

    private List<String> getNameList(){
        List<String> list = new ArrayList<>();
        list.add("");

        return list;
    }


}
