package com.company.model;

/**
 * 数据处理超类类
 */
public abstract class HandleData {

    protected DataReader dataReader;

    public HandleData(DataReader dataOneReader) {
        this.dataReader = dataOneReader;
    }


}
