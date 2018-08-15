package com.ipukr.elephant.qr;

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
public class FontImageHelper {

    public static BufferedImage characters2Image(String characters,
                                                 Integer width,
                                                 Integer height) throws Exception {
        return characters2Image(characters, new Font("宋体", Font.BOLD, Math.min(width, height)/2 ), width, height);
    }
    /**
     * 文字转图片
     * @param characters
     * @param font
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static BufferedImage characters2Image(String characters,
                                                 Font font,
                                                 Integer width,
                                                 Integer height) throws Exception {
        return characters2Image(characters, font, Color.BLACK, Color.WHITE, width, height);
    }


    /**
     * 文字转图片
     * @param characters
     * @param font
     * @param background
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static BufferedImage characters2Image(String characters,
                                                 Font font,
                                                 Color background,
                                                 Integer width,
                                                 Integer height) throws Exception {
        return characters2Image(characters, font, background, Color.WHITE, width, height);
    }


    /**
     * 文字转图片
     * @param characters
     * @param font
     * @param fontColor
     * @param background
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static BufferedImage characters2Image(String characters,
                                                 Font font,
                                                 Color fontColor,
                                                 Color background,
                                                 Integer width,
                                                 Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.getGraphics();
        graphics.setClip(0, 0, width, height);
        graphics.setColor(background);
        // 先用黑色填充整张图片,也就是背景
        graphics.fillRect(0, 0, width, height);
        // 在换成黑色
        graphics.setColor(fontColor);
        // 设置画笔字体
        graphics.setFont(font);
        /**
         * 用于获得垂直居中y
         * */
        Rectangle clip = graphics.getClipBounds();
        FontMetrics fm = graphics.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        // 水平居中
        int x = clip.width / 2 -fm.stringWidth(characters) / 2;
        // 上下居中
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        graphics.drawString(characters, x, y);
        graphics.dispose();
        return image;
    }

    public static BufferedImage characters2Image(String characters,
                                                 String outline,
                                                 Font f1,
                                                 Font f2,
                                                 Integer width,
                                                 Integer height) throws Exception {
        return characters2Image(characters, outline, f1, f2, Color.BLACK, Color.BLACK, Color.WHITE, width, height);
    }


    public static BufferedImage characters2Image(String characters,
                                                 String outline,
                                                 Font f1,
                                                 Font f2,
                                                 Color c1,
                                                 Color c2,
                                                 Integer width,
                                                 Integer height) throws Exception {
        return characters2Image(characters, outline, f1, f2, c1, c2, Color.WHITE, width, height);
    }


    public static BufferedImage characters2Image(String characters,
                                                 String outline,
                                                 Font f1,
                                                 Font f2,
                                                 Color c1,
                                                 Color c2,
                                                 Color background,
                                                 Integer width,
                                                 Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.getGraphics();
        graphics.setClip(0, 0, width, height);
        graphics.setColor(background);
        // 先用黑色填充整张图片,也就是背景
        graphics.fillRect(0, 0, width, height);
        // 在换成黑色
        graphics.setColor(c1);
        // 设置画笔字体
        graphics.setFont(f1);
        /**
         * 用于获得垂直居中y
         * */
        Rectangle clip = graphics.getClipBounds();
        FontMetrics fm1 = graphics.getFontMetrics(f1);
        FontMetrics fm2 = graphics.getFontMetrics(f2);
        int ascent = fm1.getAscent() ;
        int descent = fm1.getDescent() ;
        int x1 = clip.width / 2 -fm1.stringWidth(characters) / 2;
        int y1 = clip.height/2 - (fm1.getAscent()+fm1.getDescent()+fm2.getAscent()+fm2.getDescent()) / 2 + fm1.getAscent();
        graphics.drawString(characters, x1, y1);

        int x2 = clip.width / 2 - fm2.stringWidth(outline) / 2;
        int y2 = clip.height/2 + ( (fm1.getAscent()+fm1.getDescent()+fm2.getAscent()+fm2.getDescent()) / 2 - (fm2.getAscent()+fm2.getDescent())) + fm2.getAscent();
        graphics.setColor(c2);
        graphics.setFont(f2);
        graphics.drawString(outline, x2, y2);
        graphics.dispose();
        return image;
    }

}