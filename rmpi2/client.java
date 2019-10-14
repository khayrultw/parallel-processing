import java.rmi.*;
import java.util.*;


public class client{
	public static void main(String args[]) throws Exception{
		RmiInterface st = (RmiInterface)Naming.lookup("rmi://localhost:2003/server1");
		System.out.println(st.toUpper("anskfkdhg"));
	}
}
