import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;

@SuppressWarnings("serial")
public class ClientGui extends JFrame{
	private JLabel Jlb_serverIP = new JLabel("Server IP：");
	JTextField jServerIP = new JTextField(20);
	private JLabel Jlb_serverPort = new JLabel("Server Port：");
	JTextField jServerPort = new JTextField(20);
    private JButton Jbtn_connect = new JButton("Connect");
    private JButton Jbtn_close = new JButton("Close");
    JTextArea board;
    private JScrollPane scrollBoard;
    JTextField jMessage = new JTextField(20);
    private JButton Jbtn_send = new JButton("Send");
    ClientGui gui;
    Socket socket;
    int sendFlag;
    
    public ClientGui() {
    	this.gui = this;
        Container c = getContentPane();
        c.setLayout(null);
       
        //設定視窗
        setTitle("P2P聊天室 Client");
        setSize(600, 500);
        setVisible(true);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //設定IP label
        Jlb_serverIP.setBounds(14, 28, 103, 34);
        Jlb_serverIP.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        c.add(Jlb_serverIP);
        
        //設定IP輸入框
        jServerIP.setBounds(107, 29, 125, 34);
        jServerIP.setFont(new Font("微軟正黑體", Font.PLAIN, 17));
        jServerIP.setText("127.0.0.1");
        c.add(jServerIP);
        
        //設定Port label
        Jlb_serverPort.setBounds(246, 28, 125, 34);
        Jlb_serverPort.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        c.add(Jlb_serverPort);
        
        //設定Port輸入框
        jServerPort.setBounds(360, 29, 91, 34);
        jServerPort.setFont(new Font("微軟正黑體", Font.PLAIN, 17));
        jServerPort.setText("3000");
        c.add(jServerPort);
        
        //設定connect按鈕
        Jbtn_connect.setBounds(465, 16, 115, 29);
        Jbtn_connect.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        c.add(Jbtn_connect);
        Jbtn_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//判斷輸入是否為空
				if(jServerIP.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "請輸入Server IP", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
				else if(jServerPort.getText().equals("")){
					JOptionPane.showMessageDialog(new JFrame(), "請輸入Server Port", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
				else {
					new Client(gui).start();
				}
			}
		});
        
        //設定close按鈕
        Jbtn_close.setBounds(465, 51, 115, 29);
        Jbtn_close.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        c.add(Jbtn_close);
        Jbtn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//關閉socket
                try {
                	ClientReceiveThread.socketIsConnect = false;
                	socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
        
        //設定聊天內容
        board = new JTextArea();
        scrollBoard = new JScrollPane(board);
        scrollBoard.setBounds(14, 89, 566, 321);
        board.setLineWrap(true);
        board.setEditable(false);
        DefaultCaret caret = (DefaultCaret)board.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        board.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        c.add(scrollBoard);
        
        //設定訊息輸入框
        jMessage.setBounds(14, 423, 458, 29);
        jMessage.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        c.add(jMessage);
        jMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(jMessage.getText().length() != 0) sendFlag = 1;
					else JOptionPane.showMessageDialog(new JFrame(), "請輸入訊息", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

        //設定send按鈕
        Jbtn_send.setBounds(489, 423, 91, 29);
        Jbtn_send.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        c.add(Jbtn_send);
        Jbtn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jMessage.getText().length() != 0) sendFlag = 1;
				else JOptionPane.showMessageDialog(new JFrame(), "請輸入訊息", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
		});
        repaint();
    }
	
}
