import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

class FileSelector extends JFrame{

	//����ɮ�
	File select(){
		//�I�sJFileChooser
		JFileChooser fileChooser = new JFileChooser();
		File selectedFile = null;
		
		//�]�w��l�ؿ�
		fileChooser.setCurrentDirectory(new File("D://"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			String dir =  fileChooser.getCurrentDirectory().getName(); 
			fileChooser.setCurrentDirectory(new File (dir));
		}
		
		//�^�ǿ�ܪ��ɮ�
		return selectedFile;
	}
}