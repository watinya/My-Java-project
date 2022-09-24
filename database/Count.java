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
			System.out.println("�䤣���X�ʵ{�����O");
			e.printStackTrace();
		}		
		try {
			Connection connection = getConnection(); // �s�u��Ʈw
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
		String serverName = "140.127.74.170";	// ���A�����}
		String database = "410877019";			// ��Ʈw�W��
		String user = "410877019";				// �ϥΪ̦W��
		String password = "410877019";			// �ϥΪ̱K�X
		String url = "jdbc:mysql://" + serverName + "/" + database;	// JDBC�u
		return DriverManager.getConnection(url, user, password);	// �s�u��k
	}

}