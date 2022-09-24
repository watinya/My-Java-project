import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

class FileSelector extends JFrame{

	//選擇檔案
	File select(){
		//呼叫JFileChooser
		JFileChooser fileChooser = new JFileChooser();
		File selectedFile = null;
		
		//設定初始目錄
		fileChooser.setCurrentDirectory(new File("D://"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			String dir =  fileChooser.getCurrentDirectory().getName(); 
			fileChooser.setCurrentDirectory(new File (dir));
		}
		
		//回傳選擇的檔案
		return selectedFile;
	}
}