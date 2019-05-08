package com.company.model;

import com.company.bean.RowData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HandleOneData extends HandleData {

    public HandleOneData(DataReader dataOneReader) {
        super(dataOneReader);
    }

    @Override
    public void handleData() {
        hanldeBaseTitle();
        handleBaseData();
    }

    /**
     * 1、如果原始表中对 “接单时间”为空
     * 2、计算当前时间减”派单时间”的差；
     * 3、加可设置筛选条件（分钟），得出结果表-接单催办表；
     * 4、生成报表
     *
     * 接单时间--字段为空，第5个字段，index4
     * 派单时间--字段不为空，第4个字段，index3
     */
    @Override
    public void handleBaseData() {
        List<RowData> handleData = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        //筛选接单时间为空的数据,根据配置文件筛选,字符串转时间类型,时间类型计算时间差
        for (RowData rowData : dataReader.getDataList().getAllRowList()) {
            //第5个字段为空，第4个字段不为空
            if ("".equals(rowData.getOneRowList().get(4)) && !("".equals(rowData.getOneRowList().get(3)))) {
                try {
                    Date date = sdf.parse(rowData.getOneRowList().get(3));
                    long datetime = date.getTime();
                    long difference = currentTime - datetime;
                    long minute = difference / (1000 * 60);
                    //TODO 这里的时间需要从配置文件中获取
                    if (minute >= 11430) {
                        handleData.add(rowData);
                        System.out.println(minute + ";" + rowData.getOneRowList().get(3));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        //保存输出数据
        allBaseData.setAllRowList(handleData);
    }

    @Override
    public void hanldeBaseTitle() {
        List<String> tempTitleList = new ArrayList<>();
        tempTitleList.addAll(dataReader.getDataTitle().getTitleNameList());
        dataTitle.setTitleNameList(tempTitleList);
    }
}
