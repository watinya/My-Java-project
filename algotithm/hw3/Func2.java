
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Func2 extends JFrame implements ActionListener{
	private JLabel Jlb_ID = new JLabel("�Ǹ��G");
	private JTextField jstudentID = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("�T�{");
    private JLabel Jlb_score = new JLabel("");
    
    public Func2()
    {
        super("�d�ߩҦ����Z");
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
        Jlb_score.setForeground(new Color(65, 105, 225));
        
        //�]�w���Zlabel�j�p��m����ܦr��
        Jlb_score.setLocation(24,83);
        Jlb_score.setSize(656,45);
        Jlb_score.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_score);
        
        //�]�w����
        setSize(700, 200);
        setLocationRelativeTo(null);//�����m��
        setResizable(false);//������j���s�L��
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm || e.getSource() == jstudentID) {
        	//�P�_��J���\��N��
        	String info;
			String studentID = jstudentID.getText();
			String score = GradeSystem.searchAllGrade(studentID);
			
			if(studentID.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "��J���i����", "���~", JOptionPane.ERROR_MESSAGE);
			}
			else if(score == null) {
				info = "�L " + studentID + " �����";
				Jlb_score.setForeground(Color.RED);
				Jlb_score.setText(info);
			}
			else if(score.equals("")) {
				info = studentID + " ���ǥͨèS�׽�";
				Jlb_score.setForeground(Color.RED);
				Jlb_score.setText(info);
			}
			else {
				info = studentID + " �ǥͪ� ���Ƭ� " + score;
				Jlb_score.setForeground(new Color(65, 105, 225));
				Jlb_score.setText(info);
			}
        }
    }

}
