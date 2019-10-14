import java.rmi.*;
import java.rmi.registry.*;

public class Server1{
	
	public static void main(String args[]) throws RemoteException{
		try{
			RmiInterface skel = new RmiImplement();
			LocateRegistry.createRegistry(2003);
			Naming.rebind("rmi://localhost:2003/server1", skel);
			System.out.println("Server1 is running...");
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
