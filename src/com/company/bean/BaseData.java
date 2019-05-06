package com.company.bean;

import java.util.List;

/**
 * 数据表基础类
 */
public class BaseData {

    /**
     * 保存一行数据
     */
    List<String> otherNameList;

    public List<String> getOtherNameList() {
        return otherNameList;
    }

    public void setOtherNameList(List<String> otherNameList) {
        this.otherNameList = otherNameList;
    }
}
