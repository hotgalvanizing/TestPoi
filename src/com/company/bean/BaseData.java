package com.company.bean;

import java.util.List;

/**
 * 电子表格中的数据部分
 */
public class BaseData {

    List<RowData> allRowList;

    public List<RowData> getAllRowList() {
        return allRowList;
    }

    public void setAllRowList(List<RowData> allRowList) {
        this.allRowList = allRowList;
    }
}
