import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Func3 extends JFrame implements ActionListener{
	private JLabel Jlb_ID = new JLabel("學號：");
	private JLabel Jlb_subject = new JLabel("科目及成績：");
	private JTextField jstudentID = new JTextField(20);
	private JTextField jsubjectName = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("確認");
    private JLabel Jlb_score = new JLabel("");
    
    public Func3()
    {
        super("新增成績");
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定學號label大小位置及顯示字型
        Jlb_ID.setLocation(96,25);
        Jlb_ID.setSize(72,45);
        Jlb_ID.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_ID);
        
        //設定科目label大小位置及顯示字型
        Jlb_subject.setLocation(24,74);
        Jlb_subject.setSize(144,45);
        Jlb_subject.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_subject);
        
        //設定學號輸入框大小位置及顯示字型
        jstudentID.setLocation(174,28);
        jstudentID.setSize(103,40);
        jstudentID.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jstudentID.addActionListener(this);
        c.add(jstudentID);
        
        //設定科目輸入框大小位置及顯示字型
        jsubjectName.setLocation(174,77);
        jsubjectName.setSize(206,40);
        jsubjectName.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jsubjectName.addActionListener(this);
        c.add(jsubjectName);
        
        //設定確認Jbtn_confirm按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(150,127);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_score.setForeground(new Color(65, 105, 225));
        
        //設定成績label大小位置及顯示字型
        Jlb_score.setLocation(24,180);
        Jlb_score.setSize(356,45);
        Jlb_score.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_score);
        
        //設定視窗
        setSize(400, 300);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm) {
        	String info;
        	
        	//判斷輸入的功能代號
			String studentID = jstudentID.getText();
			String subject = jsubjectName.getText();
			int studentIDLocate = GetData.getStudent(studentID);
			int subjectLocate = GetData.getSubject(subject);
			
			if(studentID.equals("") || subject.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
			else if(subjectLocate == -1) {
				JOptionPane.showMessageDialog(new JFrame(), "科目不存在", "錯誤", JOptionPane.ERROR_MESSAGE);
				jsubjectName.setText("");
			}
			else if(studentIDLocate == -1) {
				JOptionPane.showMessageDialog(new JFrame(), "學生不存在", "錯誤", JOptionPane.ERROR_MESSAGE);
				jstudentID.setText("");
			}
			else {
				try {
					String[][] data = GetData.readFile();
					info = data[studentIDLocate][subjectLocate+1] + " 新增成功！";
					Jlb_score.setText(info);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        }
    }

}
