package grade2;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.event.*;

public class Func5 extends JFrame implements ActionListener{
	private JLabel Jlb_subjectName = new JLabel("科目名稱：");
    private JTextField jSubjectName = new JTextField(20);
    private JLabel Jlb_sortFunc = new JLabel("排序方法：");
    private JComboBox<String> jcb_sortMethod;
    private JButton Jbtn_enter = new JButton("確認");
    private DefaultTableModel tableM;
    private JLabel Jlb_result = new JLabel("");
    
    private String subjectName;
    
    public Func5()
    {
        super("排序學生成績");
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定subjectName標籤大小位置及顯示字型
        Jlb_subjectName.setLocation(29,23);
        Jlb_subjectName.setSize(126,45);
        Jlb_subjectName.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_subjectName);
        
        //設定科目名稱輸入框大小位置及顯示字型
        jSubjectName.setLocation(151,26);
        jSubjectName.setSize(130,40);
        jSubjectName.setFont(new Font("微軟正黑體", Font.BOLD,24));
        c.add(jSubjectName);
        
        //設定sortFunc標籤大小位置及顯示字型
        Jlb_sortFunc.setLocation(309,23);
        Jlb_sortFunc.setSize(126,45);
        Jlb_sortFunc.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_sortFunc);
        
        //設定下拉式選單大小位置及顯示字型
    	String[] directories = {"Insertion Sort", "Merge Sort", "Radix Sort"};
    	jcb_sortMethod = new JComboBox<String>();
    	jcb_sortMethod.addItem("請選擇");
		for(int i = 0; i < directories.length; i++) {
			jcb_sortMethod.addItem(directories[i]);
    	}
		jcb_sortMethod.setLocation(432, 28);
		jcb_sortMethod.setSize(213, 36);
		jcb_sortMethod.setFont(new Font("微軟正黑體",Font.BOLD,22));
		jcb_sortMethod.addActionListener(this);
        c.add(jcb_sortMethod);
               
        //設定確認Jbtn_enter按鈕大小位置及顯示字型
        Jbtn_enter.setLocation(659,26);
        Jbtn_enter.setSize(97,40);
        Jbtn_enter.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        Jbtn_enter.addActionListener(this);
        c.add(Jbtn_enter);
        
        //表格內容
    	String[] columns = { "學號", "科目", "分數"};
		tableM = new DefaultTableModel(null, columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		if (column < 3)
					return false;
				else
					return true;
			}// 表格不允許被編輯
	    };
	    JTable courseTable = new JTable(tableM) ;
	    //表格標題大小
	    JTableHeader head = courseTable.getTableHeader();
	    head.setFont(new Font("微軟正黑體", Font.BOLD, 26));
	    head.setPreferredSize(new Dimension(head.getWidth(), 32));
	    //表格大小
	    courseTable.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
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
       
		//設定result標籤大小位置及顯示字型
        Jlb_result.setLocation(29,702);
        Jlb_result.setSize(727,45);
        Jlb_result.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_result);
		
        //設定視窗
        setSize(800, 800);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Jbtn_enter) {
			subjectName = jSubjectName.getText();
			String selectedSort = (String) jcb_sortMethod.getSelectedItem();
			
			if(subjectName.equals("") || selectedSort.equals(null)) {
				JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
			else {
				//根據輸入的科目名稱及排序方法進行成績排序
				int[] sortInfo = {0, 0};//紀錄排序的交換和比較次數
				int[][] data = GradeSystem.getSortData(subjectName);
				
				cleanTable(tableM);
				if(selectedSort.equals("Insertion Sort")) {
					SortFunction.insertionSort(data, sortInfo);
					String[][] strData = toString(data);
					for(int i = data.length-1; i >=0 ;i--){
						tableM.addRow(strData[i]);
					}
					Jlb_result.setText("使用Insertion Sort排序，系統完成排序共比較"+sortInfo[0]+"次，交換元素"+sortInfo[1]+"次");
				}
				else if(selectedSort.equals("Merge Sort")){
					SortFunction.mergeSort(data, 0, data.length-1, sortInfo);
					String[][] strData = toString(data);
					for(int i = data.length-1; i >=0 ;i--){
						tableM.addRow(strData[i]);
					}
					Jlb_result.setText("使用Merge Sort排序，系統完成排序共比較"+sortInfo[0]+"次，交換元素"+sortInfo[1]+"次");
				}
				else if(selectedSort.equals("Radix Sort")){
					SortFunction.radixSort(data, data.length);
					String[][] strData = toString(data);
					for(int i = data.length-1; i >=0 ;i--){
						tableM.addRow(strData[i]);
					}
					Jlb_result.setText("使用Radix Sort排序");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "請選擇排序方式", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	//清空表格method
	static void cleanTable(DefaultTableModel table) {
		while (table.getRowCount() > 0)
			table.removeRow(0);
	}
	
	//將data轉成String
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
