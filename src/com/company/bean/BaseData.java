package com.company.bean;

import java.util.List;

/**
 * 数据表基础类
 */
public class BaseData {

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
