package grade2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Func4 extends JFrame implements ActionListener{
	private JLabel Jlb_ID = new JLabel("�Ǹ��G");
	private JTextField jstudentID = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("�T�{");
    private JLabel Jlb_score = new JLabel("");
    
    public Func4()
    {
        super("�R�����Z");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�w�Ǹ�label�j�p��m����ܦr��
        Jlb_ID.setLocation(24,25);
        Jlb_ID.setSize(89,45);
        Jlb_ID.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_ID);
                
        //�]�w�Ǹ���J�ؤj�p��m����ܦr��
        jstudentID.setLocation(99,28);
        jstudentID.setSize(103,40);
        jstudentID.setFont(new Font("�L�n������", Font.BOLD,24));
        jstudentID.addActionListener(this);
        c.add(jstudentID);
                
        //�]�w�T�{Jbtn_confirm���s�j�p��m����ܦr��
        Jbtn_confirm.setLocation(227,29);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("�L�n������", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_score.setForeground(Color.RED);
        
        //�]�w���Zlabel�j�p��m����ܦr��
        Jlb_score.setLocation(24,149);
        Jlb_score.setSize(356,45);
        Jlb_score.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_score);
        
        //�]�w����
        setSize(400, 300);
        setLocationRelativeTo(null);//�����m��
        setResizable(false);//������j���s�L��
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm || e.getSource() == jstudentID) {
        	String info;
        	
        	//�P�_��J���\��N��
			String studentID = jstudentID.getText();
			
			if(studentID.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "��J���i����", "���~", JOptionPane.ERROR_MESSAGE);
			}
			else {
				GradeSystem.deleteGrade(studentID);
				info = studentID + " �R�����\�I";
				Jlb_score.setText(info);
			}
        }
    }

}
