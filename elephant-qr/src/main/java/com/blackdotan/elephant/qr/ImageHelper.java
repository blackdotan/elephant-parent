package com.blackdotan.elephant.qr;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    @Deprecated
    public static BufferedImage concat(BufferedImage image1, BufferedImage image2) {
        return concat(image1, image2, Color.WHITE);
    }

    /**
     * @param image1
     * @param image2
     * @param background
     * @return
     */
    @Deprecated
    public static BufferedImage concat(BufferedImage image1, BufferedImage image2, Color background) {
        int width = image1.getWidth() + image2.getWidth();
        int height = Math.max(image1.getHeight(), image2.getHeight());
        int meanHeight = height/2;
        // 创建图片
        BufferedImage image = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_BGR);

        Graphics graphics = image.createGraphics();
        graphics.setColor(background);
        // 先用黑色填充整张图片,也就是背景
        graphics.fillRect(0, 0, width, height);

        graphics.drawImage(image1,
                30,
                image1.getHeight() > image2.getHeight() ? 0  : Math.max((image1.getHeight() + image2.getHeight())/2 - image1.getHeight(), 0),
                image1.getWidth() + 30,
                image1.getHeight(),
                null);
        graphics.drawImage(image2,
                image1.getWidth(),
                image2.getHeight() > image1.getHeight() ? 0  : Math.max( (image1.getHeight() + image2.getHeight())/2 - image1.getHeight(), 0),
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
    public static void zip(ZipOutputStream ous, QrFormat format, Map<String, BufferedImage> images) throws IOException {
        if (images.size() > 0) {
            for (Map.Entry<String, BufferedImage> entry : images.entrySet()) {
                ZipEntry ize = new ZipEntry(entry.getKey().concat(".").concat(format.value()));
                ous.putNextEntry(ize);
                ImageIO.write(entry.getValue(), format.value(), ous);
                ous.closeEntry();
            }
            ous.finish();
        }
    }
}
