package gatocreador887.darkness.util.graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class TransparentGradientInImage {
	public static void updateGradientAt(Image originalImage, BufferedImage image, Point point, int radius, int brightness)
    {
        Graphics2D g = image.createGraphics();
        g.drawImage(originalImage, 0, 0, null);

        float fractions[] = {
        		0.0f,
        		1.0f
        };
        Color colors[] = {
        		new Color(0,0,0,brightness),
        		new Color(0,0,0,0)
        };
        RadialGradientPaint paint = 
            new RadialGradientPaint(point, radius, fractions, colors);
        g.setPaint(paint);

        g.setComposite(AlphaComposite.DstOut);
        g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);
        g.dispose();
        //repaint();
    }

    public static BufferedImage convertToARGB(Image image)
    {
        BufferedImage newImage =
            new BufferedImage(image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}