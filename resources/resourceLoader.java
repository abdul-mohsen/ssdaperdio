import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import java.util.Iterator;


public class resourceLoader {
	static resourceLoader RL=new resourceLoader();

	public static BufferedImage ImageLoader(String FileName) {
		//return Toolkit.getDefaultToolkit().getImage(RL.getClass().getResource("/img/"+FileName));
		try {
			return ImageIO.read(RL.getClass().getResource("/img/"+FileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
				
	}

	    public static BufferedImage getScaledInstance(
	        BufferedImage img, int targetWidth,
	        int targetHeight, Object hint, 
	        boolean higherQuality)
	    {
	        int type =
	            (img.getTransparency() == Transparency.OPAQUE)
	            ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage ret = (BufferedImage) img;
	        int w, h;
	        if (higherQuality)
	        {
	            // Use multi-step technique: start with original size, then
	            // scale down in multiple passes with drawImage()
	            // until the target size is reached
	            w = img.getWidth();
	            h = img.getHeight();
	        }
	        else
	        {
	            // Use one-step technique: scale directly from original
	            // size to target size with a single drawImage() call
	            w = targetWidth;
	            h = targetHeight;
	        }

	        do
	        {
	            if (higherQuality && w > targetWidth)
	            {
	                w /= 2;
	                if (w < targetWidth)
	                {
	                    w = targetWidth;
	                }
	            }

	            if (higherQuality && h > targetHeight)
	            {
	                h /= 2;
	                if (h < targetHeight)
	                {
	                    h = targetHeight;
	                }
	            }

	            BufferedImage tmp = new BufferedImage(w, h, type);
	            Graphics2D g2 = tmp.createGraphics();
	            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
	            g2.drawImage(ret, 0, 0, w, h, null);
	            g2.dispose();

	            ret = tmp;
	        } while (w != targetWidth || h != targetHeight);

	        return ret;
	    }
	}

