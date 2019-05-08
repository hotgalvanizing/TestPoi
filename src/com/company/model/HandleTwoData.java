package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class HandleTwoData extends HandleData{
    public HandleTwoData(DataReader dataOneReader) {
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
     * 4、在接单催办表中，根据“所属地市”判断，通过微信发送给不同处理人，
     * 微信发送模块可以设置不同地市接收人或指定群内根据所属地市判断后@不同人员微信）
     * <p>
     * 5、如果接单时间不为空；
     * 6、计算“接单时间”减“派单时间”与的差；
     * 7、加可设置筛选条件（分钟），得出结果表-回单催办表
     * 8、在回单催办表中，根据“所属地市”判断，通过微信发送给不同处理人，
     * 微信发送模块可以设置不同地市接收人或指定群内根据所属地市判断后@不同人员微信）
     */

    @Override
    public void handleBaseData() {

    }

    @Override
    public void hanldeBaseTitle() {
        List<String> tempTitleList = new ArrayList<>();
        tempTitleList.addAll(dataReader.getDataTitle().getTitleNameList());
        dataTitle.setTitleNameList(tempTitleList);
    }
}
