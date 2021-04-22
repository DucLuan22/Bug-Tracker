package code;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private String url1;
	private String username1;
	private String password1;
	private Connection con;
	
	public DBConnection (String url,String username, String password)
	{
		this.url1 = url;
		this.username1 = username;
		this.password1 = password;
	}
	
	  public Connection getConnection() throws Exception{
	        try{
	            String driver = "com.mysql.jdbc.Driver";
	            String url = url1;
	            String username = username1;
	            String password = password1;
	            Class.forName(driver);
	            
	            Connection conn = DriverManager.getConnection(url,username,password);
	            System.out.println("Connected");
	            return conn;
	        } catch(Exception e){System.out.println(e);}
	        return null;
	    }
}
