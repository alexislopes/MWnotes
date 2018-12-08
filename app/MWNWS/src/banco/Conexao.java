package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String BD_URL = "jdbc:mysql://root:1234@localhost:3306/mvnotes?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
	
	public Connection getConexao() throws ClassNotFoundException, SQLException {
		Connection c = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		c = DriverManager.getConnection(BD_URL); 
		
		return c;
	}
}
