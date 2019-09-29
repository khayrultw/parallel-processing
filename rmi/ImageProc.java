package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;

public class ImageProc extends UnicastRemoteObject implements Transform{

	public ImageProc() throws RemoteException{
		super();
	}
	public byte[] transform(byte[] imgByte) throws RemoteException{
		
		BufferedImage image = Convert.byteToImage(imgByte);
		System.out.println("Processing...");
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y < image.getHeight(); y++) {
				Color color = new Color(image.getRGB(x, y));
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				int gr = (r + g + b)/3;
				color = new Color(gr,gr,gr);
				image.setRGB(x, y, color.getRGB());
			}
		}
		try {
			ImageIO.write(image, "jpg", new File("/home/beta-ori/Music/nature10.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgByte = Convert.imageToByte(image);
		return imgByte;
		
	}

}
