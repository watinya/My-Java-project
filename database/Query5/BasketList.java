package Query5;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class BasketList extends JFrame {

	private JPanel contentPane;

	public BasketList(ArrayList<Basket>basket) {
		super();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 463);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		StringBuilder str = new StringBuilder();
		String[] title = { "UPC", "�ƶq"}; // ���D
		DefaultTableModel tableM = new DefaultTableModel(null, title);// table���e
		JTable table = new JTable(tableM) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}// ��椣���\�Q�s��
		};
		JScrollPane tablePane = new JScrollPane(table);// �ưʺu���P��ܼ��D�C
		tablePane.setBounds(22, 37, 344, 349);
		for(int i=0;i<basket.size();i++) {
			tableM.addRow(new Object[] {basket.get(i).UPC,basket.get(i).amount});
		}
		contentPane.add(tablePane);
	}

}
