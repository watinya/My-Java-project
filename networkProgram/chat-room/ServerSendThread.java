import java.io.IOException;
import java.io.OutputStream;

public class ServerSendThread extends Thread{
	ServerGui gui;
    private OutputStream out;
    
    public ServerSendThread(ServerGui gui) {
    	this.gui = gui;
    }
    
    //�ǰe�T��
    public void send() {
		try {
			out = gui.socket.getOutputStream();
			
			String message = gui.jMessage.getText();
			message = "Server�G " + message + "\n";
			gui.board.append(message);
			out.write(message.getBytes());
			out.flush();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void run() {
		while (true) {
			System.out.print("");
			//�}�l�ǰT
			if (gui.sendFlag == 1) {
				send();
				gui.sendFlag = 0;
				gui.jMessage.setText("");
			}
		}
    }
}
