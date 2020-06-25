package com.ipukr.elephant.utils;

import com.ipukr.elephant.utils.reader.ExcelReader;
import com.ipukr.elephant.utils.writer.ExcelWriter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Excel文件处理工具类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/2/8.
 */
public class ExcelUtils {

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    /**
     * @param filename
     * @param ins
     * @return
     * @throws IOException
     */
    public static ExcelReader reader(String filename, InputStream ins) throws IOException {
        // 根据文件名判断文件是2003版本还是2007版本
        Workbook book = isExcel2007(filename) ? new XSSFWorkbook(ins) : new HSSFWorkbook(ins);
        return new ExcelReader(book);
    }

    /**
     * @param file
     * @return
     * @throws Exception
     */
    public static ExcelReader reader(File file) throws Exception {
        return reader(file.getName(), new FileInputStream(file));
    }

    /**
     * @return
     */
    public static ExcelWriter write() {
        return write(new HSSFWorkbook());
    }

    /**
     * @param workbook
     * @return
     */
    public static ExcelWriter write(HSSFWorkbook workbook) {
        return new ExcelWriter(workbook);
    }


    /**
     * 判断行是否空
     *
     * @param row
     * @return
     */
    public static boolean isEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK)
                return false;
        }
        return true;
    }

    /**
     * 判断单元格是否空
     *
     * @param cell
     * @return
     */
    public static boolean isEmpty(Cell cell) {
        return !(cell != null && cell.getCellTypeEnum() != CellType.BLANK);
    }


    /**
     * @param cell
     * @param def
     * @return
     */
    public static Object orElse(Cell cell, Object def) {
        if (isEmpty(cell)) {
            return def;
        } else {
            Object obj =  null;
            switch (cell.getCellTypeEnum()) {
                case BOOLEAN:
                    obj = cell.getBooleanCellValue();
                    break;
                case ERROR:
                    obj = cell.getErrorCellValue();
                    break;
                case FORMULA:
                    obj = cell.getNumericCellValue();
                    break;
                case NUMERIC:
                    obj = cell.getNumericCellValue();
                    break;
                case STRING:
                    obj = cell.getStringCellValue().trim();
                    break;
                default:
                    break;
            }
            return obj;
        }
    }


}
