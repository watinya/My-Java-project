
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

public class FtpSystem {
	FtpClient client;		//client��
	String workingDir = "";	//�ثe���u�@�ؿ�
	
	FtpSystem(String host, String account, String psWord){
		try {
			//���� FtpClient ����
			client = FtpClient.create(host);
			//�n�J
			client.login(account, psWord.toCharArray());
			//�]�w���ǿ�榡�� binary file 
			client.setBinaryType();
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�x�s��eserver�ݪ��ؿ��M�ɮ�
	private FtpDirEntry[] lsFiles() {
		ArrayList<FtpDirEntry> arrayList = new ArrayList<FtpDirEntry>();
		Iterator<FtpDirEntry> it = null;
		try {
			it = client.listFiles(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�Q�έ��N
		while (it.hasNext()) {
			FtpDirEntry entry = it.next();
			arrayList.add(entry);
		}
		FtpDirEntry[] files = new FtpDirEntry[arrayList.size()];
		arrayList.toArray(files);
		return files;
	}
	
	//��server�ݥؿ���m��path
	FtpDirEntry[] toSelectDir(String path) {
		try {
			client.changeDirectory(path);
			workingDir = client.getWorkingDirectory();
			
			return lsFiles();
		} catch (FtpProtocolException e) {
			JOptionPane.showMessageDialog(new JFrame(), "��J�����|" + path + "���~", "���~", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//��server�ݨ�W�@�h�ؿ�
	FtpDirEntry[] toParentDir() {
		try {
			client.changeToParentDirectory();
			workingDir = client.getWorkingDirectory();
			
			return lsFiles();
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//GET file from server to dir
	void getFile(String dir, String filename) {
		File inFile = new File(dir + "\\" + filename);
		try {
			client.getFile(inFile.getName(), new FileOutputStream(inFile));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "���~", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
	}

	//PUT file to server
	void putFile(File file) {
		try {
			client.putFile(file.getName(), new FileInputStream(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "�п���ɮ�", "���~", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error: " + e.getMessage());
		}
	}
}
