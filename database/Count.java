import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Count {
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驅動程式類別");
			e.printStackTrace();
		}		
		try {
			Connection connection = getConnection(); // 連線資料庫
			Statement statement = connection.createStatement();
			String discover = 
					"SELECT " + 
					"(SELECT count(*) FROM basket) basket, " + 
					"(SELECT count(*) FROM inventory) inventory, " + 
					"(SELECT count(*) FROM member) member, " + 
					"(SELECT count(*) FROM product) product, " + 
					"(SELECT count(*) FROM shopper) shopper, " + 
					"(SELECT count(*) FROM store) store, " + 
					"(SELECT count(*) FROM vendor) vendor";
			System.out.println("Table Name\tCount");
			try (ResultSet resultSet = statement.executeQuery(discover)) {
				while (resultSet.next()) {
					System.out.println(
							"basket\t\t" + resultSet.getString("basket") + "\n" +
							"inventory\t" + resultSet.getString("inventory") + "\n" +
							"member\t\t" + resultSet.getString("member") + "\n" +
							"product\t\t" + resultSet.getString("product") + "\n" +
							"shopper\t\t" + resultSet.getString("shopper") + "\n" +
							"store\t\t" + resultSet.getString("store") + "\n" +
							"vendor\t\t" + resultSet.getString("vendor"));
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