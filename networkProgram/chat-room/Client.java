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
			//socket連線
			gui.socket = new Socket(gui.jServerIP.getText(), Integer.parseInt(gui.jServerPort.getText()));
			
			//傳送連線成功的訊息
			gui.board.append("連線成功！\n");
			out = gui.socket.getOutputStream();
			String message = "Client0 已經連線\n";
			out.write(message.getBytes());
			out.flush();
			
			//開始接收/傳送訊息的thread
			new ClientSendThread(gui).start();
			new ClientReceiveThread(gui).start();
			
		}catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "啟動伺服器異常", "錯誤", JOptionPane.ERROR_MESSAGE);
		}
	}
}
