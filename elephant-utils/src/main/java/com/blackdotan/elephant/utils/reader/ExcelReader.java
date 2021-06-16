package com.blackdotan.elephant.utils.reader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/15.
 */
public class ExcelReader implements Readable {

    private Workbook book;

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }

    public ExcelReader(Workbook book) {
        this.book = book;
    }

    /**
     * @param sheetName
     * @param function
     * @param <T>
     * @return
     */
    public <T> List<T> readline(String sheetName, Function<Row, T> function) {
        return readline(sheetName, 0, Integer.MAX_VALUE, function);
    }


    /**
     * @param sheetName
     * @param begin
     * @param end
     * @param function
     * @param <T>
     * @return
     */
    public <T> List<T> readline(String sheetName, int begin, int end, Function<Row, T> function) {
        Sheet sheet = book.getSheet(sheetName);

        // 读取起始行（包含）
        begin = Math.max(begin, sheet.getFirstRowNum());
        // 读取结束行（包含）
        end = Math.min(end, sheet.getLastRowNum());

        List<T> arr = new ArrayList<T>();
        for (int i = begin; i <= end; i++) {
            T ins = function.apply(sheet.getRow(i));
            if ( ins != null ) {
                arr.add(ins);
            }
        }
        return arr;
    }


}
