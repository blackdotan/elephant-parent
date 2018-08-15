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
public class FontImageTest {
    @Test
    public void test() throws Exception {
        BufferedImage images = FontImageHelper.characters2Image("Hello World!", 200 ,100);
        ImageIO.write(images, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("a.png")));
    }

    @Test
    public void test2() throws Exception {
        BufferedImage images = FontImageHelper.characters2Image("Hello World!", "Ryan",
                new Font("宋体", Font.BOLD, 20),
                new Font("宋体", Font.BOLD, 15),
                400 ,200);
        ImageIO.write(images, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("a.png")));
    }
}
