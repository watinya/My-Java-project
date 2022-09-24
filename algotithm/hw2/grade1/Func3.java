package grade1;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Func3 extends JFrame implements ActionListener{
	private JLabel Jlb_data = new JLabel("�Ǹ��ά�ئ��Z�G");
	private JTextField jdata = new JTextField(50);
    private JButton Jbtn_confirm = new JButton("�T�{");
    private JLabel Jlb_score = new JLabel("");
    
    public Func3()
    {
        super("�s�W���Z");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�w�Ǹ��ά�ئ��Zlabel�j�p��m����ܦr��
        Jlb_data.setLocation(14,13);
        Jlb_data.setSize(206,45);
        Jlb_data.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_data);
                      
        //�]�w��J�ؤj�p��m����ܦr��
        jdata.setLocation(14,62);
        jdata.setSize(557,40);
        jdata.setFont(new Font("�L�n������", Font.BOLD,24));
        jdata.addActionListener(this);
        c.add(jdata);
        
        //�]�w�T�{Jbtn_confirm���s�j�p��m����ܦr��
        Jbtn_confirm.setLocation(585,62);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("�L�n������", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        Jlb_score.setForeground(new Color(65, 105, 225));
        
        //�]�w���Zlabel�j�p��m����ܦr��
        Jlb_score.setLocation(14,107);
        Jlb_score.setSize(663,45);
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
        if(e.getSource() == Jbtn_confirm || e.getSource() == jdata) {
        	String info;
        	
        	//�P�_��J���\��N��
			String data = jdata.getText();
			
			if(data.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "��J���i����", "���~", JOptionPane.ERROR_MESSAGE);
			}
			else {
				GradeSystem.insertGrade(data);
				info = data + " �s�W���\�I";
				Jlb_score.setText(info);
			}
        }
    }
}
