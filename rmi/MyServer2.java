package main;

import java.rmi.Naming;

public class MyServer2 {
	public static void main(String[] args) {
		try {
			Transform stub= new ImageProc();
			Naming.rebind("rmi://localhost:5000/server2", stub);
			System.out.println("Server2 is running...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
