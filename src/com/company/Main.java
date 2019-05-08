package com.company;

import com.company.bean.BaseExcel;
import com.company.bean.BaseSheet;
import com.company.model.*;
import com.company.model.handledata.HandleData;
import com.company.model.handledata.one.HandleOneAData;
import com.company.model.handledata.one.HandleOneBData;
import com.company.model.handledata.three.HandleThreeAData;
import com.company.model.handledata.three.HandleThreeBData;
import com.company.model.handledata.three.HandleThreeCData;
import com.company.model.handledata.two.HandleTwoAData;
import com.company.model.handledata.two.HandleTwoBData;
import com.company.model.handledata.two.HandleTwoCData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 1、初始化配置
 * 2、读取数据表
 * 3、读取配置表
 * 4、处理数据表
 * 5、生成结果表
 * 6、生成历史表
 */
public class Main {

    /**
     * 入口函数
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 方法一：获取当前可执行jar包所在目录
         */
        String filePath = System.getProperty("java.class.path");
        String pathSplit = System.getProperty("path.separator");//得到当前操作系统的分隔符，windows下是";",linux下是":"

        /**
         * 若没有其他依赖，则filePath的结果应当是该可运行jar包的绝对路径，
         * 此时我们只需要经过字符串解析，便可得到jar所在目录
         */
        if(filePath.contains(pathSplit)){
            filePath = filePath.substring(0,filePath.indexOf(pathSplit));
        }else if (filePath.endsWith(".jar")) {//截取路径中的jar包名,可执行jar包运行的结果里包含".jar"
            filePath = filePath.substring(0, filePath.lastIndexOf(File.separator) + 1);
        }
        System.out.println("jar包所在目录："+filePath);


        System.out.println("自动催办工具运行");

        System.out.println("读取铁塔动环工单");
//        DataReader dataOneReader = new DataReader("D:\\javalearn\\project\\exwork\\workfloder\\铁塔动环工单输入表.xls");
        DataReader dataOneReader = new DataReader("E:\\JavaProject\\铁塔动环工单输入表.xls");
        dataOneReader.readData();

        System.out.println("读取配置文件");
        ConfigReader configReader = new ConfigReader("D:\\javalearn\\project\\exwork\\workfloder\\配置文件.xls");

        System.out.println("处理数据");
        //报表1A
        HandleData handleOneAData = new HandleOneAData(dataOneReader);
        handleOneAData.handleData();

        //报表1B
        HandleData handleOneBData = new HandleOneBData(dataOneReader);
        handleOneBData.handleData();

        System.out.println("处理结束");

        System.out.println("输出接单催办表、回单催办表");
        BaseExcel baseExcel = new BaseExcel();

        //输入表1的输出
        BaseSheet baseSheet1A = new BaseSheet();
        baseSheet1A.setBaseData(handleOneAData.getAllBaseData());
        baseSheet1A.setDataTitle(handleOneAData.getDataTitle());
        baseSheet1A.setSheetName("Test1");

        BaseSheet baseSheet1B = new BaseSheet();
        baseSheet1B.setBaseData(handleOneBData.getAllBaseData());
        baseSheet1B.setDataTitle(handleOneBData.getDataTitle());
        baseSheet1B.setSheetName("Test2");

        List<BaseSheet> baseSheets = new ArrayList<>();
        baseSheets.add(baseSheet1A);
        baseSheets.add(baseSheet1B);

        baseExcel.setSheetList(baseSheets);

//        OutFile outFile = new OutFile(baseExcel, "D:\\javalearn\\project\\exwork\\workfloder\\自动催办.xls");
        OutFile outFile = new OutFile(baseExcel, "E:\\JavaProject\\自动催办.xls");
        outFile.writeFile();

        System.out.println("归档文件");
        PlaceFile placeOneFile = new PlaceFile("E:\\JavaProject\\铁塔动环工单输入表.xls", "E:\\JavaProject\\归档数据","铁塔动环工单输入表电信输入表.xls");
        PlaceFile placeTwoFile = new PlaceFile("E:\\JavaProject\\联通输入表.xls", "E:\\JavaProject\\归档数据","联通输入表.xls");
        PlaceFile placeThreeFile = new PlaceFile("E:\\JavaProject\\电信输入表.xls", "E:\\JavaProject\\归档数据","电信输入表.xls");
        placeOneFile.copyfile();
        placeTwoFile.copyfile();
        placeThreeFile.copyfile();
    }
}
