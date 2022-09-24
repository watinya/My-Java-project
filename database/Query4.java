import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Query4 extends TimerTask{
	
	public static void main(String[] args) {
		Query4 checkInventory = new Query4();
		checkInventory.testScheduleDateAndPeriod();
	}
	
	void testScheduleDateAndPeriod() {
		Timer timer = new Timer();

		//設定填入schedule中的 Date firstTime為現在時間
		Calendar calendar = Calendar.getInstance();
		//calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 5);
		Date firstTime = calendar.getTime();

		//System.out.println("In testScheduleDateAndPeriod：" + new Date());
		//System.out.println("設定執行 Date 為0秒後：" + firstTime + ", Period：5秒");

		//兩分鐘檢查一次
		timer.schedule(new Query4(), firstTime, 120000);
	}

	@Override
	public void run() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驅動程式類別");
			e.printStackTrace();
		}

		System.out.println("Check invnetory at " + new Date() + " :");	

		try {
			Connection connection = getConnection(); // 連線資料庫
			Statement statement = connection.createStatement();
			String discover = 
					"SELECT i.store_id, p.UPC, i.amount, v.name " + 
					"FROM inventory i, product p, vendor v " + 
					"WHERE i.amount < 5 " + 
					"AND i.UPC = p.UPC " + 
					"AND p.vendor_id = v.vendor_id " + 
					"ORDER BY i.store_id ASC";
			try (ResultSet resultSet = statement.executeQuery(discover)) {
				while (resultSet.next()) {
					System.out.println(
							"店號：" + resultSet.getString("store_id") +
							" 的 UPC：" + resultSet.getString("UPC") +
							" 剩餘：" + resultSet.getString("amount") + 
							"\t已向製造商；" + resultSet.getString("name") + 
							"\t進行訂購");
				}
			}
			
			String update = 
					"UPDATE inventory " + 
					"SET amount = amount + 30 " + 
					"WHERE amount < 5;";
			statement.executeUpdate(update);
			
			System.out.println("-------Check Finish-------\n");
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