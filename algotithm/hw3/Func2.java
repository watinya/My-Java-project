
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Func2 extends JFrame implements ActionListener{
	private JLabel Jlb_ID = new JLabel("學號：");
	private JTextField jstudentID = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("確認");
    private JLabel Jlb_score = new JLabel("");
    
    public Func2()
    {
        super("查詢所有成績");
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定學號label大小位置及顯示字型
        Jlb_ID.setLocation(24,25);
        Jlb_ID.setSize(89,45);
        Jlb_ID.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_ID);
                
        //設定學號輸入框大小位置及顯示字型
        jstudentID.setLocation(99,28);
        jstudentID.setSize(103,40);
        jstudentID.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jstudentID.addActionListener(this);
        c.add(jstudentID);
                
        //設定確認Jbtn_confirm按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(227,29);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_score.setForeground(new Color(65, 105, 225));
        
        //設定成績label大小位置及顯示字型
        Jlb_score.setLocation(24,83);
        Jlb_score.setSize(656,45);
        Jlb_score.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_score);
        
        //設定視窗
        setSize(700, 200);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm || e.getSource() == jstudentID) {
        	//判斷輸入的功能代號
        	String info;
			String studentID = jstudentID.getText();
			String score = GradeSystem.searchAllGrade(studentID);
			
			if(studentID.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
			else if(score == null) {
				info = "無 " + studentID + " 此資料";
				Jlb_score.setForeground(Color.RED);
				Jlb_score.setText(info);
			}
			else if(score.equals("")) {
				info = studentID + " 此學生並沒修課";
				Jlb_score.setForeground(Color.RED);
				Jlb_score.setText(info);
			}
			else {
				info = studentID + " 學生的 分數為 " + score;
				Jlb_score.setForeground(new Color(65, 105, 225));
				Jlb_score.setText(info);
			}
        }
    }

}
