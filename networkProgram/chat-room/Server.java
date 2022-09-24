import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {
	ServerGui gui;
	
	Server(ServerGui gui) {
		super();
		this.gui = gui;
	}
	
	public void run() {
		
		
		try {
			//socket連線
			gui.serverSocket = new ServerSocket(Integer.parseInt(gui.jServerPort.getText()));
			gui.socket = gui.serverSocket.accept();
			
			//開始接收/傳送訊息的thread
			new ServerSendThread(gui).start();
			new ServerReceiveThread(gui).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
