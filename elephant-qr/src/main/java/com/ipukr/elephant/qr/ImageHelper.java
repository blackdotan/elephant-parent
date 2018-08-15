package com.ipukr.elephant.qr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/13.
 */
public class ImageHelper {
    /**
     * 左边连接
     * @param image1
     * @param image2
     * @return
     */
    public static BufferedImage concat(BufferedImage image1, BufferedImage image2) {
        // 创建图片
        BufferedImage image = new BufferedImage(
                image1.getWidth() + image2.getWidth(),
                Math.max(image1.getHeight(), image2.getHeight()),
                BufferedImage.TYPE_INT_BGR);

        Graphics graphics = image.createGraphics();
        graphics.drawImage(image1,
                0,
                Math.max( (image1.getHeight() + image2.getHeight())/2 - image1.getHeight(), 0),
                image1.getWidth(),
                image1.getHeight(),
                null);
        graphics.drawImage(image2,
                image1.getWidth(),
                Math.max( (image1.getHeight() + image2.getHeight())/2 - image1.getHeight(), 0),
                image2.getWidth(),
                image2.getHeight(),
                null);
        graphics.dispose();
        return image;
    }
}
