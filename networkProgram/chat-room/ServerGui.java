import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("serial")
public class ServerGui extends JFrame{
	private JLabel Jlb_serverPort = new JLabel("Server Port：");
	JTextField jServerPort = new JTextField(20);
    private JButton Jbtn_connect = new JButton("開始聆聽");
    private JButton Jbtn_close = new JButton("Close");
    JTextArea board = new JTextArea();
    private JScrollPane scrollBoard = new JScrollPane(board);
    JTextField jMessage = new JTextField(20);
    private JButton Jbtn_send = new JButton("傳送");
    ServerGui gui;
    ServerSocket serverSocket;
    Socket socket;
    int sendFlag;
    
    public ServerGui() {
    	this.gui = this;
        Container c = getContentPane();
        c.setLayout(null);
        
    	//設定視窗
    	setTitle("P2P聊天室 Server");
        setSize(600, 500);
        setVisible(true);
        setLocationRelativeTo(null);//視窗置中
        setResizable(false);//視窗放大按鈕無效
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //設定Port label
        Jlb_serverPort.setBounds(14, 28, 125, 34);
        Jlb_serverPort.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        c.add(Jlb_serverPort);
        
        //設定Port輸入框
        jServerPort.setBounds(130, 29, 91, 34);
        jServerPort.setFont(new Font("微軟正黑體", Font.PLAIN, 17));
        jServerPort.setText("3000");
        c.add(jServerPort);
        
        //設定聆聽按鈕
        Jbtn_connect.setBounds(465, 16, 115, 29);
        Jbtn_connect.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        c.add(Jbtn_connect);
        Jbtn_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jServerPort.getText().equals("")) 
					JOptionPane.showMessageDialog(new JFrame(), "請輸入Server Port", "錯誤", JOptionPane.ERROR_MESSAGE);
				else{
					int port = Integer.parseInt(jServerPort.getText());
					board.setText("開始聆聽於 " + port + "\n");
				}
                new Server(gui).start();
			}
		});
        
        //設定close按鈕
        Jbtn_close.setBounds(465, 51, 115, 29);
        Jbtn_close.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        c.add(Jbtn_close);
        Jbtn_close.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent args) {
        		try {
        			ServerReceiveThread.socketIsConnect = false;
        			serverSocket.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        		System.exit(0);
			}
		});
        
        //設定聊天內容
        scrollBoard.setBounds(14, 89, 566, 321);
        board.setLineWrap(true);
        board.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        DefaultCaret caret = (DefaultCaret)board.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        board.setEditable(false);
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

        //設定傳送按鈕
        Jbtn_send.setBounds(489, 423, 91, 29);
        Jbtn_send.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        c.add(Jbtn_send);
        Jbtn_send.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		if(jMessage.getText().length() != 0) sendFlag = 1;
        		else JOptionPane.showMessageDialog(new JFrame(), "請輸入訊息", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
		});
        repaint();
    }

}
