package com.ipukr.elephant.qr;


import org.apache.commons.imaging.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    /**
     * @param ous
     * @param format
     * @param images
     * @throws IOException
     */
    public static void zip(ZipOutputStream ous, ImageFormat format, Map<String, BufferedImage> images) throws IOException {
        if (images.size() > 0) {
            for (Map.Entry<String, BufferedImage> entry : images.entrySet()) {
                ZipEntry ize = new ZipEntry(entry.getKey().concat(".").concat(format.getName()));
                ous.putNextEntry(ize);
                ImageIO.write(entry.getValue(), format.getName(), ous);
                ous.closeEntry();
            }
            ous.close();
        }
    }
}
