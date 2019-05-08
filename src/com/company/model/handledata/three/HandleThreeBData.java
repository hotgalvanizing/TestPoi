package com.company.model.handledata.three;

import com.company.bean.RowData;
import com.company.model.DataReader;
import com.company.model.handledata.HandleData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HandleThreeBData extends HandleData {


    public HandleThreeBData(DataReader dataOneReader) {
        super(dataOneReader);
    }

    @Override
    public void handleData() {
        hanldeBaseTitle();
        handleBaseData();
    }

    /**
     * 1、如果“状态”为“新建”，生成电信接单催办表；
     * 2、在电信接单催办表中，根据据“告警发生地市”，
     * 通过微信发送给不同处理人（微信发送模块可以设置不同地市接收人或指定群内根据所属地市判断后@不同人员微信）
     *
     * 3、如果“状态”为“处理中”且“系统记录故障恢复时间”有值，生成电信回单催办表；
     * 4、在电信回单催办表中，根据“告警发生地市”判断，通过微信发送给不同处理人，
     * 微信发送模块可以设置不同地市接收人或指定群内根据所属地市判断后@不同人员微信）；
     *
     * 5、如果如果“状态”为“处理中”且“系统记录故障恢复时间”为空
     * 6、计算当前时间减“创建时间”的差；
     * 7、加可设置筛选条件（分钟），得出结果表-电信处理催办表；
     * 8、通过电信催办表中的“告警发生地市”判断，然后通过微信发送给不同处理人
     * （微信发送模块可以设置不同地市接收人或指定群内根据所属地市判断后@不同人员微信）
     */
    @Override
    public void handleBaseData() {
        List<RowData> handleData = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //筛选接单时间为空的数据,根据配置文件筛选,字符串转时间类型,时间类型计算时间差
        for (RowData rowData : dataReader.getDataList().getAllRowList()) {
            //第5个字段不为空，第4个字段不为空
            if (!("".equals(rowData.getOneRowList().get(4))) &&  !("".equals(rowData.getOneRowList().get(3)))) {
                try {
                    Date date1 = sdf.parse(rowData.getOneRowList().get(4));
                    Date date2 = sdf.parse(rowData.getOneRowList().get(3));
                    long datetime1 = date1.getTime();
                    long datetime2 = date2.getTime();
                    long difference = datetime1 - datetime2;
                    long minute = difference / (1000 * 60);
                    //TODO 这里的时间需要从配置文件中获取
                    if (minute >= 14400) {
                        handleData.add(rowData);
                        System.out.println(minute + ";" + rowData.getOneRowList().get(4));
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
