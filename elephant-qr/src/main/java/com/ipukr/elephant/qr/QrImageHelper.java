package com.ipukr.elephant.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/13.
 */
public class QrImageHelper {



    /**
     * @param text
     * @param width
     * @param height
     * @throws WriterException
     * @throws IOException
     * @return
     */
    public static BufferedImage generate(String text, int width, int height) throws WriterException, IOException {
        return generate(text, width, height, 0);
    }

    /**
     * @param text
     * @param width
     * @param height
     * @throws WriterException
     * @throws IOException
     * @return
     */
    public static BufferedImage generate(String text, int width, int height, int margin) throws WriterException, IOException {
        Hashtable hints= new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, margin);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        return image;
    }
}
