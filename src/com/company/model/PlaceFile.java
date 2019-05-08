package com.company.model;

import java.io.*;
import java.util.Date;

public class PlaceFile {

    File oldFile;
    File newFile;

    public PlaceFile(String url1, String url2,String url3) {
        long current = System.currentTimeMillis();
        Date date = new Date(current);
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
        System.out.println(year+"-"+month+"-"+day);
//        oldFile = new File(url1);
//        newFile = new File(url2 + File.separator + );
    }

    public void copyfile() {
        //复制文件
        FileInputStream ins = null;
        FileOutputStream out = null;
        try {
            ins = new FileInputStream(oldFile);
            out = new FileOutputStream(newFile);
            //自定义缓冲对象
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, b.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
