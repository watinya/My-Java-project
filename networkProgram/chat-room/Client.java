import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Client extends Thread{
	ClientGui gui;
	OutputStream out;
	
	Client(ClientGui gui) {
		super();
		this.gui = gui;
	}
	
	public void run() {
		try {
			//socket�s�u
			gui.socket = new Socket(gui.jServerIP.getText(), Integer.parseInt(gui.jServerPort.getText()));
			
			//�ǰe�s�u���\���T��
			gui.board.append("�s�u���\�I\n");
			out = gui.socket.getOutputStream();
			String message = "Client0 �w�g�s�u\n";
			out.write(message.getBytes());
			out.flush();
			
			//�}�l����/�ǰe�T����thread
			new ClientSendThread(gui).start();
			new ClientReceiveThread(gui).start();
			
		}catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "�Ұʦ��A�����`", "���~", JOptionPane.ERROR_MESSAGE);
		}
	}
}
