package com.blackdotan.elephant.utils.writer;

import com.blackdotan.elephant.utils.stream.IFunction;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

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
