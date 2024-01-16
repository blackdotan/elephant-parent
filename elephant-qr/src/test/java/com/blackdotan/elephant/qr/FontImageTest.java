package com.blackdotan.elephant.qr;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
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
public class FontImageTest {

    private int WIDTH = 826;
    private int HEIGHT = 354;

    /**
     * 测试输出字体图片
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        BufferedImage images = FontImageHelper.characters2Image(" 富盾保安集团 ", 826 ,354);
        ImageIO.write(images, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("a.png")));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        BufferedImage images = FontImageHelper.characters2Image("Hello World!", "Ryan",
                new Font("宋体", Font.BOLD, 20),
                new Font("宋体", Font.BOLD, 15),
                400 ,200);
        ImageIO.write(images, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("a.png")));
    }

    @Test
    public void test3() throws IOException {
        Font font = new Font("宋体", Font.BOLD, 50);
        BufferedImage images = FontImageHelper.draw(" 富盾保安集团 ", 826 ,354, font);
        ImageIO.write(images, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("d.png")));
    }

    /**
     * 测试输出字体图片，测试对齐规则
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        BufferedImage image = new BufferedImage(826 , 354, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = image.createGraphics();
        Font font = new Font("宋体", Font.BOLD, 30);
        graphics.setFont(font);
        FontImageHelper.drawAlignmentString(graphics, "富盾", 210, 0, 300,  CharactersAlignmentFormat.居中);
        FontImageHelper.drawAlignmentString(graphics, "富盾", 210, 30, 300,  CharactersAlignmentFormat.左对齐);
        FontImageHelper.drawAlignmentString(graphics, "富盾", 210, 60, 300,  CharactersAlignmentFormat.右对齐);
        FontImageHelper.drawAlignmentString(graphics, "富盾保", 210, 90, 300,  CharactersAlignmentFormat.左右对齐);
        FontImageHelper.drawAlignmentString(graphics, "富盾保安", 210, 120, 300,  CharactersAlignmentFormat.左右对齐);
        FontImageHelper.drawAlignmentString(graphics, "富盾保安公", 210, 150, 300,  CharactersAlignmentFormat.左右对齐);
        FontImageHelper.drawAlignmentString(graphics, "富盾保安公司", 210, 180, 300,  CharactersAlignmentFormat.左右对齐);

        graphics.dispose();
        ImageIO.write(image, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("e.png")));
    }

    /**
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        int width = 826;
        int height = 354;
        // 主图片：先用黑色填充整张图片,也就是背景

        BufferedImage image = new BufferedImage(width , height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        Color color = new Color(0, 91, 162);
        graphics.setColor(color);
        graphics.fillRect(0, 0, width, height);

        // 二维码
        BufferedImage lbi = QrImageHelper.generate("你好", 150, 150);
        graphics.drawImage(lbi, 20, (height -150)/2, 150, 150, null);
        // 文字部分
        Font font1 = new Font("黑体", Font.BOLD, 100);
        graphics.setFont(font1);
        graphics.setColor(Color.WHITE);
        FontImageHelper.drawAlignmentString(graphics, "富盾保安公司",190, 60, 616,  CharactersAlignmentFormat.左右对齐);
        Font font2 = new Font("黑体", Font.BOLD, 80);
        graphics.setFont(font2);
        graphics.setColor(Color.WHITE);
        FontImageHelper.drawAlignmentString(graphics, "P02161",190, 190, 616,  CharactersAlignmentFormat.左右对齐);
        graphics.dispose();

        ImageIO.write(image, "PNG", new File(FontImageTest.class.getResource("/").getPath().concat("ab.png")));
    }

}
