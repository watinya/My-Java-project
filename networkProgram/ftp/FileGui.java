import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sun.net.ftp.FtpDirEntry;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class FileGui {
	JFrame frmMyClientFtp;
	private JTextField tfCd;		//cd輸入框
	private JTextField tfLcd;		//lcd輸入框
	private JLabel lbDir;			//資料夾標籤
	private JLabel lbFile;			//檔案標籤
	private JPanel panel;			//目錄
	private String lcd;				//存輸入的lcd位置
	private JList<String> list = new JList<String>();//存當前目錄中的檔案與資料夾
	private FtpDirEntry [] files;	//ftp讀取資料
	
	private FtpSystem ftpSystem;	//ftp系統
	private String[] strList;		//存入JList中的字串

	public FileGui() {
		initialize();
	}

	private void initialize() {
		//連線(主機, 帳號, 密碼)
		this.ftpSystem = new FtpSystem("localhost", "abc", "abc");
		
		frmMyClientFtp = new JFrame();
		frmMyClientFtp.setTitle("My Client FTP");
		frmMyClientFtp.setBounds(100, 100, 552, 573);
		frmMyClientFtp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Up按鈕
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(14, 13, 49, 27);
		frmMyClientFtp.getContentPane().setLayout(null);
		frmMyClientFtp.getContentPane().add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//到前一個資料夾
				files = ftpSystem.toParentDir();
				//變更顯示的目錄
				changeDir();
			}
		});
		
		//Dir標籤
		JLabel lbDirCap = new JLabel("Working Dir: ");
		lbDirCap.setBounds(14, 53, 81, 27);
		lbDirCap.setBackground(Color.LIGHT_GRAY);
		frmMyClientFtp.getContentPane().add(lbDirCap);
		
		//CD標籤
		JLabel lbCd = new JLabel("CD");
		lbCd.setBounds(24, 97, 31, 19);
		frmMyClientFtp.getContentPane().add(lbCd);
		//CD輸入框
		tfCd = new JTextField();
		tfCd.setBounds(72, 97, 273, 25);
		frmMyClientFtp.getContentPane().add(tfCd);
		tfCd.setColumns(10);
		//LCD標籤
		JLabel lbLcd = new JLabel("LCD");
		lbLcd.setBounds(14, 135, 36, 19);
		frmMyClientFtp.getContentPane().add(lbLcd);
		//LCD輸入框
		tfLcd = new JTextField();
		tfLcd.setBounds(72, 135, 273, 25);
		frmMyClientFtp.getContentPane().add(tfLcd);
		tfLcd.setColumns(10);
		
		//Get按鈕
		JButton btnGet = new JButton("Get");
		btnGet.setBounds(402, 18, 56, 27);
		frmMyClientFtp.getContentPane().add(btnGet);
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName = "";
				int index = list.getSelectedIndex();//在目錄上所選擇檔案的index
				
				//判斷有無選擇檔案
				if(index == -1) {	
					JOptionPane.showMessageDialog(new JFrame(), "請先選擇檔案", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(files[index].getType() == FtpDirEntry.Type.FILE)
						fileName = files[index].getName();
					//獲取選擇檔案file到指定資料夾lcd
					ftpSystem.getFile(lcd, fileName);
				}
			}
		});
		
		//Put按鈕
		JButton btnPut = new JButton("Put");
		btnPut.setBounds(472, 18, 56, 27);
		frmMyClientFtp.getContentPane().add(btnPut);
		btnPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//呼叫FileSelector
				FileSelector fs = new FileSelector();
				File fileName = fs.select();
				
				//上傳檔案file到遠端
				ftpSystem.putFile(fileName);
			}
		});
		
		//顯示框
		panel = new JPanel();
		panel.setBounds(37, 193, 407, 303);
		panel.setLayout(null);
		frmMyClientFtp.getContentPane().add(panel);
		
		//資料夾位置
		lbDir = new JLabel("");
		lbDir.setBounds(96, 57, 151, 19);
		frmMyClientFtp.getContentPane().add(lbDir);
		list.setBounds(0, 0, 300, 200);
		panel.add(list);
		//偵測滑鼠點擊
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index = list.getSelectedIndex();
				
				//判斷點擊的為檔案還是資料夾
				if(files[index].getType() == FtpDirEntry.Type.FILE) {
					//變更顯示的檔案名稱
					lbFile.setText(files[index].getName());
				} else if(files[index].getType() == FtpDirEntry.Type.DIR) {
					//到所選擇的資料夾
					files = ftpSystem.toSelectDir(files[index].getName());
					//變更顯示的目錄
					changeDir();
				}
			}
		});
		
		//檔案標題標籤
		JLabel lbFileCap = new JLabel("File: ");
		lbFileCap.setBounds(367, 57, 31, 19);
		frmMyClientFtp.getContentPane().add(lbFileCap);
		//檔案名
		lbFile = new JLabel("");
		lbFile.setBounds(396, 57, 107, 19);
		frmMyClientFtp.getContentPane().add(lbFile);
		
		//執行CD按鈕
		JButton btnCd = new JButton("execute CD");
		btnCd.setBounds(377, 93, 110, 27);
		frmMyClientFtp.getContentPane().add(btnCd);
		btnCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cdPath = tfCd.getText();
				//判斷輸入是否為空
				if(cdPath.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空白", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//移動到輸入的資料夾目錄
					files = ftpSystem.toSelectDir(cdPath);
					//變更顯示的目錄
					changeDir();
				}
				tfCd.setText("");
			}
		});
		
		//執行LCD按鈕
		JButton btnLcd = new JButton("excute LCD");
		btnLcd.setBounds(377, 131, 110, 27);
		frmMyClientFtp.getContentPane().add(btnLcd);
		btnLcd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//紀錄lcd目的地
				lcd = tfLcd.getText();
				if(lcd.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "輸入不可為空白", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//初始化
		files = ftpSystem.toSelectDir(".");
		//變更顯示的目錄
		changeDir();
	}
	
	//變更目前所在的資料夾目錄及GUI顯示
	void changeDir() {
		//將FtpDirEntry轉成String，並加到JList中
		strList = toStringArr(files);
		list.setListData(strList);
		
		//變更顯示的目錄位置及選取檔案
		lbDir.setText(ftpSystem.workingDir);
		lbFile.setText("");
	}
	
	//將FtpDirEntry轉成String陣列，並回傳
	String[] toStringArr(FtpDirEntry[] files) {
		String[] fileList = new String[files.length];
		for(int i = 0; i < files.length; i++) {
			fileList[i] = files[i].getName();
			
			//判斷檔案型別是否為資料夾
			if(files[i].getType() == FtpDirEntry.Type.DIR)
				fileList[i] += "(dir)";
		}
		return fileList;
	}
}
