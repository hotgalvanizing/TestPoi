package com.company;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 1、初始化配置
 * 2、读取数据表
 * 3、读取配置表
 * 4、处理数据表
 * 5、生成结果表
 * 6、生成历史表
 */
public class Main {

    /**
     * case 1:
     * 如果判断为铁塔动环工单：执行下列操作
     *
     * 1、如果原始表中对 “接单时间”为空
     * 2、计算当前时间减”派单时间”的差；
     * 3、加可设置筛选条件（分钟），得出结果表-接单催办表；
     *
     * 5、如果接单时间不为空；
     * 6、计算“接单时间”减“派单时间”与的差；
     * 7、加可设置筛选条件（分钟），得出结果表-回单催办表
     */

    /**
     * 入口函数
     *
     * @param args
     */
    public static void main(String[] args) {

        //创建输入流，接受目标excel文件
        FileInputStream in = null;

        //得到POI文件系统对象
        POIFSFileSystem fs = null;

        File file = new File("E:\\JavaProject\\铁塔动环工单输入表.xls");

        try {
            in = new FileInputStream(file);
            fs = new POIFSFileSystem(in);

            if (file.isFile() && file.exists()){
                System.out.println("File ok");

                //得到Excel工作簿对象
                //TODO HSSFWorkbook与XSSFWorkbook的区别
                HSSFWorkbook wk = new HSSFWorkbook(fs);

                //得到Excel工作簿的第一页，即excel工作表对象
                HSSFSheet sheet = wk.getSheetAt(0);

                //拿到表头行
                HSSFRow row= sheet.getRow(0);
                //遍历单元格对象
                for (Cell cell : row){
                    System.out.println(cell.getStringCellValue());
                }

                //遍历行对象
//                for (Row row : sheet){
//                    //打印行索引
//                    System.out.println(row.getRowNum());
//
//                }


            }else {
                //TODO 写入日志
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
