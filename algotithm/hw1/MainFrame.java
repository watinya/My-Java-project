import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener{
	private JTextField jtext = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("�T�{");
	private JLabel Jlb_title = new JLabel("�п�J�\��N���G");
	private JLabel Jlb_function = new JLabel("");
    
    public MainFrame()
    {
        super("���Z�t��");
        Container c = getContentPane();
        c.setLayout(null);
       
        //�]�w��J�ؤj�p��m����ܦr��
        jtext.setLocation(181,16);
        jtext.setSize(103,40);
        jtext.setFont(new Font("�L�n������", Font.BOLD,24));
        jtext.addActionListener(this);
        c.add(jtext);
        
        //�]�w�T�{Jbtn_confirm���s�j�p��m����ܦr��
        Jbtn_confirm.setLocation(298,17);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("�L�n������", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        
        //�]�wJlb_title�j�p��m����ܦr��
        Jlb_title.setLocation(14,13);
        Jlb_title.setSize(172,45);
        Jlb_title.setFont(new Font("�L�n������", Font.BOLD, 20));
        c.add(Jlb_title);
        
        //�]�wJlb_function�j�p��m����ܦr��
        Jlb_function.setLocation(24,69);
        Jlb_function.setSize(241,244);
        Jlb_function.setText("<html><body>1.�d�߾ǥͳ�즨�Z<br>2.�d�߾ǥͩҦ����Z<br>3.�s�W�ǥͦ��Z<br>4.�R���ǥͦ��Z" + 
					"<br>5.�ƧǾǥͦ��Z<br>6.���}�t��</body></html>");
        Jlb_function.setFont(new Font("�L�n������", Font.BOLD, 24));
        c.add(Jlb_function);
               
        //�]�w����
        setSize(400, 400);
        setLocationRelativeTo(null);//�����m��
        setResizable(false);//������j���s�L��
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jtext || e.getSource() == Jbtn_confirm) {
        	//�P�_��J���\��N��
			String functionNum = jtext.getText();
			switch (functionNum){
				case "1":
					new Func1();
					jtext.setText("");
					break;
				case "2":
					new Func2();
					jtext.setText("");
					break;
				case "3":
					new Func3();
					jtext.setText("");
					break;
				case "4":
					new Func4();
					jtext.setText("");
					break;
				case "5":
					new Func5();
					jtext.setText("");
					break;
				case "6":
					System.exit(0);//���}�t��
					break;
				default:
					JOptionPane.showMessageDialog(null, "�п�J���T���N�X", "���~", JOptionPane.ERROR_MESSAGE);
					jtext.setText("");
					break;
			}
        }
    }

}
