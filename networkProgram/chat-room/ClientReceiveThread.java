import java.io.IOException;
import java.io.InputStream;

public class ClientReceiveThread extends Thread{
	ClientGui gui;
    private InputStream in;
    static boolean socketIsConnect = true;
	
	public ClientReceiveThread(ClientGui gui) {
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
				gui.board.append("Server¡G" + returnedMessage);
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
