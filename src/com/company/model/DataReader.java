package com.company.model;

import com.company.bean.BaseData;
import com.company.bean.DataOne;
import com.company.bean.DataTitle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 读数据类：读数据表
 */
public class DataReader {

    /**
     * 保存表头信息
     */
    private DataTitle dataTitle;
    /**
     * 保存一行数据
     */
    private List<BaseData> dataList = new ArrayList<>();

    /**
     * 测试数据类型
     */
    private Set<String> testSet;

    public Set<String> getTestSet() {
        return testSet;
    }

    public void setTestSet(Set<String> testSet) {
        this.testSet = testSet;
    }

    public DataTitle getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(DataTitle dataTitle) {
        this.dataTitle = dataTitle;
    }

    public List<BaseData> getDataList() {
        return dataList;
    }

    public void setDataList(List<BaseData> dataList) {
        this.dataList = dataList;
    }

    /**
     * 读取文件的地址
     */
    String url;

    public DataReader(String url) {
        this.url = url;
    }

    public void readData() {

        //创建输入流，接受目标excel文件
        FileInputStream in = null;

        //得到POI文件系统对象
        POIFSFileSystem fs = null;

        File file = new File(url);

        try {
            in = new FileInputStream(file);
            fs = new POIFSFileSystem(in);

            if (file.isFile() && file.exists()) {

                //得到Excel工作簿对象
                //TODO HSSFWorkbook与XSSFWorkbook的区别
                HSSFWorkbook wk = new HSSFWorkbook(fs);

                //得到Excel工作簿的第一页，即excel工作表对象
                HSSFSheet sheet = wk.getSheetAt(0);

                //遍历行对象
                //TODO 遍历过程需要判断电子表格是否有空行，需要判断表头在哪一行
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        //表头行
                        dataTitle = new DataTitle();
                        List<String> titleList = new ArrayList<>();
                        for (Cell cell : row) {
                            titleList.add(cell.getStringCellValue());
                        }
                        dataTitle.setTitleNameList(titleList);
                    } else {
                        //数据行
                        DataOne dataOne = new DataOne();

                        List<String> dataList = new ArrayList<>();

                        testSet = new HashSet<>();
                        //遍历单元格对象
                        for (Cell cell : row) {
                            CellType cellType = cell.getCellType();

                            if (cellType == CellType._NONE){
                                //TODO
                            }else if (cellType == CellType.NUMERIC){
                                dataList.add(Double.toString(cell.getNumericCellValue()));
                            }else if (cellType == CellType.STRING){
                                dataList.add(cell.getStringCellValue());
                            }else if (cellType == CellType.FORMULA){
                                //TODO
                            }else if (cellType == CellType.BLANK){
                                //TODO
                            }else if (cellType == CellType.BOOLEAN){
                                //TODO
                            }else if (cellType == CellType.ERROR){
                                //TODO
                            }else {
                                //TODO
                            }
                        }
                        dataOne.setOneRowList(dataList);
                        this.dataList.add(dataOne);

                    }
                }
            } else {
                //TODO 写入日志
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
