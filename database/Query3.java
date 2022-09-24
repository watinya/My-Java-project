import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query3 {
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("�䤣���X�ʵ{�����O");
			e.printStackTrace();
		}
		
		System.out.println("Top 3 types of product that customers buy: ");
		
		try {
			Connection connection = getConnection(); // �s�u��Ʈw
			Statement statement = connection.createStatement();
			String discover = 
					"SELECT type, SUM(amount) sales " + 
					"FROM product p, basket b " + 
					"WHERE p.UPC = b.UPC " + 
					"GROUP BY p.type " + 
					"ORDER BY sales DESC " + 
					"LIMIT 3";
			try (ResultSet resultSet = statement.executeQuery(discover)) {
				while (resultSet.next()) {
					System.out.println(
							resultSet.getString("type") + "\t" +
							resultSet.getString("sales"));
				}
			}
			System.out.println("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		String serverName = "140.127.74.170";	// ���A�����}
		String database = "410877019";			// ��Ʈw�W��
		String user = "410877019";				// �ϥΪ̦W��
		String password = "410877019";			// �ϥΪ̱K�X
		String url = "jdbc:mysql://" + serverName + "/" + database;	// JDBC�u
		return DriverManager.getConnection(url, user, password);	// �s�u��k
	}

}