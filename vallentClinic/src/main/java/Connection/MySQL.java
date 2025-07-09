package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private static final String url = "jdbc:mysql://localhost:3306/dbcontrolecontas";
	private static final String usr = "root";
	private static final String pwd = "";

	public static Connection Conectar() {
		try {
			return DriverManager.getConnection(url, usr, pwd);

		} catch (SQLException e) {
			throw new RuntimeException("has ocurred an error. " + e.getMessage());
		}
	}

	public static void Desconectar(Connection conexao) {
		if(conexao !=  null) {
			try {
				conexao.close();
			}catch(SQLException e) {
				throw new RuntimeException("has ocurred an error. " +e.getMessage() );
			}
		}else {
			
		}
	}

}
