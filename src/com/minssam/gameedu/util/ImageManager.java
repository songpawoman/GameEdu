package com.minssam.gameedu.util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageManager {

	public static Image getImage(String path, int width, int height, boolean reverse) {
		Image image=null;
		BufferedImage img=null;
		URL url = ClassLoader.getSystemResource(path);
		
		//System.out.println(url);
		try {
			img=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//이미지 뒤집기라면
		
		if(reverse) {
			AffineTransform tx = AffineTransform.getScaleInstance(1,-1);
			tx.setToScale(-1, 1);
			tx.translate(-img.getWidth(),0);
			
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			img = op.filter(img, null);
		}
		
		image = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return image;
	}
	
}
