import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query2 {
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驅動程式類別");
			e.printStackTrace();
		}
		
		System.out.println("The number of stores where Coke outsell Pepsi: ");
		
		try {
			Connection connection = getConnection(); // 連線資料庫
			Statement statement = connection.createStatement();
			String discover = 
					"SELECT COUNT(DISTINCT coke.store_id) result " + 
					"FROM shopper, (" + 
					"SELECT store_id, SUM(amount) cokeSum " + 
					"FROM basket b, shopper s " + 
					"WHERE UPC IN (1000003, 1000004) " + 
					"AND b.invoice = s.invoice " + 
					"GROUP BY s.store_id) coke " + 
					"LEFT JOIN (" + 
					"SELECT store_id, SUM(amount) pepsiSum " + 
					"FROM basket b, shopper s " + 
					"WHERE UPC IN (1000006, 1000007) " + 
					"AND b.invoice = s.invoice " + 
					"GROUP BY s.store_id) pepsi " + 
					"ON coke.store_id = pepsi.store_id " + 
					"WHERE cokeSum > (CASE WHEN pepsiSum IS NULL THEN 0 ELSE pepsiSum END)";
			try (ResultSet resultSet = statement.executeQuery(discover)) {
				while (resultSet.next()) {
					System.out.println(resultSet.getString("result"));
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