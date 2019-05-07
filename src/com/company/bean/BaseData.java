package com.company.bean;

import java.util.List;

/**
 * 电子表格中的数据部分
 */
public class BaseData {

    List<RowData> oneRowList;

    public List<RowData> getOneRowList() {
        return oneRowList;
    }

    public void setOneRowList(List<RowData> oneRowList) {
        this.oneRowList = oneRowList;
    }
}
