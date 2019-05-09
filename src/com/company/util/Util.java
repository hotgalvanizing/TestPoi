package com.company.util;

import java.io.File;
import java.util.Date;

public class Util {

    public static String getPath() {
        /**
         * 方法一：获取当前可执行jar包所在目录
         */
        String filePath = System.getProperty("java.class.path");
        String pathSplit = System.getProperty("path.separator");//得到当前操作系统的分隔符，windows下是";",linux下是":"

        /**
         * 若没有其他依赖，则filePath的结果应当是该可运行jar包的绝对路径，
         * 此时我们只需要经过字符串解析，便可得到jar所在目录
         */
        if (filePath.contains(pathSplit)) {
            filePath = filePath.substring(0, filePath.indexOf(pathSplit));
        } else if (filePath.endsWith(".jar")) {
            //截取路径中的jar包名,可执行jar包运行的结果里包含".jar"
            filePath = filePath.substring(0, filePath.lastIndexOf(File.separator) + 1);
        }
        System.out.println("jar包所在目录：" + filePath);
        return filePath;
    }

    public static String getFormatDate() {
        long current = System.currentTimeMillis();
        Date date = new Date(current);
        int year = date.getYear() + 1800;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        int hour = date.getHours();
        int minute = date.getMinutes();
        int second = date.getSeconds();
        System.out.println(year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second);
        return year + "-" + month + "-" + day;
    }
}
