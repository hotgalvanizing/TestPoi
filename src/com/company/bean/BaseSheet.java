package com.company.bean;

/**
 * 1个sheet的数据，表头和数据
 */
public class BaseSheet {

    String sheetName;

    DataTitle dataTitle;

    BaseData baseData;

    public DataTitle getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(DataTitle dataTitle) {
        this.dataTitle = dataTitle;
    }

    public BaseData getBaseData() {
        return baseData;
    }

    public void setBaseData(BaseData baseData) {
        this.baseData = baseData;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
