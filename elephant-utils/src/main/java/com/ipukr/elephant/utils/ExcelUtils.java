package com.ipukr.elephant.utils;

import com.ipukr.elephant.utils.reader.ExcelReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    public static ExcelReader reader(String filename, InputStream ins) throws IOException {
        // 根据文件名判断文件是2003版本还是2007版本
        Workbook book = isExcel2007(filename) ? new XSSFWorkbook(ins) : new HSSFWorkbook(ins);
        return new ExcelReader(book);
    }

    public static ExcelReader reader(File file) throws Exception {
        return reader(file.getName(), new FileInputStream(file));
    }

}
