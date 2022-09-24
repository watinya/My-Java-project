import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;

@SuppressWarnings("serial")
public class ClientGui extends JFrame{
	private JLabel Jlb_serverIP = new JLabel("Server IP�G");
	JTextField jServerIP = new JTextField(20);
	private JLabel Jlb_serverPort = new JLabel("Server Port�G");
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
       
        //�]�w����
        setTitle("P2P��ѫ� Client");
        setSize(600, 500);
        setVisible(true);
        setLocationRelativeTo(null);//�����m��
        setResizable(false);//������j���s�L��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //�]�wIP label
        Jlb_serverIP.setBounds(14, 28, 103, 34);
        Jlb_serverIP.setFont(new Font("�L�n������", Font.PLAIN, 18));
        c.add(Jlb_serverIP);
        
        //�]�wIP��J��
        jServerIP.setBounds(107, 29, 125, 34);
        jServerIP.setFont(new Font("�L�n������", Font.PLAIN, 17));
        jServerIP.setText("127.0.0.1");
        c.add(jServerIP);
        
        //�]�wPort label
        Jlb_serverPort.setBounds(246, 28, 125, 34);
        Jlb_serverPort.setFont(new Font("�L�n������", Font.PLAIN, 18));
        c.add(Jlb_serverPort);
        
        //�]�wPort��J��
        jServerPort.setBounds(360, 29, 91, 34);
        jServerPort.setFont(new Font("�L�n������", Font.PLAIN, 17));
        jServerPort.setText("3000");
        c.add(jServerPort);
        
        //�]�wconnect���s
        Jbtn_connect.setBounds(465, 16, 115, 29);
        Jbtn_connect.setFont(new Font("�L�n������", Font.PLAIN, 16));
        c.add(Jbtn_connect);
        Jbtn_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�P�_��J�O�_����
				if(jServerIP.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "�п�JServer IP", "���~", JOptionPane.ERROR_MESSAGE);
				}
				else if(jServerPort.getText().equals("")){
					JOptionPane.showMessageDialog(new JFrame(), "�п�JServer Port", "���~", JOptionPane.ERROR_MESSAGE);
				}
				else {
					new Client(gui).start();
				}
			}
		});
        
        //�]�wclose���s
        Jbtn_close.setBounds(465, 51, 115, 29);
        Jbtn_close.setFont(new Font("�L�n������", Font.PLAIN, 16));
        c.add(Jbtn_close);
        Jbtn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//����socket
                try {
                	ClientReceiveThread.socketIsConnect = false;
                	socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
        
        //�]�w��Ѥ��e
        board = new JTextArea();
        scrollBoard = new JScrollPane(board);
        scrollBoard.setBounds(14, 89, 566, 321);
        board.setLineWrap(true);
        board.setEditable(false);
        DefaultCaret caret = (DefaultCaret)board.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        board.setFont(new Font("�L�n������", Font.PLAIN, 16));
        c.add(scrollBoard);
        
        //�]�w�T����J��
        jMessage.setBounds(14, 423, 458, 29);
        jMessage.setFont(new Font("�L�n������", Font.PLAIN, 18));
        c.add(jMessage);
        jMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(jMessage.getText().length() != 0) sendFlag = 1;
					else JOptionPane.showMessageDialog(new JFrame(), "�п�J�T��", "���~", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

        //�]�wsend���s
        Jbtn_send.setBounds(489, 423, 91, 29);
        Jbtn_send.setFont(new Font("�L�n������", Font.PLAIN, 16));
        c.add(Jbtn_send);
        Jbtn_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jMessage.getText().length() != 0) sendFlag = 1;
				else JOptionPane.showMessageDialog(new JFrame(), "�п�J�T��", "���~", JOptionPane.ERROR_MESSAGE);
			}
		});
        repaint();
    }
	
}
