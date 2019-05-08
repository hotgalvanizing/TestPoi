package com.company;

import com.company.bean.BaseData;
import com.company.bean.BaseExcel;
import com.company.bean.BaseSheet;
import com.company.bean.DataTitle;
import com.company.model.*;

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

        System.out.println("自动催办工具运行");

        System.out.println("读取铁塔动环工单");
//        DataReader dataOneReader = new DataReader("D:\\javalearn\\project\\exwork\\workfloder\\铁塔动环工单输入表.xls");
        DataReader dataOneReader = new DataReader("E:\\JavaProject\\铁塔动环工单输入表.xls");
        dataOneReader.readData();

        System.out.println("读取配置文件");
        ConfigReader configReader = new ConfigReader("D:\\javalearn\\project\\exwork\\workfloder\\配置文件.xls");

        System.out.println("处理数据");
        //报表1
        HandleData handleOneData = new HandleOneData(dataOneReader);
        handleOneData.handleData();

        //报表2
        HandleData handleTwoData = new HandleTwoData(dataOneReader);
        handleTwoData.handleData();
        System.out.println("处理结束");

        System.out.println("输出接单催办表、回单催办表");
        BaseExcel baseExcel = new BaseExcel();

        BaseSheet baseSheet1 = new BaseSheet();
        baseSheet1.setBaseData(handleOneData.getAllBaseData());
        baseSheet1.setDataTitle(handleOneData.getDataTitle());
        baseSheet1.setSheetName("Test1");

        BaseSheet baseSheet2 = new BaseSheet();
        baseSheet2.setBaseData(handleTwoData.getAllBaseData());
        baseSheet2.setDataTitle(handleTwoData.getDataTitle());
        baseSheet2.setSheetName("Test2");

        List<BaseSheet> baseSheets = new ArrayList<>();
        baseSheets.add(baseSheet1);
        baseSheets.add(baseSheet2);

        baseExcel.setSheetList(baseSheets);

//        OutFile outFile = new OutFile(baseExcel, "D:\\javalearn\\project\\exwork\\workfloder\\自动催办.xls");
        OutFile outFile = new OutFile(baseExcel, "E:\\JavaProject\\自动催办.xls");
        outFile.writeFile();
        System.out.println("归档文件");
        PlaceFile placeFile = new PlaceFile("", "");

    }
}
