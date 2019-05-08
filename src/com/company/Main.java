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

        System.out.println("读取铁塔动环工单");
//        DataReader dataOneReader = new DataReader("D:\\javalearn\\project\\exwork\\workfloder\\铁塔动环工单输入表.xls");
        DataReader dataTwoReader = new DataReader("E:\\JavaProject\\联通输入表.xls");
        dataTwoReader.readData();

        System.out.println("读取铁塔动环工单");
//        DataReader dataOneReader = new DataReader("D:\\javalearn\\project\\exwork\\workfloder\\铁塔动环工单输入表.xls");
        DataReader dataThreeReader = new DataReader("E:\\JavaProject\\电信输入表.xls");
        dataThreeReader.readData();

        System.out.println("读取配置文件");
        ConfigReader configReader = new ConfigReader("D:\\javalearn\\project\\exwork\\workfloder\\配置文件.xls");

        System.out.println("处理数据");
        //报表1A
        HandleData handleOneAData = new HandleOneAData(dataOneReader);
        handleOneAData.handleData();

        //报表1B
        HandleData handleOneBData = new HandleOneBData(dataOneReader);
        handleOneBData.handleData();

        //报表2A
        HandleData handleTwoAData = new HandleTwoAData(dataOneReader);
        handleOneBData.handleData();

        //报表2B
        HandleData handleTwoBData = new HandleTwoBData(dataOneReader);
        handleOneBData.handleData();

        //报表2C
        HandleData handleTwoCData = new HandleTwoCData(dataOneReader);
        handleOneBData.handleData();

        //报表2A
        HandleData handleThreeAData = new HandleThreeAData(dataOneReader);
        handleOneBData.handleData();

        //报表2B
        HandleData handleThreeBData = new HandleThreeBData(dataOneReader);
        handleOneBData.handleData();

        //报表2C
        HandleData handleThreeCData = new HandleThreeCData(dataOneReader);
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

        //输入表2的输出
        BaseSheet baseSheet2A = new BaseSheet();
        baseSheet2A.setBaseData(handleOneAData.getAllBaseData());
        baseSheet2A.setDataTitle(handleOneAData.getDataTitle());
        baseSheet2A.setSheetName("Test1");

        BaseSheet baseSheet2B = new BaseSheet();
        baseSheet2B.setBaseData(handleOneBData.getAllBaseData());
        baseSheet2B.setDataTitle(handleOneBData.getDataTitle());
        baseSheet2B.setSheetName("Test2");

        BaseSheet baseSheet2C = new BaseSheet();
        baseSheet2C.setBaseData(handleOneBData.getAllBaseData());
        baseSheet2C.setDataTitle(handleOneBData.getDataTitle());
        baseSheet2C.setSheetName("Test2");

        //输入表3的输出
        BaseSheet baseSheet3A = new BaseSheet();
        baseSheet3A.setBaseData(handleOneAData.getAllBaseData());
        baseSheet3A.setDataTitle(handleOneAData.getDataTitle());
        baseSheet3A.setSheetName("Test1");

        BaseSheet baseSheet3B = new BaseSheet();
        baseSheet3B.setBaseData(handleOneBData.getAllBaseData());
        baseSheet3B.setDataTitle(handleOneBData.getDataTitle());
        baseSheet3B.setSheetName("Test2");

        BaseSheet baseSheet3C = new BaseSheet();
        baseSheet3C.setBaseData(handleOneBData.getAllBaseData());
        baseSheet3C.setDataTitle(handleOneBData.getDataTitle());
        baseSheet3C.setSheetName("Test2");

        List<BaseSheet> baseSheets = new ArrayList<>();
        baseSheets.add(baseSheet1A);
        baseSheets.add(baseSheet1B);

        baseSheets.add(baseSheet2A);
        baseSheets.add(baseSheet2B);
        baseSheets.add(baseSheet2C);

        baseSheets.add(baseSheet3A);
        baseSheets.add(baseSheet3B);
        baseSheets.add(baseSheet3C);

        baseExcel.setSheetList(baseSheets);

//        OutFile outFile = new OutFile(baseExcel, "D:\\javalearn\\project\\exwork\\workfloder\\自动催办.xls");
        OutFile outFile = new OutFile(baseExcel, "E:\\JavaProject\\自动催办.xls");
        outFile.writeFile();

        System.out.println("归档文件");
        PlaceFile placeFile = new PlaceFile("", "");

    }
}
