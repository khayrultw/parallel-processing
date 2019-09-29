package main;

import java.rmi.Naming;

public class MyServer {
	public static void main(String[] args) {
		try {
			Transform stub= new ImageProc();
			Naming.rebind("rmi://localhost:5000/server0", stub);
			System.out.println("Server0 is running...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
