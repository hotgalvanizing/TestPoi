package com.company;

import com.company.bean.BaseExcel;
import com.company.bean.BaseSheet;
import com.company.model.ConfigReader;
import com.company.model.DataReader;
import com.company.model.OutFile;
import com.company.model.PlaceFile;
import com.company.model.handledata.HandleData;
import com.company.model.handledata.one.HandleOneAData;
import com.company.model.handledata.one.HandleOneBData;
import com.company.util.Util;

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

        String rootPath = Util.getPath();
        System.out.println("自动催办工具运行");

        System.out.println("读取铁塔动环工单");
        String dataOneUrl = rootPath + File.separator + "输入文件" + File.separator + "铁塔动环工单输入表.xls";
        DataReader dataOneReader = new DataReader(dataOneUrl);
        dataOneReader.readData();

        System.out.println("读取配置文件");
        String configUrl = rootPath + File.separator + "配置文件" + File.separator + "配置文件.xls";
        ConfigReader configReader = new ConfigReader(configUrl);
        configReader.readConfig();

        System.out.println("处理数据");
        //报表1A
        HandleData handleOneAData = new HandleOneAData(dataOneReader, configReader);
        handleOneAData.handleData();

        //报表1B
        HandleData handleOneBData = new HandleOneBData(dataOneReader, configReader);
        handleOneBData.handleData();

        System.out.println("处理结束");

        System.out.println("输出接单催办表、回单催办表");
        BaseExcel baseExcel = new BaseExcel();

        //输入表1的输出
        BaseSheet baseSheet1A = new BaseSheet();
        baseSheet1A.setBaseData(handleOneAData.getAllBaseData());
        baseSheet1A.setDataTitle(handleOneAData.getDataTitle());
        baseSheet1A.setSheetName("接单催办表");

        BaseSheet baseSheet1B = new BaseSheet();
        baseSheet1B.setBaseData(handleOneBData.getAllBaseData());
        baseSheet1B.setDataTitle(handleOneBData.getDataTitle());
        baseSheet1B.setSheetName("回单催办表");

        List<BaseSheet> baseSheets = new ArrayList<>();
        baseSheets.add(baseSheet1A);
        baseSheets.add(baseSheet1B);

        baseExcel.setSheetList(baseSheets);

        String outFileUrl = rootPath + File.separator + "输出文件" + File.separator + "自动催办.xls";
        OutFile outFile = new OutFile(baseExcel, outFileUrl);
        outFile.writeFile();

        System.out.println("归档文件");
        String oldFileUrl = rootPath + File.separator + "输入文件" + File.separator + "铁塔动环工单输入表.xls";
        String newFileUrl = rootPath + File.separator + "归档数据" + File.separator + "铁塔动环工单输入表" + Util.getFormatDate() + ".xls";
        PlaceFile placeOneFile = new PlaceFile(oldFileUrl, newFileUrl);
        placeOneFile.copyfile();
    }
}
