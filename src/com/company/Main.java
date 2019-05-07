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
        DataReader dataOneReader = new DataReader("D:\\javalearn\\project\\exwork\\workfloder\\铁塔动环工单输入表.xls");
        dataOneReader.readData();

        System.out.println("读取配置文件");
        ConfigReader configReader = new ConfigReader("D:\\javalearn\\project\\exwork\\workfloder\\配置文件.xls");

        System.out.println("处理数据");
        HandleData handleOneData = new HandleOneData(dataOneReader);
        System.out.println("处理结束");

        System.out.println("输出接单催办表、回单催办表");
        BaseExcel baseExcel = new BaseExcel();

        BaseSheet baseSheet = new BaseSheet();
        baseSheet.setBaseData(dataOneReader.getDataList());
        baseSheet.setDataTitle(dataOneReader.getDataTitle());
        baseSheet.setSheetName("Test");

        List<BaseSheet> baseSheets = new ArrayList<>();
        baseSheets.add(baseSheet);

        baseExcel.setSheetList(baseSheets);

        OutFile outFile = new OutFile(baseExcel, "D:\\javalearn\\project\\exwork\\workfloder\\自动催办.xls");
        outFile.writeFile();
        System.out.println("归档文件");
        PlaceFile placeFile = new PlaceFile("", "");

    }
}
