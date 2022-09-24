import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener{
	private JTextField jtext = new JTextField(20);
    private JButton Jbtn_confirm = new JButton("確認");
	private JLabel Jlb_title = new JLabel("請輸入功能代號：");
	private JLabel Jlb_function = new JLabel("");
    
    public MainFrame()
    {
        super("成績系統");
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定輸入框大小位置及顯示字型
        jtext.setLocation(181,16);
        jtext.setSize(103,40);
        jtext.setFont(new Font("微軟正黑體", Font.BOLD,24));
        jtext.addActionListener(this);
        c.add(jtext);
        
        //設定確認Jbtn_confirm按鈕大小位置及顯示字型
        Jbtn_confirm.setLocation(298,17);
        Jbtn_confirm.setSize(82,40);
        Jbtn_confirm.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        Jbtn_confirm.addActionListener(this);
        c.add(Jbtn_confirm);
        
        //設定Jlb_title大小位置及顯示字型
        Jlb_title.setLocation(14,13);
        Jlb_title.setSize(172,45);
        Jlb_title.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        c.add(Jlb_title);
        
        //設定Jlb_function大小位置及顯示字型
        Jlb_function.setLocation(24,69);
        Jlb_function.setSize(241,244);
        Jlb_function.setText("<html><body>1.查詢學生單科成績<br>2.查詢學生所有成績<br>3.新增學生成績<br>4.刪除學生成績" + 
					"<br>5.排序學生成績<br>6.離開系統</body></html>");
        Jlb_function.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        c.add(Jlb_function);
               
        //設定視窗
        setSize(400, 400);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jtext || e.getSource() == Jbtn_confirm) {
        	//判斷輸入的功能代號
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
					System.exit(0);//離開系統
					break;
				default:
					JOptionPane.showMessageDialog(null, "請輸入正確的代碼", "錯誤", JOptionPane.ERROR_MESSAGE);
					jtext.setText("");
					break;
			}
        }
    }

}
