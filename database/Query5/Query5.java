package Query5;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Query5 {
	private Statement statement;

	Query5(Statement statement) {
		this.statement = statement;
	}

	public void checkOut(int storeID, ArrayList<Basket> basket, Integer memberID) throws SQLException {

		String invoice = produceInvoice();
		String time = getDate();
		System.out.println(invoice+" "+time);



		boolean emptyBasket = true;

		// �[���O�̬���
		String addShopper;
		if (memberID == null) {
			addShopper = "INSERT INTO `shopper` (`invoice`,`store_id`,`date`, `member_id`)" + " VALUES (\'" + invoice
					+ "\',\'" + storeID + "\',\'" + time + "\',NULL);";
		} else {
			addShopper = "INSERT INTO `shopper` (`invoice`,`store_id`,`date`, `member_id`)" + " VALUES (\'" + invoice
					+ "\',\'" + storeID + "\',\'" + time + "\',\'" + memberID + "\');";
		}

		statement.executeUpdate(addShopper);

		// ��w�s�B�s�W���O���ʪ������e
		for (int i = 0; i < basket.size(); i++) {
			
			statement.executeUpdate("BEGIN; ");// �}�l
			statement.executeUpdate("SAVEPOINT start");//�إ��x�s�I

			String updateInventory = "UPDATE `inventory`" + " SET amount=amount-" + basket.get(i).amount
					+ " WHERE UPC = " + basket.get(i).UPC + " AND   store_id =" + storeID + ";";
			
			try {
				statement.executeUpdate(updateInventory);
			} catch (SQLException e) {
				// �]���w�s���A��unsigned�A�u�n�p��0�N�|�i��catch SQLException
				statement.executeUpdate("ROLLBACK TO start");//�^���x�s�I(�٥��[�J�o����ƳB)
				JOptionPane.showMessageDialog(new JFrame(),
						"UPC:" + basket.get(i).UPC.toString() + "�ʶR���ѡA�ʶR�ƶq�j��ثe�w�s�A�еy�᭫�s�ʶR", "�ʶR����",
						JOptionPane.INFORMATION_MESSAGE);
				continue;
			}
			

			String updateBasket = "INSERT INTO `basket` (`invoice`,`UPC`,`amount`)" 
						+ " VALUES (\'" + invoice + "\',\'"+basket.get(i).UPC+"\',\'"+ basket.get(i).amount + "\');";
			try {
				statement.executeUpdate(updateBasket);
				emptyBasket = false;
			} catch (SQLException e) {
				e.printStackTrace();
				statement.executeUpdate("ROLLBACK TO start");
			}
			
			statement.executeUpdate("COMMIT;");

		}

		if (emptyBasket == true) {
			String delShopper = "DELETE FROM `shopper` WHERE `shopper`.`invoice` = \'"+invoice+"\';";
			statement.executeUpdate(delShopper);
			JOptionPane.showMessageDialog(new JFrame(), "�z�����\�ʶR����ӫ~�A�ҥH���|���͵o����", "����", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "<html><body>�z���o��:" + invoice + "<br>���¥��{</body></html>", "�o������",JOptionPane.INFORMATION_MESSAGE);
		}


	}

	private String produceInvoice() {
		Random r = new Random();
		int upCase1 = r.nextInt(26) + 65;
		int upCase2 = r.nextInt(26) + 65;
		String invoice = String.valueOf((char) upCase1) + String.valueOf((char) upCase2);
		for (int i = 0; i < 8; i++) {
			Integer num = r.nextInt(9);
			invoice += num.toString();
		}
		return invoice;
	}

	private String getDate() {
		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdFormat.format(date).toString();
		return time;
	}

}