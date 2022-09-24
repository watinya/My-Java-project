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
			System.out.println("找不到驅動程式類別");
			e.printStackTrace();
		}
		
		System.out.println("Top 3 types of product that customers buy: ");
		
		try {
			Connection connection = getConnection(); // 連線資料庫
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
		String serverName = "140.127.74.170";	// 伺服器網址
		String database = "410877019";			// 資料庫名稱
		String user = "410877019";				// 使用者名稱
		String password = "410877019";			// 使用者密碼
		String url = "jdbc:mysql://" + serverName + "/" + database;	// JDBC線
		return DriverManager.getConnection(url, user, password);	// 連線方法
	}

}