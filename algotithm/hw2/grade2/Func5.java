package grade2;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.event.*;

public class Func5 extends JFrame implements ActionListener{
	private JLabel Jlb_subjectName = new JLabel("��ئW�١G");
    private JTextField jSubjectName = new JTextField(20);
    private JLabel Jlb_sortFunc = new JLabel("�ƧǤ�k�G");
    private JComboBox<String> jcb_sortMethod;
    private JButton Jbtn_enter = new JButton("�T�{");
    private DefaultTableModel tableM;
    private JLabel Jlb_result = new JLabel("");
    
    private String subjectName;
    
    public Func5()
    {
        super("�ƧǾǥͦ��Z");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�wsubjectName���Ҥj�p��m����ܦr��
        Jlb_subjectName.setLocation(29,23);
        Jlb_subjectName.setSize(126,45);
        Jlb_subjectName.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_subjectName);
        
        //�]�w��ئW�ٿ�J�ؤj�p��m����ܦr��
        jSubjectName.setLocation(151,26);
        jSubjectName.setSize(130,40);
        jSubjectName.setFont(new Font("�L�n������", Font.BOLD,24));
        c.add(jSubjectName);
        
        //�]�wsortFunc���Ҥj�p��m����ܦr��
        Jlb_sortFunc.setLocation(309,23);
        Jlb_sortFunc.setSize(126,45);
        Jlb_sortFunc.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_sortFunc);
        
        //�]�w�U�Ԧ����j�p��m����ܦr��
    	String[] directories = {"Insertion Sort", "Merge Sort", "Radix Sort"};
    	jcb_sortMethod = new JComboBox<String>();
    	jcb_sortMethod.addItem("�п��");
		for(int i = 0; i < directories.length; i++) {
			jcb_sortMethod.addItem(directories[i]);
    	}
		jcb_sortMethod.setLocation(432, 28);
		jcb_sortMethod.setSize(213, 36);
		jcb_sortMethod.setFont(new Font("�L�n������",Font.BOLD,22));
		jcb_sortMethod.addActionListener(this);
        c.add(jcb_sortMethod);
               
        //�]�w�T�{Jbtn_enter���s�j�p��m����ܦr��
        Jbtn_enter.setLocation(659,26);
        Jbtn_enter.setSize(97,40);
        Jbtn_enter.setFont(new Font("�L�n������", Font.BOLD, 22));
        Jbtn_enter.addActionListener(this);
        c.add(Jbtn_enter);
        
        //��椺�e
    	String[] columns = { "�Ǹ�", "���", "����"};
		tableM = new DefaultTableModel(null, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 3)
					return false;
				else
					return true;
			}// ��椣���\�Q�s��
	    };
	    JTable courseTable = new JTable(tableM) ;
	    //�����D�j�p
	    JTableHeader head = courseTable.getTableHeader();
	    head.setFont(new Font("�L�n������", Font.BOLD, 26));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    //���j�p
	    courseTable.setFont(new Font("�L�n������", Font.PLAIN, 20));
	    courseTable.setRowHeight(28);
	    
	    JScrollPane coursePane = new JScrollPane(courseTable);
		TableColumn column;
		for (int i = 0; i < columns.length; i++) {
			column = courseTable.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		coursePane.setLocation(14, 81);
		coursePane.setSize(753, 608);
		c.add(coursePane);
       
		//�]�wresult���Ҥj�p��m����ܦr��
        Jlb_result.setLocation(29,702);
        Jlb_result.setSize(727,45);
        Jlb_result.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_result);
		
        //�]�w����
        setSize(800, 800);
        setLocationRelativeTo(null);//�����m��
        setResizable(false);//������j���s�L��
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Jbtn_enter) {
			subjectName = jSubjectName.getText();
			String selectedSort = (String) jcb_sortMethod.getSelectedItem();
			
			if(subjectName.equals("") || selectedSort.equals(null)) {
				JOptionPane.showMessageDialog(new JFrame(), "��J���i����", "���~", JOptionPane.ERROR_MESSAGE);
			}
			else {
				//�ھڿ�J����ئW�٤αƧǤ�k�i�榨�Z�Ƨ�
				int[] sortInfo = {0, 0};//�����ƧǪ��洫�M�������
				int[][] data = GradeSystem.getSortData(subjectName);
				
				cleanTable(tableM);
				if(selectedSort.equals("Insertion Sort")) {
					SortFunction.insertionSort(data, sortInfo);
					String[][] strData = toString(data);
					for(int i = data.length-1; i >=0 ;i--){
						tableM.addRow(strData[i]);
					}
					Jlb_result.setText("�ϥ�Insertion Sort�ƧǡA�t�Χ����ƧǦ@���"+sortInfo[0]+"���A�洫����"+sortInfo[1]+"��");
				}
				else if(selectedSort.equals("Merge Sort")){
					SortFunction.mergeSort(data, 0, data.length-1, sortInfo);
					String[][] strData = toString(data);
					for(int i = data.length-1; i >=0 ;i--){
						tableM.addRow(strData[i]);
					}
					Jlb_result.setText("�ϥ�Merge Sort�ƧǡA�t�Χ����ƧǦ@���"+sortInfo[0]+"���A�洫����"+sortInfo[1]+"��");
				}
				else if(selectedSort.equals("Radix Sort")){
					SortFunction.radixSort(data, data.length);
					String[][] strData = toString(data);
					for(int i = data.length-1; i >=0 ;i--){
						tableM.addRow(strData[i]);
					}
					Jlb_result.setText("�ϥ�Radix Sort�Ƨ�");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "�п�ܱƧǤ覡", "���~", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	//�M�Ū��method
	static void cleanTable(DefaultTableModel table) {
		while (table.getRowCount() > 0)
			table.removeRow(0);
	}
	
	//�Ndata�নString
	String[][] toString(int[][] data) {
		String[][] strData = new String[data.length][3];
		
		for(int i = 0; i < data.length; i++) {
			strData[i][0] = Integer.toString(data[i][0]);
			strData[i][1] = subjectName;
			strData[i][2] = Integer.toString(data[i][1]);
		}
		return strData;
	}
}
