package com.ipukr.elephant.utils.writer;

import com.ipukr.elephant.utils.stream.IFunction;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExcelWriter {

    /**
     *
     */
    private Workbook book;

    /**
     * @param book
     */
    public ExcelWriter(Workbook book) {
        this.book = book;
    }

    /**
     * @param sheetName
     * @param function
     * @param <T>
     * @return
     */
    public <T> void writeline(String sheetName,
                              List<String> headers,
                              IFunction<Row, List<String>> hfuction,
                              List<T> data,
                              IFunction<Row, T> function) {
        Sheet sheet = book.createSheet(sheetName);
        int offset = 0;

        // 表格头设置
        {
            Row row = sheet.createRow(sheet.getLastRowNum());
            hfuction.apply(row, headers);
        }

        // 表格内容填充
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(sheet.getLastRowNum()+1);
            function.apply(row, data.get(i));
        }

    }


}
