import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;

public class RmiImplement extends UnicastRemoteObject implements RmiInterface{

	RmiImplement() throws RemoteException {
		super();
	}
	
	public String toLower(String str) throws Exception{
		return str.toLowerCase();
	}
	
	public String toUpper(String str) throws Exception{
		return str.toUpperCase();
	}
	
	public String add(String str1, String str2) throws Exception{
		return str1+" and "+str2;
	}
	
	
}
