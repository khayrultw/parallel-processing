package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Naming;
import javax.imageio.ImageIO;

public class MyClient1 {
	
	public static BufferedImage joinImage(BufferedImage img1, BufferedImage img2) {
		
		int w = img1.getWidth()+img2.getWidth();
		int h = Math.max(img1.getHeight(), img2.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

		Graphics g = combined.getGraphics();
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, img1.getWidth(), 0, null);
		return combined;
	}
	
	public static void main(String[] args) {
		
		BufferedImage image, img1, img2, img3;
		
		image = img1 = img2 = img3 = null;
		try {
			File file = new File("/home/beta-ori/Music/nature.jpeg");
			image = ImageIO.read(file);
			
			img1 = image.getSubimage(0, 0, image.getWidth()/3, image.getHeight());
			img2 = image.getSubimage(image.getWidth()/3, 0, image.getWidth()/3, image.getHeight());
			img3 = image.getSubimage(2*image.getWidth()/3, 0, image.getWidth()/3, image.getHeight());
			
			Transform trans1 = (Transform) Naming.lookup("rmi://localhost:5000/server0");
			byte[]  x1 = trans1.transform(Convert.imageToByte(img1));
			img1 = Convert.byteToImage(x1);
			
			Transform trans2 = (Transform) Naming.lookup("rmi://localhost:5000/server1");
			byte[]  x2 = trans2.transform(Convert.imageToByte(img2));
			img2 = Convert.byteToImage(x2);
			
			Transform trans3 = (Transform) Naming.lookup("rmi://localhost:5000/server2");
			byte [] x3 = trans3.transform(Convert.imageToByte(img3));
			img3 = Convert.byteToImage(x3);
		
			ImageIO.write(img1, "jpeg", new File("/home/beta-ori/Music/nature1.jpeg"));

			ImageIO.write(img2, "jpeg", new File("/home/beta-ori/Music/nature2.jpeg"));
			
			ImageIO.write(img3, "jpeg", new File("/home/beta-ori/Music/nature3.jpeg"));
			
			image = joinImage(img1, img2);
			image = joinImage(image, img3);
	
			ImageIO.write(image, "jpeg", new File("/home/beta-ori/Music/nature4.jpeg"));

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Complete...");
	}
}
