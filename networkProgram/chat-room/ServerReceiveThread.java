import java.io.IOException;
import java.io.InputStream;

public class ServerReceiveThread extends Thread{
	ServerGui gui;
    private InputStream in;
    static boolean socketIsConnect = true;
	
	public ServerReceiveThread(ServerGui gui) {
		this.gui = gui;
	}
	
	//±µ¦¬°T®§
	public void receive() {
		try {
			in = gui.socket.getInputStream();
			int n;
			byte[] buf = new byte[1024];

			while (true) {
				System.out.print("");
				
				n = in.read(buf);
				String returnedMessage = new String(buf, 0, n);
				gui.board.append(returnedMessage);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(socketIsConnect) {
			receive();
		}
	}
}
