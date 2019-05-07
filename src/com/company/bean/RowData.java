package com.company.bean;

import java.util.List;

/**
 * 电子表格中一行数据
 */
public class RowData {

    /**
     * 保存一行数据
     */
    List<String> oneRowList;

    public List<String> getOneRowList() {
        return oneRowList;
    }

    public void setOneRowList(List<String> oneRowList) {
        this.oneRowList = oneRowList;
    }
}
