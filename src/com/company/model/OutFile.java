package com.company.model;

import com.company.bean.BaseExcel;
import com.company.bean.BaseSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 输出文件类
 */
public class OutFile {

    /**
     * 读取文件的地址
     */
    String url;

    /**
     * 需要输出的所有数据
     */
    BaseExcel baseExcel;


    public OutFile(BaseExcel baseExcel, String url) {
        this.url = url;
        this.baseExcel = baseExcel;
    }

    public void writeFile() {

        File file = new File(url);

        //获得Excel文件输出流
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(file);

            //循环创建需要的所有sheet
            for (BaseSheet baseSheet : baseExcel.getSheetList()) {
                //创建excel工作簿对象
                HSSFWorkbook wb = new HSSFWorkbook();
                //创建excel页
                HSSFSheet sheet = wb.createSheet(baseSheet.getSheetName());

                //创建表头
                HSSFRow row1 = sheet.createRow(0);
                for (int i = 0; i < baseSheet.getDataTitle().getTitleNameList().size(); i++) {
                    HSSFCell cell = row1.createCell(i);
                    cell.setCellValue(baseSheet.getDataTitle().getTitleNameList().get(i));
                }
                //写入数据
                for (int i = 0; i < baseSheet.getBaseData().getAllRowList().size(); i++) {
                    HSSFRow row = sheet.createRow(i + 1);
                    for (int j = 0; j < baseSheet.getBaseData().getAllRowList().get(i).getOneRowList().size(); j++) {
                        HSSFCell cell = row.createCell(j);
                        cell.setCellValue(baseSheet.getBaseData().getAllRowList().get(i).getOneRowList().get(j));
                    }
                }
                wb.write(out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
