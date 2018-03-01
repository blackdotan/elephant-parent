package com.ipukr.elephant.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Excel文件处理工具类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/2/8.
 */
public class ExcelUtils {

    /**
     * 读取Excel文件Cell
     * @param file Excel文件
     * @return Cell表格
     * @throws IOException 文件读取IO流异常
     * @throws InvalidFormatException Excel文件格式异常
     */
    public static List<List<Cell>> read(File file) throws IOException, InvalidFormatException {
        return read(file, 0);
    }


    /**
     * 读取Excel文件Cell
     * @param file Excel文件
     * @param sheet sheet页
     * @return Cell表格
     * @throws IOException 文件读取IO流异常
     * @throws InvalidFormatException Excel文件格式异常
     *
     * Maven Resource异常： ZIP entry size is too large /原因 Maven Resource Filter时会过滤Excel文件，导致损坏
     *
     */
    public static List<List<Cell>> read(File file, int sheet) throws IOException, InvalidFormatException {
        List<List<Cell>> rows = new ArrayList<List<Cell>>();
        InputStream inp = new FileInputStream(file);
        //根据上述创建的输入流 创建工作簿对象
        Workbook wb = WorkbookFactory.create(inp);
        //得到第一页 sheet
        //页Sheet是从0开始索引的
        Sheet wbsheet = wb.getSheetAt(sheet);
        //利用foreach循环 遍历sheet中的所有行
        Row header = wbsheet.getRow(0);
        Iterator<Cell> it = header.iterator();
        for (int i = 1;i<= wbsheet.getLastRowNum(); i++) {
            List<Cell> row = new ArrayList<Cell>();
            for (Cell cell : wbsheet.getRow(i)) {
                row.add(cell);
            }
            rows.add(row);
        }
        return rows;
    }

}
