package com.company.model;

import com.company.bean.BaseData;
import com.company.bean.DataTitle;
import com.company.bean.RowData;
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
     * 保存所有数据
     */
    private BaseData allBaseData;

    /**
     * 测试数据类型
     */
    private Set<String> testSet;

    public Set<String> getTestSet() {
        return testSet;
    }

    public DataTitle getDataTitle() {
        return dataTitle;
    }

    public BaseData getDataList() {
        return allBaseData;
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
                List<RowData> dataList = new ArrayList<>();
                //遍历行对象
                //TODO 遍历过程需要判断电子表格是否有空行，需要判断表头在哪一行
                for (Row row : sheet) {
                    //第1行是表头
                    if (row.getRowNum() == 0) {
                        dataTitle = new DataTitle();
                        List<String> titleList = new ArrayList<>();
                        for (Cell cell : row) {
                            titleList.add(cell.getStringCellValue());
                        }
                        dataTitle.setTitleNameList(titleList);
                    } else {
                        RowData rowData = new RowData();
                        List<String> rowDataList = new ArrayList<>();
                        for (Cell cell : row) {
                            CellType cellType = cell.getCellType();
                            switch (cellType) {
                                case _NONE:
                                    //TODO
                                    break;
                                case BLANK:
                                    //TODO
                                    break;
                                case ERROR:
                                    //TODO
                                    break;
                                case STRING:
                                    rowDataList.add(cell.getStringCellValue());
                                    break;
                                case BOOLEAN:
                                    //TODO
                                    break;
                                case FORMULA:
                                    //TODO
                                    break;
                                case NUMERIC:
                                    rowDataList.add(Double.toString(cell.getNumericCellValue()));
                                    break;
                                default:
                            }
                        }
                        rowData.setOneRowList(rowDataList);
                        dataList.add(rowData);
                    }
                }
                //遍历表头和数据后
                allBaseData = new BaseData();
                allBaseData.setAllRowList(dataList);
            } else {
                System.out.println("请确认是否正确放入文件");
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
