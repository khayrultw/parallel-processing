package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import javax.imageio.ImageIO;

public class MyClient {
	
	public BufferedImage image, img1, img2, img3;
	
	public void go() {
		
		image = img1 = img2 = img3 = null;
		try {
			File file = new File("/home/beta-ori/Music/nature.jpeg");
			image = ImageIO.read(file);
			
			img1 = image.getSubimage(0, 0, image.getWidth()/3, image.getHeight());
			img2 = image.getSubimage(image.getWidth()/3, 0, image.getWidth()/3, image.getHeight());
			img3 = image.getSubimage(2*image.getWidth()/3, 0, image.getWidth()/3, image.getHeight());

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					byte[] x1 = Convert.imageToByte(img1);
					Transform trans = (Transform) Naming.lookup("rmi://localhost:5000/server0");
					x1 = trans.transform(x1);
					img1 = Convert.byteToImage(x1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});

		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					byte[] x1 = Convert.imageToByte(img2);
					Transform trans = (Transform) Naming.lookup("rmi://localhost:5000/server1");
					x1 = trans.transform(x1);
					img2 = Convert.byteToImage(x1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});

		
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					byte[] x1 = Convert.imageToByte(img3);
					Transform trans = (Transform) Naming.lookup("rmi://localhost:5000/server2");
					x1 = trans.transform(x1);
					img3 = Convert.byteToImage(x1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		try {
			t1.start();
			t1.join();
			
			t2.start();
			t2.join();
			
			t3.start();
			t3.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!t1.isAlive() && !t2.isAlive() && !t3.isAlive())
		{
			try {
				File out = new File("/home/beta-ori/Music/nature1.jpeg");
				ImageIO.write(img1, "jpg", out);

				out = new File("/home/beta-ori/Music/nature2.jpeg");
				ImageIO.write(img2, "jpg", out);
				
				out = new File("/home/beta-ori/Music/nature3.jpeg");
				ImageIO.write(img2, "jpg", out);
				
				image = joinImage(img1, img2);
				image = joinImage(image, img3);
				File out1 = new File("/home/beta-ori/Music/nature4.jpeg");
				ImageIO.write(image, "jpeg", out1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static BufferedImage joinImage(BufferedImage img1, BufferedImage img2) {
		
		int w = img1.getWidth()+img2.getWidth();
		int h = Math.max(img1.getHeight(), img2.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, img1.getWidth(), 0, null);
		return combined;
	}
	
	public static void main(String[] args) {
		MyClient client = new MyClient();
		client.go();
	}
}
