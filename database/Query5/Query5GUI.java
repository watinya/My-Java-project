package Query5;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Query5GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableM;
	private JTextField upcTf;
	private JTextField amountTf;
	Connection connection = getConnection();
	Statement statement = connection.createStatement();
	ArrayList<Basket> basket = new ArrayList<Basket>();
	
	// �����C��
	private int[] storeIdList = { 14342, 16592, 18623, 18659, 19039, 19775, 20374, 20445 };
	private JTextField memberTf;



	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Query5GUI() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
			
		} catch (ClassNotFoundException e) {
			System.out.println("�䤣���X�ʵ{�����O");
			e.printStackTrace();
		}
		Query5 q5 = new Query5(statement);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 446);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel memberLb = new JLabel("�|���s��(�p:1,2,3...,�D�|���h�K��):");
		memberLb.setFont(new Font("�L�n������", Font.PLAIN, 16));
		memberLb.setBounds(53, 4, 277, 45);
		contentPane.add(memberLb);

		memberTf = new JTextField();
		memberTf.setColumns(10);
		memberTf.setBounds(311, 11, 187, 31);
		contentPane.add(memberTf);

		JLabel storeLb = new JLabel("����:");
		storeLb.setFont(new Font("�L�n������", Font.PLAIN, 16));
		storeLb.setBounds(115, 48, 51, 45);
		contentPane.add(storeLb);

		JButton search = new JButton("�d��");
		search.setBounds(413, 59, 85, 23);
		contentPane.add(search);

		JLabel upcLb = new JLabel("���~UPC:");
		upcLb.setFont(new Font("�L�n������", Font.PLAIN, 16));
		upcLb.setBounds(53, 93, 92, 45);
		contentPane.add(upcLb);

		upcTf = new JTextField();
		upcTf.setBounds(140, 100, 132, 31);
		contentPane.add(upcTf);
		upcTf.setColumns(10);

		JLabel amountLb = new JLabel("�ƶq:");
		amountLb.setFont(new Font("�L�n������", Font.PLAIN, 16));
		amountLb.setBounds(294, 92, 75, 45);
		contentPane.add(amountLb);

		amountTf = new JTextField();
		amountTf.setColumns(10);
		amountTf.setBounds(343, 99, 132, 31);
		contentPane.add(amountTf);

		JButton buyBtn = new JButton("�[�J�ʪ���");
		buyBtn.setBounds(488, 108, 122, 23);
		contentPane.add(buyBtn);

		// ���C��
		String[] list = { "14342 �s�Ψ�����", "16592 �̪F���ة�", "18623 ����������", "18659 �P�_���v�j��", "19039 �x�n��w��", 
				"19775 �̪F���Ω�", "20374 �̪F�j�R��", "20445 �x�n���r��", "18281 �x�����\��", "18378 �x�����K��"};
		JComboBox<String> storeBox = new JComboBox<String>(list);
		//JComboBox<String> storeBox = new JComboBox<String>();
		storeBox.setBounds(176, 58, 216, 25);
		contentPane.add(storeBox);

		String[] title = { "UPC", "�~�P", "�ӫ~�W��", "�j�p", "price", "�w�s�ƶq"  }; // ���D
		tableM = new DefaultTableModel(null, title);// table���e
		table = new JTable(tableM) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}// ��椣���\�Q�s��
		};
		JScrollPane tablePane = new JScrollPane(table);// �ưʺu���P��ܼ��D�C
		tablePane.setBounds(22, 169, 599, 217);
		contentPane.add(tablePane);

		JButton checkOut = new JButton("���b");

		checkOut.setBounds(264, 141, 85, 23);
		contentPane.add(checkOut);
		
		JButton checkBasket = new JButton("�d���ʪ���");
		checkBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BasketList(basket);
				new JFrame();
			}
		});
		checkBasket.setBounds(519, 4, 97, 94);
		contentPane.add(checkBasket);
		repaint();

		// ���U�d�߫��s
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanTable(tableM);// �M��menu
				updateMenu(storeBox.getSelectedIndex());// ��smenu
				repaint();
			}
		});
		
		//���U�[�J�ʪ������s
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int storeID = storeIdList[storeBox.getSelectedIndex()];
				int buyAmount= Integer.parseInt(amountTf.getText());
				String upc = upcTf.getText();
				String check =
						"SELECT amount FROM `inventory`"
						+" WHERE UPC = "+upcTf.getText()
						+" AND   store_id ="+storeID;
				try (ResultSet resultSet = statement.executeQuery(check)) {
					while (resultSet.next()) {
						int recentAmount = Integer.parseInt(resultSet.getString("amount"));
						if(recentAmount<buyAmount)
						   JOptionPane.showMessageDialog(new JFrame(),"�[�J���ѡA�ʶR�ƶq�j��w�s�A�д���ʶR�ƶq",
	                                  "�[�J����", JOptionPane.INFORMATION_MESSAGE);
						else {
							basket.add(new Basket(upc,buyAmount));
							upcTf.setText("");
						}
					}
					amountTf.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		//���U���b���s
		checkOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int storeID = storeIdList[storeBox.getSelectedIndex()];
				if("".equals(memberTf.getText())) {
					try {
						q5.checkOut(storeID, basket, null);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					try {
						q5.checkOut(storeID, basket, Integer.parseInt(memberTf.getText()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				memberTf.setText("");
				basket.clear();
			}
		});
	}

	void updateMenu(int index) {
		int storeID = storeIdList[index];
		try {

			String discover = "SELECT p.UPC, brand, name, size, price, amount " 
							+ " FROM inventory i, product p"
							+ "	WHERE store_id = "+storeID
							+ "	AND i.UPC = p.UPC";
			try (ResultSet resultSet = statement.executeQuery(discover)) {
				while (resultSet.next()) {
					Object[]temp = {resultSet.getString("p.UPC"),
									resultSet.getString("brand"),
									resultSet.getString("name"),
									resultSet.getString("size"),
									resultSet.getString("price"),
									resultSet.getString("amount")};
					tableM.addRow(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	void cleanTable(DefaultTableModel tableM) {
		while (tableM.getRowCount() > 0)
			tableM.removeRow(0);
	}

	private Connection getConnection() throws SQLException {
		String serverName = "140.127.74.170"; // ���A�����}
		String database = "410877019"; // ��Ʈw�W��
		String user = "410877019"; // �ϥΪ̦W��
		String password = "410877019"; // �ϥΪ̱K�X
		String url = "jdbc:mysql://" + serverName + "/" + database; // JDBC�u
		return DriverManager.getConnection(url, user, password); // �s�u��k
	}
}
