package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Transform extends Remote{
	
	public byte[] transform(byte[] img) throws RemoteException;
	
}
