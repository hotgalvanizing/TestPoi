package com.company.model.handledata;

import com.company.bean.BaseData;
import com.company.bean.DataTitle;
import com.company.model.DataReader;

/**
 * 数据处理超类类
 */
public abstract class HandleData {

    /**
     * 用于获取读入的数据
     */
    protected DataReader dataReader;

    /**
     * 保存输出表头信息
     */
    protected DataTitle dataTitle;

    /**
     * 保存输出所有数据
     */
    protected BaseData allBaseData;

    public HandleData(DataReader dataOneReader) {
        this.dataReader = dataOneReader;
        dataTitle = new DataTitle();
        allBaseData = new BaseData();
    }

    public abstract void handleData();

    public abstract void handleBaseData();

    public abstract void hanldeBaseTitle();

    public DataTitle getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(DataTitle dataTitle) {
        this.dataTitle = dataTitle;
    }

    public BaseData getAllBaseData() {
        return allBaseData;
    }

    public void setAllBaseData(BaseData allBaseData) {
        this.allBaseData = allBaseData;
    }
}
