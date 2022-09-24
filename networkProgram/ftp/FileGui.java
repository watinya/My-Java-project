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
	private JTextField tfCd;		//cd��J��
	private JTextField tfLcd;		//lcd��J��
	private JLabel lbDir;			//��Ƨ�����
	private JLabel lbFile;			//�ɮ׼���
	private JPanel panel;			//�ؿ�
	private String lcd;				//�s��J��lcd��m
	private JList<String> list = new JList<String>();//�s��e�ؿ������ɮ׻P��Ƨ�
	private FtpDirEntry [] files;	//ftpŪ�����
	
	private FtpSystem ftpSystem;	//ftp�t��
	private String[] strList;		//�s�JJList�����r��

	public FileGui() {
		initialize();
	}

	private void initialize() {
		//�s�u(�D��, �b��, �K�X)
		this.ftpSystem = new FtpSystem("localhost", "abc", "abc");
		
		frmMyClientFtp = new JFrame();
		frmMyClientFtp.setTitle("My Client FTP");
		frmMyClientFtp.setBounds(100, 100, 552, 573);
		frmMyClientFtp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Up���s
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(14, 13, 49, 27);
		frmMyClientFtp.getContentPane().setLayout(null);
		frmMyClientFtp.getContentPane().add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//��e�@�Ӹ�Ƨ�
				files = ftpSystem.toParentDir();
				//�ܧ���ܪ��ؿ�
				changeDir();
			}
		});
		
		//Dir����
		JLabel lbDirCap = new JLabel("Working Dir: ");
		lbDirCap.setBounds(14, 53, 81, 27);
		lbDirCap.setBackground(Color.LIGHT_GRAY);
		frmMyClientFtp.getContentPane().add(lbDirCap);
		
		//CD����
		JLabel lbCd = new JLabel("CD");
		lbCd.setBounds(24, 97, 31, 19);
		frmMyClientFtp.getContentPane().add(lbCd);
		//CD��J��
		tfCd = new JTextField();
		tfCd.setBounds(72, 97, 273, 25);
		frmMyClientFtp.getContentPane().add(tfCd);
		tfCd.setColumns(10);
		//LCD����
		JLabel lbLcd = new JLabel("LCD");
		lbLcd.setBounds(14, 135, 36, 19);
		frmMyClientFtp.getContentPane().add(lbLcd);
		//LCD��J��
		tfLcd = new JTextField();
		tfLcd.setBounds(72, 135, 273, 25);
		frmMyClientFtp.getContentPane().add(tfLcd);
		tfLcd.setColumns(10);
		
		//Get���s
		JButton btnGet = new JButton("Get");
		btnGet.setBounds(402, 18, 56, 27);
		frmMyClientFtp.getContentPane().add(btnGet);
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName = "";
				int index = list.getSelectedIndex();//�b�ؿ��W�ҿ���ɮת�index
				
				//�P�_���L����ɮ�
				if(index == -1) {	
					JOptionPane.showMessageDialog(new JFrame(), "�Х�����ɮ�", "���~", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(files[index].getType() == FtpDirEntry.Type.FILE)
						fileName = files[index].getName();
					//�������ɮ�file����w��Ƨ�lcd
					ftpSystem.getFile(lcd, fileName);
				}
			}
		});
		
		//Put���s
		JButton btnPut = new JButton("Put");
		btnPut.setBounds(472, 18, 56, 27);
		frmMyClientFtp.getContentPane().add(btnPut);
		btnPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�I�sFileSelector
				FileSelector fs = new FileSelector();
				File fileName = fs.select();
				
				//�W���ɮ�file�컷��
				ftpSystem.putFile(fileName);
			}
		});
		
		//��ܮ�
		panel = new JPanel();
		panel.setBounds(37, 193, 407, 303);
		panel.setLayout(null);
		frmMyClientFtp.getContentPane().add(panel);
		
		//��Ƨ���m
		lbDir = new JLabel("");
		lbDir.setBounds(96, 57, 151, 19);
		frmMyClientFtp.getContentPane().add(lbDir);
		list.setBounds(0, 0, 300, 200);
		panel.add(list);
		//�����ƹ��I��
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index = list.getSelectedIndex();
				
				//�P�_�I�������ɮ��٬O��Ƨ�
				if(files[index].getType() == FtpDirEntry.Type.FILE) {
					//�ܧ���ܪ��ɮצW��
					lbFile.setText(files[index].getName());
				} else if(files[index].getType() == FtpDirEntry.Type.DIR) {
					//��ҿ�ܪ���Ƨ�
					files = ftpSystem.toSelectDir(files[index].getName());
					//�ܧ���ܪ��ؿ�
					changeDir();
				}
			}
		});
		
		//�ɮ׼��D����
		JLabel lbFileCap = new JLabel("File: ");
		lbFileCap.setBounds(367, 57, 31, 19);
		frmMyClientFtp.getContentPane().add(lbFileCap);
		//�ɮצW
		lbFile = new JLabel("");
		lbFile.setBounds(396, 57, 107, 19);
		frmMyClientFtp.getContentPane().add(lbFile);
		
		//����CD���s
		JButton btnCd = new JButton("execute CD");
		btnCd.setBounds(377, 93, 110, 27);
		frmMyClientFtp.getContentPane().add(btnCd);
		btnCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cdPath = tfCd.getText();
				//�P�_��J�O�_����
				if(cdPath.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "��J���i���ť�", "���~", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//���ʨ��J����Ƨ��ؿ�
					files = ftpSystem.toSelectDir(cdPath);
					//�ܧ���ܪ��ؿ�
					changeDir();
				}
				tfCd.setText("");
			}
		});
		
		//����LCD���s
		JButton btnLcd = new JButton("excute LCD");
		btnLcd.setBounds(377, 131, 110, 27);
		frmMyClientFtp.getContentPane().add(btnLcd);
		btnLcd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//����lcd�ت��a
				lcd = tfLcd.getText();
				if(lcd.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "��J���i���ť�", "���~", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//��l��
		files = ftpSystem.toSelectDir(".");
		//�ܧ���ܪ��ؿ�
		changeDir();
	}
	
	//�ܧ�ثe�Ҧb����Ƨ��ؿ���GUI���
	void changeDir() {
		//�NFtpDirEntry�নString�A�å[��JList��
		strList = toStringArr(files);
		list.setListData(strList);
		
		//�ܧ���ܪ��ؿ���m�ο���ɮ�
		lbDir.setText(ftpSystem.workingDir);
		lbFile.setText("");
	}
	
	//�NFtpDirEntry�নString�}�C�A�æ^��
	String[] toStringArr(FtpDirEntry[] files) {
		String[] fileList = new String[files.length];
		for(int i = 0; i < files.length; i++) {
			fileList[i] = files[i].getName();
			
			//�P�_�ɮ׫��O�O�_����Ƨ�
			if(files[i].getType() == FtpDirEntry.Type.DIR)
				fileList[i] += "(dir)";
		}
		return fileList;
	}
}
