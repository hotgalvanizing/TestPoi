package com.company.model.handledata.one;

import com.company.bean.RowData;
import com.company.model.DataReader;
import com.company.model.handledata.HandleData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HandleOneBData extends HandleData {
    public HandleOneBData(DataReader dataOneReader) {
        super(dataOneReader);
    }

    @Override
    public void handleData() {
        hanldeBaseTitle();
        handleBaseData();
    }

    /**
     * 5、如果接单时间不为空；
     * 6、计算“接单时间”减“派单时间”与的差；
     * 7、加可设置筛选条件（分钟），得出结果表-回单催办表
     * 4、生成报表
     *
     * 接单时间--字段为空，第5个字段，index4
     * 派单时间--字段不为空，第4个字段，index3
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
