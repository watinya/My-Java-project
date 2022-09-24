import java.io.IOException;
import java.io.OutputStream;

public class ClientSendThread extends Thread{
	ClientGui gui;
    private OutputStream out;
    
    public ClientSendThread(ClientGui gui) {
    	this.gui = gui;
    }
    
    //�ǰe�T��
    public void send() {
		try {
			out = gui.socket.getOutputStream();
			
			String message = gui.jMessage.getText();
			message = "Client0�G " + message + "\n";
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
