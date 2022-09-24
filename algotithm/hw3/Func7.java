import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Func7 extends JFrame implements ActionListener{
	private JLabel Jlb_courseNum = new JLabel("�ҵ{�`�ơG");
	private JTextField jcourseNum = new JTextField(50);
	private JLabel Jlb_condition = new JLabel("���M����G");
	private JTextField jcondition = new JTextField(50);
    private JButton Jbtn_confirm = new JButton("�T�{");
    private JLabel Jlb_result = new JLabel("");
    
    public Func7()
    {
        super("�ƽ�");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�w���ƽҵ{��label�j�p��m����ܦr��
        Jlb_courseNum.setLocation(14,13);
        Jlb_courseNum.setSize(128,45);
        Jlb_courseNum.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_courseNum);
                      
        //�]�w�ҵ{��J�ؤj�p��m����ܦr��
        jcourseNum.setLocation(137,16);
        jcourseNum.setSize(82,40);
        jcourseNum.setFont(new Font("�L�n������", Font.BOLD,24));
        jcourseNum.addActionListener(this);
        c.add(jcourseNum);
        
        //�]�w����label�j�p��m����ܦr��
        Jlb_condition.setLocation(14,58);
        Jlb_condition.setSize(128,45);
        Jlb_condition.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_condition);
                      
        //�]�w�ƽұ����J�ؤj�p��m����ܦr��
        jcondition.setLocation(137,61);
        jcondition.setSize(447,40);
        jcondition.setFont(new Font("�L�n������", Font.BOLD,24));
        jcondition.addActionListener(this);
        c.add(jcondition);
        
        //�]�w�T�{Jbtn_confirm���s�j�p��m����ܦr��
        Jbtn_confirm.setLocation(598,62);
        Jbtn_confirm.setSize(84,40);
        Jbtn_confirm.setFont(new Font("�L�n������", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        
        //�]�w���Zlabel�j�p��m����ܦr��
        Jlb_result.setLocation(14,130);
        Jlb_result.setSize(1147,45);
        Jlb_result.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_result);
        
        //�]�w����
        setSize(720, 250);
        setLocationRelativeTo(null);//�����m��
        //setResizable(false);//������j���s�L��
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm || e.getSource() == jcondition) {
        	String result = "";
        	CourseSchedule cs = new CourseSchedule();
        	
        	//�P�_��J���\��N��
			String courseNum = jcourseNum.getText();
			String condition = jcondition.getText();
			
			//�P�_��J�O�_����
			if(courseNum.equals("") || condition.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "��J���i����", "���~", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int num = Integer.parseInt(courseNum);
				int[][] conditionArray = cs.toArray(condition);
				
				//�P�_��J������O�_���~
				if(!judgeCondition(num, conditionArray)) {
					JOptionPane.showMessageDialog(new JFrame(), "��J���ƾڦ��~", "���~", JOptionPane.ERROR_MESSAGE);
				}
				//�O�_�i�����ƽ�
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
    
    //�P�_��J������M�ҵ{�ƬO�_�۲�
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
