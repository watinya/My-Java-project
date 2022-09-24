import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Func7 extends JFrame implements ActionListener{
	private JLabel Jlb_courseNum = new JLabel("課程總數：");
	private JTextField jcourseNum = new JTextField(50);
	private JLabel Jlb_condition = new JLabel("先決條件：");
	private JTextField jcondition = new JTextField(50);
    private JButton Jbtn_confirm = new JButton("確認");
    private JLabel Jlb_result = new JLabel("");
    
    public Func7()
    {
        super("排課");
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定欲排課程數label大小位置及顯示字型
        Jlb_courseNum.setLocation(14,13);
        Jlb_courseNum.setSize(128,45);
        Jlb_courseNum.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_courseNum);
                      
        //設定課程輸入框大小位置及顯示字型
        jcourseNum.setLocation(137,16);
        jcourseNum.setSize(82,40);
        jcourseNum.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jcourseNum.addActionListener(this);
        c.add(jcourseNum);
        
        //設定條件label大小位置及顯示字型
        Jlb_condition.setLocation(14,58);
        Jlb_condition.setSize(128,45);
        Jlb_condition.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_condition);
                      
        //設定排課條件輸入框大小位置及顯示字型
        jcondition.setLocation(137,61);
        jcondition.setSize(447,40);
        jcondition.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jcondition.addActionListener(this);
        c.add(jcondition);
        
        //設定確認Jbtn_confirm按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(598,62);
        Jbtn_confirm.setSize(84,40);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        
        //設定成績label大小位置及顯示字型
        Jlb_result.setLocation(14,130);
        Jlb_result.setSize(1147,45);
        Jlb_result.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_result);
        
        //設定視窗
        setSize(720, 250);
        setLocationRelativeTo(null);//視窗置中
        //setResizable(false);//視窗放大按鈕無效
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm || e.getSource() == jcondition) {
        	String result = "";
        	CourseSchedule cs = new CourseSchedule();
        	
        	//判斷輸入的功能代號
			String courseNum = jcourseNum.getText();
			String condition = jcondition.getText();
			
			//判斷輸入是否為空
			if(courseNum.equals("") || condition.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int num = Integer.parseInt(courseNum);
				int[][] conditionArray = cs.toArray(condition);
				
				//判斷輸入的條件是否有誤
				if(!judgeCondition(num, conditionArray)) {
					JOptionPane.showMessageDialog(new JFrame(), "輸入的數據有誤", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
				//是否可完成排課
				else if(cs.judgeSchedule(num, conditionArray)) {
					result = cs.answer.toString();
				}
				else {
					result = "[]";
				}
				Jlb_result.setText(result);
				jcourseNum.setText("");
				jcondition.setText("");
			}
        }
    }
    
    //判斷輸入之條件和課程數是否相符
    boolean judgeCondition(int num, int[][] arr) {
    	for(int i = 0; i < arr.length; i++) {
    		for(int j = 0; j < arr[i].length; j++) {
    			if(arr[i][j] >= num) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
