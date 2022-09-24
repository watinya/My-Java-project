
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Func6 extends JFrame implements ActionListener{
	private JLabel Jlb_subjectName = new JLabel("��ئW�١G");
	private JTextField jsubjectName = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("�T�{");
    private JLabel Jlb_subArray = new JLabel("");
    
    public Func6()
    {
        super("�̪����W���Z�l�ǦC");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�w��ئW��label�j�p��m����ܦr��
        Jlb_subjectName.setLocation(14,25);
        Jlb_subjectName.setSize(142,45);
        Jlb_subjectName.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_subjectName);
                
        //�]�w��ؿ�J�ؤj�p��m����ܦr��
        jsubjectName.setLocation(142,28);
        jsubjectName.setSize(103,40);
        jsubjectName.setFont(new Font("�L�n������", Font.BOLD,24));
        jsubjectName.addActionListener(this);
        c.add(jsubjectName);
                
        //�]�w�T�{Jbtn_confirm���s�j�p��m����ܦr��
        Jbtn_confirm.setLocation(260,29);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("�L�n������", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_subArray.setForeground(new Color(65, 105, 225));
        
        //�]�w�̪��l�ǦClabel�j�p��m����ܦr��
        Jlb_subArray.setLocation(14,83);
        Jlb_subArray.setSize(1198,116);
        Jlb_subArray.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_subArray);
        
        //�]�w����
        setSize(700, 270);
        setLocationRelativeTo(null);//�����m��
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Jbtn_confirm || e.getSource() == jsubjectName) {
        	//�P�_��J���\��N��
        	String info;
			String subjectName = jsubjectName.getText();
			int[] score = GradeSystem.getGrade(subjectName);
			
			//��J����
			if(subjectName.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "��J���i����", "���~", JOptionPane.ERROR_MESSAGE);
			}
			//�S����J����ئ��Z
			else if(!GradeSystem.searchSubject(subjectName)) {
				info = "�S�� " + subjectName + " ��ؤ����Z";
				Jlb_subArray.setForeground(Color.RED);
				Jlb_subArray.setText(info);
				jsubjectName.setText("");
			}
			else {
				int longestLen = GradeSystem.longestIncreasingSubsequence(score);
				String scoreData = Arrays.toString(score);
				scoreData = scoreData.substring(1,scoreData.length()-1);
				info = "<html><body>" + subjectName + " ���Z�ǦC�� " + scoreData + "<br>�̪����W�l�ǦC���׬� " + longestLen + "</html></body>";
				Jlb_subArray.setForeground(new Color(65, 105, 225));
				Jlb_subArray.setText(info);
				jsubjectName.setText("");
			}
        }
    }
}
