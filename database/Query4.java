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

		//�]�w��Jschedule���� Date firstTime���{�b�ɶ�
		Calendar calendar = Calendar.getInstance();
		//calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 5);
		Date firstTime = calendar.getTime();

		//System.out.println("In testScheduleDateAndPeriod�G" + new Date());
		//System.out.println("�]�w���� Date ��0���G" + firstTime + ", Period�G5��");

		//������ˬd�@��
		timer.schedule(new Query4(), firstTime, 120000);
	}

	@Override
	public void run() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("�䤣���X�ʵ{�����O");
			e.printStackTrace();
		}

		System.out.println("Check invnetory at " + new Date() + " :");	

		try {
			Connection connection = getConnection(); // �s�u��Ʈw
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
							"�����G" + resultSet.getString("store_id") +
							" �� UPC�G" + resultSet.getString("UPC") +
							" �Ѿl�G" + resultSet.getString("amount") + 
							"\t�w�V�s�y�ӡF" + resultSet.getString("name") + 
							"\t�i��q��");
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
		String serverName = "140.127.74.170";	// ���A�����}
		String database = "410877019";			// ��Ʈw�W��
		String user = "410877019";				// �ϥΪ̦W��
		String password = "410877019";			// �ϥΪ̱K�X
		String url = "jdbc:mysql://" + serverName + "/" + database;	// JDBC�u
		return DriverManager.getConnection(url, user, password);	// �s�u��k
	}
}