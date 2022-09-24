
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Func6 extends JFrame implements ActionListener{
	private JLabel Jlb_subjectName = new JLabel("科目名稱：");
	private JTextField jsubjectName = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("確認");
    private JLabel Jlb_subArray = new JLabel("");
    
    public Func6()
    {
        super("最長遞增成績子序列");
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定科目名稱label大小位置及顯示字型
        Jlb_subjectName.setLocation(14,25);
        Jlb_subjectName.setSize(142,45);
        Jlb_subjectName.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_subjectName);
                
        //設定科目輸入框大小位置及顯示字型
        jsubjectName.setLocation(142,28);
        jsubjectName.setSize(103,40);
        jsubjectName.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jsubjectName.addActionListener(this);
        c.add(jsubjectName);
                
        //設定確認Jbtn_confirm按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(260,29);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_subArray.setForeground(new Color(65, 105, 225));
        
        //設定最長子序列label大小位置及顯示字型
        Jlb_subArray.setLocation(14,83);
        Jlb_subArray.setSize(1198,116);
        Jlb_subArray.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_subArray);
        
        //設定視窗
        setSize(700, 270);
        setLocationRelativeTo(null);//視窗置中
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm || e.getSource() == jsubjectName) {
        	//判斷輸入的功能代號
        	String info;
			String subjectName = jsubjectName.getText();
			int[] score = GradeSystem.getGrade(subjectName);
			
			//輸入為空
			if(subjectName.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
			//沒有輸入的科目成績
			else if(!GradeSystem.searchSubject(subjectName)) {
				info = "沒有 " + subjectName + " 科目之成績";
				Jlb_subArray.setForeground(Color.RED);
				Jlb_subArray.setText(info);
				jsubjectName.setText("");
			}
			else {
				int longestLen = GradeSystem.longestIncreasingSubsequence(score);
				String scoreData = Arrays.toString(score);
				scoreData = scoreData.substring(1,scoreData.length()-1);
				info = "<html><body>" + subjectName + " 成績序列為 " + scoreData + "<br>最長遞增子序列長度為 " + longestLen + "</html></body>";
				Jlb_subArray.setForeground(new Color(65, 105, 225));
				Jlb_subArray.setText(info);
				jsubjectName.setText("");
			}
        }
    }
}
