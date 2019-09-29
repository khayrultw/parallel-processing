package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {
	public static void main(String[] args) {
		File file = new File("/home/beta-ori/Music/nature.jpeg");
		try {
			BufferedImage image = ImageIO.read(file);
			byte b[] = Convert.imageToByte(image);
			BufferedImage img2 = Convert.byteToImage(b);
			file = new File("/home/beta-ori/Music/nature11.jpeg");
			ImageIO.write(img2, "jpeg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
