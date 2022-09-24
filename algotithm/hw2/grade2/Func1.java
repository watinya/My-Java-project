package grade2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Func1 extends JFrame implements ActionListener{
	private JLabel Jlb_ID = new JLabel("�Ǹ��G");
	private JLabel Jlb_subject = new JLabel("��ءG");
	private JTextField jstudentID = new JTextField(20);
	private JTextField jsubjectName = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("�T�{");
    private JLabel Jlb_score = new JLabel("");
    
    public Func1()
    {
        super("�d�߳�즨�Z");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�w�Ǹ�label�j�p��m����ܦr��
        Jlb_ID.setLocation(24,25);
        Jlb_ID.setSize(89,45);
        Jlb_ID.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_ID);
        
        //�]�w���label�j�p��m����ܦr��
        Jlb_subject.setLocation(24,74);
        Jlb_subject.setSize(89,45);
        Jlb_subject.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_subject);
        
        //�]�w�Ǹ���J�ؤj�p��m����ܦr��
        jstudentID.setLocation(99,28);
        jstudentID.setSize(103,40);
        jstudentID.setFont(new Font("�L�n������", Font.BOLD,24));
        jstudentID.addActionListener(this);
        c.add(jstudentID);
        
        //�]�w��ؿ�J�ؤj�p��m����ܦr��
        jsubjectName.setLocation(99,77);
        jsubjectName.setSize(103,40);
        jsubjectName.setFont(new Font("�L�n������", Font.BOLD,24));
        jsubjectName.addActionListener(this);
        c.add(jsubjectName);
        
        //�]�w�T�{Jbtn_confirm���s�j�p��m����ܦr��
        Jbtn_confirm.setLocation(228,78);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("�L�n������", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_score.setForeground(new Color(65, 105, 225));
        
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
        if(e.getSource() == Jbtn_confirm || e.getSource() == jsubjectName) {
        	String info;
        	//�P�_��J���\��N��
			String studentID = jstudentID.getText();
			String subject = jsubjectName.getText();
			int score = GradeSystem.searchGrade(studentID, subject);
			
			if(studentID.equals("") || subject.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "��J���i����", "���~", JOptionPane.ERROR_MESSAGE);
			}
			else if(score == -1) {
				info = "�L " + studentID + " �� " + subject + "��� ���Z";
				Jlb_score.setForeground(Color.RED);
				Jlb_score.setText(info);
			}
			else {
				info = studentID + " �ǥͪ� " + subject + " ���Ƭ� " + score;
				Jlb_score.setForeground(new Color(65, 105, 225));
				Jlb_score.setText(info);
			}
        }
    }

}
