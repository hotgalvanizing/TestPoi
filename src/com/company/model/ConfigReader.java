package com.company.model;

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

/**
 * 读配置文件
 */
public class ConfigReader {

    /**
     * 读取文件的地址
     */
    String url;

    public List<String> getOneDataTime() {
        return oneDataTime;
    }

    List<String> oneDataTime = new ArrayList<>();

    public ConfigReader(String url) {
        this.url = url;
    }

    public void readConfig() {

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
                HSSFWorkbook wk = new HSSFWorkbook(fs);
                //得到Excel工作簿的第一页，即excel工作表对象
                HSSFSheet sheet = wk.getSheetAt(0);
                //遍历行对象
                for (Row row : sheet) {
                    //第1行是表头
                    if (row.getRowNum() == 0) {
                        //表头
                    } else {
                        //数据项
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
                                    //TODO
                                    break;
                                case BOOLEAN:
                                    //TODO
                                    break;
                                case FORMULA:
                                    //TODO
                                    break;
                                case NUMERIC:
                                    //TODO
                                    break;
                                default:
                            }
                        }
                    }
                }
            } else {
                System.out.println("请确认是否正确放入配置文件");
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
