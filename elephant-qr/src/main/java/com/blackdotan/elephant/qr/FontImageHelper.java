package com.blackdotan.elephant.qr;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/13.
 */
public class FontImageHelper {

    @Deprecated
    public static BufferedImage characters2Image(String characters,
                                                 Integer width,
                                                 Integer height) throws Exception {
        return characters2Image(characters, new Font("宋体", Font.BOLD, Math.min(width, height)/3 ), width, height);
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
    public static BufferedImage characters2Image(String characters,
                                                 Font font,
                                                 Color fontColor,
                                                 Color background,
                                                 Integer width,
                                                 Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = image.createGraphics();
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


    @Deprecated
    public static BufferedImage characters2Image(String characters,
                                                 String outline,
                                                 Font f1,
                                                 Font f2,
                                                 Integer width,
                                                 Integer height) throws Exception {
        return characters2Image(characters, outline, f1, f2, Color.BLACK, Color.BLACK, Color.WHITE, width, height);
    }

    @Deprecated
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

    @Deprecated
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


    /**
     * @param text
     * @param width
     * @param height
     * @param font
     * @return
     */
    @Deprecated
    public static BufferedImage draw(String text, int width, int height, Font font) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setFont(font);
        drawParagraph(graphics2D, text, width , 0, 0);
        return image;
    }

    /**
     * @param graphics
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param text
     */
    @Deprecated
    private static void drawTextInBoundedArea(Graphics2D graphics,
                                              int x1,
                                              int y1,
                                              int x2,
                                              int y2,
                                              String text) {
        float interline = 1;
        float width = x2 - x1;
        AttributedString as = new AttributedString(text);
        as.addAttribute(TextAttribute.FOREGROUND, graphics.getPaint());
        as.addAttribute(TextAttribute.FONT, graphics.getFont());
        as.addAttribute(TextAttribute.WIDTH, width);
        AttributedCharacterIterator aci = as.getIterator();
        FontRenderContext frc = new FontRenderContext(null, true, false);
        LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
        while (lbm.getPosition() < text.length()) {
            TextLayout tl = lbm.nextLayout(width);
            y1 += tl.getAscent();
            tl.draw(graphics, x1, y1);
            y1 += tl.getDescent() + tl.getLeading() + (interline - 1.0f) * tl.
                    getAscent();
            if (y1 > y2) {
                break;
            }
        }
    }

    @Deprecated
    protected static float drawParagraph (Graphics2D g2, String text, float width, float x, float y){
        AttributedString attstring = new AttributedString(text);
        attstring.addAttribute(TextAttribute.FONT, g2.getFont());
        attstring.addAttribute(TextAttribute.WIDTH, width);

        AttributedCharacterIterator paragraph = attstring.getIterator();
        int paragraphStart = paragraph.getBeginIndex();
        int paragraphEnd = paragraph.getEndIndex();
        FontRenderContext frc = g2.getFontRenderContext();
        LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, frc);

        float breakWidth = width;
        float drawPosY = y;

        // Set position to the index of the first character in the paragraph.
        lineMeasurer.setPosition(paragraphStart);


        // Get lines until the entire paragraph has been displayed.
        while (lineMeasurer.getPosition() < paragraphEnd) {
            // Retrieve next layout. A cleverer program would also cache
            // these layouts until the component is re-sized.
            TextLayout layout = lineMeasurer.nextLayout(breakWidth);
            // Compute pen x position.
            float drawPosX;
//            switch (alignment){
//                case RIGHT:
//                    drawPosX = (float) x + breakWidth - layout.getAdvance();
//                    break;
//                case CENTER:
                    drawPosX = (float) x + (breakWidth - layout.getAdvance())/2;
//                    break;
//                default:
//                    drawPosX = (float) x;
//            }
            // Move y-coordinate by the ascent of the layout.
            drawPosY += layout.getAscent();

            // Draw the TextLayout at (drawPosX, drawPosY).
            layout.draw(g2, drawPosX, drawPosY);

            // Move y-coordinate in preparation for next layout.
            drawPosY += layout.getDescent() + layout.getLeading();
        }
        return drawPosY;
    }



    /**
     * @param graphics
     * @param s
     * @param x
     * @param y
     * @param width
     * @param alignment 对齐规则
     */
    public static void drawAlignmentString(Graphics graphics, String s, int x, int y, int width, CharactersAlignmentFormat alignment) {
        // Find the size of string s in the font of the Graphics context "page"
        FontMetrics fm   = graphics.getFontMetrics(graphics.getFont());
        int height = fm.getHeight();
        switch (alignment) {
            case 右对齐: {
                Rectangle2D rect = fm.getStringBounds(s, graphics);
                int textHeight = (int) (rect.getHeight());
                int textWidth = (int) (rect.getWidth());

                // Center text horizontally and vertically within provided rectangular bounds
                int textX = x;
                int textY = y + (height - textHeight) / 2 + fm.getAscent();
                graphics.drawString(s, textX, textY);
                break;
            }
            case 左对齐: {
                Rectangle2D rect = fm.getStringBounds(s, graphics);
                int textHeight = (int) (rect.getHeight());
                int textWidth = (int) (rect.getWidth());

                // Center text horizontally and vertically within provided rectangular bounds
                int textX = x + (width - textWidth);
                int textY = y + (height - textHeight) / 2 + fm.getAscent();

                graphics.drawString(s, textX, textY);
                break;
            }
            case 左右对齐: {
                if (s.length() > 1) {
                    int textWidth = 0;
                    int textHeight = 0;
                    int length = s.length();
                    {
                        Rectangle2D rect = fm.getStringBounds(s, graphics);
                        textHeight = (int) (rect.getHeight());
                        textWidth = (int) (rect.getWidth());
                    }
                    // 计算每个文字长度
                    double[] widths = new double[length];
                    // 计算文字总长度
                    double sum = 0;
                    {
                        int index = 0;
                        for (String item : s.split("")) {
                            Rectangle2D rect = fm.getStringBounds(item, graphics);
                            widths[index++] = rect.getWidth();
                            sum = sum + rect.getWidth();
                        }
                    }
                    // 计算文字间隔步长
                    double step = (width - sum) / (length - 1);

                    // 文字绘图，Y值固定不变
                    int textY = y + (height - textHeight) / 2 + fm.getAscent();
                    {
                        int index = 0;
                        for (String item : s.split("")) {
                            // x = x + 前文字消耗长度 + 步长 * 前文字个数
                            double cap = 0;
                            for (int j = 0; j < index; j++) {
                                cap = cap + widths[j];
                            }
                            int textX = (int) (x + cap + step * index);
                            graphics.drawString(item, textX, textY);
                            index ++;
                        }
                    }
                    break;
                }
            }
            default: {
                Rectangle2D rect = fm.getStringBounds(s, graphics);
                int textHeight = (int) (rect.getHeight());
                int textWidth = (int) (rect.getWidth());

                // Center text horizontally and vertically within provided rectangular bounds
                int textX = x + (width - textWidth) / 2;
                int textY = y + (height - textHeight) / 2 + fm.getAscent();
                graphics.drawString(s, textX, textY);
            }

        }
    }

}
