package grade1;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Func3 extends JFrame implements ActionListener{
	private JLabel Jlb_data = new JLabel("學號及科目成績：");
	private JTextField jdata = new JTextField(50);
    private JButton Jbtn_confirm = new JButton("確認");
    private JLabel Jlb_score = new JLabel("");
    
    public Func3()
    {
        super("新增成績");
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定學號及科目成績label大小位置及顯示字型
        Jlb_data.setLocation(14,13);
        Jlb_data.setSize(206,45);
        Jlb_data.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_data);
                      
        //設定輸入框大小位置及顯示字型
        jdata.setLocation(14,62);
        jdata.setSize(557,40);
        jdata.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jdata.addActionListener(this);
        c.add(jdata);
        
        //設定確認Jbtn_confirm按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(585,62);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_score.setForeground(new Color(65, 105, 225));
        
        //設定成績label大小位置及顯示字型
        Jlb_score.setLocation(14,107);
        Jlb_score.setSize(663,45);
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
        if(e.getSource() == Jbtn_confirm || e.getSource() == jdata) {
        	String info;
        	
        	//判斷輸入的功能代號
			String data = jdata.getText();
			
			if(data.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
			else {
				GradeSystem.insertGrade(data);
				info = data + " 新增成功！";
				Jlb_score.setText(info);
			}
        }
    }
}
