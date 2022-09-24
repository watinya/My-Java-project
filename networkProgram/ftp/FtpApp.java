import java.awt.EventQueue;

public class FtpApp {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//©I¥sFileGui
					FileGui window = new FileGui();
					window.frmMyClientFtp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
