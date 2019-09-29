package main;

import java.rmi.Naming;

public class MyServer1 {
	public static void main(String[] args) {
		try {
			Transform stub= new ImageProc();
			Naming.rebind("rmi://localhost:5000/server1", stub);
			System.out.println("Server1 is running...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
