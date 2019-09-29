package main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Convert {
	
	public static BufferedImage byteToImage(byte [] imgByte){
		BufferedImage img = null;
		try {
			InputStream in = new ByteArrayInputStream(imgByte);
			in.close();
			 img = ImageIO.read(in);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return img;
		
	}
	public static byte[] imageToByte(BufferedImage img){
		byte[] imgByte = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( img, "jpeg", baos );
			baos.flush();
			imgByte= baos.toByteArray();
			baos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return imgByte;
	}
}
