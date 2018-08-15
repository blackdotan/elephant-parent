package com.ipukr.elephant.qr;

import com.google.zxing.WriterException;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/13.
 */
public class QrImageHelperTest {
    @Test
    public void test() throws IOException, WriterException {
        BufferedImage image = QrImageHelper.write("你好", 200, 200);
        ImageIO.write(image, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("b.png")));
    }
}
