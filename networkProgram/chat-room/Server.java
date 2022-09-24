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
			//socket�s�u
			gui.serverSocket = new ServerSocket(Integer.parseInt(gui.jServerPort.getText()));
			gui.socket = gui.serverSocket.accept();
			
			//�}�l����/�ǰe�T����thread
			new ServerSendThread(gui).start();
			new ServerReceiveThread(gui).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
