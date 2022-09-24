import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class Query1 {
  public static void main(String[] args) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驅動程式類別");
			e.printStackTrace();
		}
		
    System.out.println("The 5 stores with the most sales so far this year:");

    int recentYear = LocalDate.now().getYear();
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String discover = 
					"SELECT store.store_id, store.name, SUM(basket.amount*inventory.price) as sales" +
					" FROM  basket, shopper, inventory,store" +
					" WHERE YEAR(shopper.date)=\'"+recentYear+"\'" +
					" AND shopper.invoice = basket.invoice"  +
					" AND inventory.UPC =  basket.UPC" +
					" AND shopper.store_id = store.store_id" +
					" AND inventory.store_id = store.store_id" +
					" GROUP BY store.name, store.store_id" +
					" ORDER BY sales DESC" +
					" LIMIT 5";
			System.out.println("id\tname\t\tsales");	
			try (ResultSet resultSet = statement.executeQuery(discover)) {
				while (resultSet.next()) {
          	System.out.println(
          					resultSet.getString("store.store_id")+"\t"
                            +resultSet.getString("store.name")+"\t\t"
                            +resultSet.getString("sales")
          					);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() throws SQLException {
		String serverName = "140.127.74.170"; // 伺服器網址
		String database = "410877019";        // 資料庫名稱
		String user = "410877019";            // 使用者名稱
		String password = "410877019";        // 使用者密碼
		String url = "jdbc:mysql://" + serverName + "/" + database; // JDBC線
		return DriverManager.getConnection(url, user, password);    // 連線方法
	}

}