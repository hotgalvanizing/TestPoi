package com.company;

import com.company.bean.BaseData;
import com.company.model.DataReader;

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
     * case 1:
     * 如果判断为铁塔动环工单：执行下列操作
     *
     * 1、如果原始表中对 “接单时间”为空
     * 2、计算当前时间减”派单时间”的差；
     * 3、加可设置筛选条件（分钟），得出结果表-接单催办表；
     *
     * 5、如果接单时间不为空；
     * 6、计算“接单时间”减“派单时间”与的差；
     * 7、加可设置筛选条件（分钟），得出结果表-回单催办表
     */

    /**
     * 入口函数
     *
     * @param args
     */
    public static void main(String[] args) {

        DataReader dataOneReader = new DataReader("D:\\javalearn\\project\\exwork\\workfloder\\铁塔动环工单输入表.xls");
        dataOneReader.readData();
        List<BaseData> list = dataOneReader.getDataList();
        BaseData ba = list.get(0);
        for (String s:ba.getOneRowList()) {
            if ("".equals(s)){
                System.out.println("我说空字符串");
            }
        }





    }
}
