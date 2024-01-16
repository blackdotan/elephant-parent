package com.blackdotan.elephant.utils;

import com.blackdotan.elephant.utils.domain.User;
import com.blackdotan.elephant.utils.reader.ExcelReader;
import com.blackdotan.elephant.utils.stream.IFunction;
import com.blackdotan.elephant.utils.writer.ExcelWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/15.
 */
public class ExcelUtilsTest {

    @Test
    public void test() throws Exception {
        String filepath = ExcelUtilsTest.class.getResource("/").getPath().concat("保安数据采集模板.xlsx");
        File file = new File(filepath);
        ExcelReader reader = ExcelUtils.reader(file);
        List<User> users = reader.readline("从业登记记录", 1, 100, new Function<Row, User>() {
            @Override
            public User apply(Row cells) {
                String name = cells.getCell(1).getStringCellValue();
                return new User();
            }
        });
        System.out.println(users.size());
    }

    @Test
    public void name() throws IOException {
        String filepath = ExcelUtilsTest.class.getResource("/").getPath().concat("保安数据采集模板.zip");
        HSSFWorkbook workbook = new HSSFWorkbook();
        ExcelWriter writer = ExcelUtils.write(workbook);
        User user = User.custom().username("Ryan").nickname("2").password("123456").build();
        List<User> users = Arrays.asList(user);
        writer.writeline("-", Arrays.asList("用户名", "昵称", "密码"), new IFunction<Row, List<String>>() {
            @Override
            public void apply(Row row, List<String> headers) {

                // 标题设置
                // 设置字体
                Font font = row.getSheet().getWorkbook().createFont();
                // 设置字体大小
                font.setFontHeightInPoints((short) 12);
                // 字体加粗
                font.setBold(true);
                // 设置字体名字
                font.setFontName("Courier New");
                // 设置样式
                CellStyle style = row.getSheet().getWorkbook().createCellStyle();
                //
                style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                // 在样式中应用设置的字体
                style.setFont(font);
                // 设置自动换行
                style.setWrapText(false);
                // 设置水平对齐的样式为居中对齐；
                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.CENTER);

                int offset = 0;
                for (String header : headers) {
                    Cell cell = row.createCell(offset++);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(header);
                    cell.setCellStyle(style);
                }
            }
        }, users, new IFunction<Row, User>() {
            @Override
            public void apply(Row row, User user) {
                int offset = 0;
                {
                    Cell cell = row.createCell(offset++);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(user.getUsername());
                }

                {
                    Cell cell = row.createCell(offset++);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(user.getNickname());
                }

                {
                    Cell cell = row.createCell(offset++);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(user.getPassword());
                }
            }
        });
//        workbook.write(new FileOutputStream(new File(filepath)));


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        byte[] content = baos.toByteArray();
        // 写入输入流
//        InputStream is = new ByteArrayInputStream(content);

        ZipUtils.FileModel model = ZipUtils.FileModel.builder()
                .name("123.xlsx")
                .type("xlsx")
                .bytes(content)
                .build();
//        workbook.getBytes()

        ZipUtils.compress(model, new FileOutputStream(new File(filepath)));

    }
}
