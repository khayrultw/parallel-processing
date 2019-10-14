import java.rmi.*;

public interface RmiInterface extends Remote{
	
	public String toLower(String str) throws Exception;
	
	public String toUpper(String str) throws Exception;
	
	public String add(String str1, String str2) throws Exception;
}
	
