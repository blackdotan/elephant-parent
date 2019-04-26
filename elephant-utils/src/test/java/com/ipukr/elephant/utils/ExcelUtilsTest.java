package com.ipukr.elephant.utils;

import com.ipukr.elephant.utils.domain.User;
import com.ipukr.elephant.utils.reader.ExcelReader;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
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
}
