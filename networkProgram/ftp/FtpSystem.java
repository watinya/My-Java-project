
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
	FtpClient client;		//client端
	String workingDir = "";	//目前的工作目錄
	
	FtpSystem(String host, String account, String psWord){
		try {
			//產生 FtpClient 物件
			client = FtpClient.create(host);
			//登入
			client.login(account, psWord.toCharArray());
			//設定的傳輸格式為 binary file 
			client.setBinaryType();
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//儲存當前server端的目錄和檔案
	private FtpDirEntry[] lsFiles() {
		ArrayList<FtpDirEntry> arrayList = new ArrayList<FtpDirEntry>();
		Iterator<FtpDirEntry> it = null;
		try {
			it = client.listFiles(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//利用迭代
		while (it.hasNext()) {
			FtpDirEntry entry = it.next();
			arrayList.add(entry);
		}
		FtpDirEntry[] files = new FtpDirEntry[arrayList.size()];
		arrayList.toArray(files);
		return files;
	}
	
	//更換server端目錄位置至path
	FtpDirEntry[] toSelectDir(String path) {
		try {
			client.changeDirectory(path);
			workingDir = client.getWorkingDirectory();
			
			return lsFiles();
		} catch (FtpProtocolException e) {
			JOptionPane.showMessageDialog(new JFrame(), "輸入的路徑" + path + "錯誤", "錯誤", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//更換server端到上一層目錄
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
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
	}

	//PUT file to server
	void putFile(File file) {
		try {
			client.putFile(file.getName(), new FileInputStream(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "請選擇檔案", "錯誤", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error: " + e.getMessage());
		}
	}
}
