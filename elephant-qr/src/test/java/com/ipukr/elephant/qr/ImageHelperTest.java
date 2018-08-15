package com.ipukr.elephant.qr;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/13.
 */
public class ImageHelperTest {
    @Test
    public void test() throws Exception {
        BufferedImage image1 = FontImageHelper.characters2Image("富盾集团", "P02161",
                new Font("宋体", Font.BOLD, 30),
                new Font("宋体", Font.BOLD, 20),
                400 ,200);
        BufferedImage image2 = QrImageHelper.write("你好", 200, 200);
        BufferedImage image = ImageHelper.concat(image1, image2);

        ImageIO.write(image, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("c.png")));
    }
}
