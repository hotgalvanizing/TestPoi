package com.company.bean;

import java.util.List;

/**
 * 一次输出的数据
 */
public class BaseExcel {

    List<BaseSheet> sheetList;

    public List<BaseSheet> getSheetList() {
        return sheetList;
    }

    public void setSheetList(List<BaseSheet> sheetList) {
        this.sheetList = sheetList;
    }
}
