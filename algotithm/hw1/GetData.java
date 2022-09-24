import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GetData {
	private static String dataLocation = "data\\grade1.txt";//��Ƹ��|
	
	//Ū���|����ƨæs�J�}�C��
	static String[][] readFile() throws IOException {
		String line;
		ArrayList<String> temp = new ArrayList<String>();
		
		//Ū��
		InputStreamReader read = new InputStreamReader(new FileInputStream(dataLocation), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		while((line = reader.readLine()) != null) {
			temp.add(line);
		}
		reader.close();
		
		//�s�J�}�C
		String[][] arr = new String[temp.size()][temp.get(0).split(" ").length];
		for(int i = 0; i < temp.size(); i++) {
			arr[i] = temp.get(i).split(" ");
		}
		
		return arr;
	}
	
	//�N��J����ئW�٤����Ʀs�J�}�C
	static int[][] getData(String subjectName) {
		try {
			String[][] arr = readFile();
			int temp = 0;
			for(int i = 1; i < arr[0].length; i+=2) {
				if(arr[0][i].equals(subjectName)) temp = i+1;
			}
			
			int[][] data = new int[arr.length][2];
			for(int i = 0; i < arr.length; i++) {
				data[i][0] = Integer.parseInt(arr[i][0]);//�Ǹ�
				data[i][1] = Integer.parseInt(arr[i][temp]);//����
			}
			
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//�P�_��ئ��L�s�b
	static int getSubject(String subjectName) {
		try {
			String[][] data = readFile();
			for(int i = 1; i < data[0].length; i+=2) {
				//�p��ئs�b�h�^�ǸӬ�ظ�Ʀ�m
				if(data[0][i].equals(subjectName)) return i;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//���s�b�h�^��-1
		return -1;
	}
	
	//�P�_�ǥͦs���s�b
	static int getStudent(String studentID) {
		try {
			String[][] data = readFile();
			for(int i = 0; i < data.length; i++) {
				//�p��ئs�b�h�^�ǸӾǥ͸�Ʀ�m
				if(data[i][0].equals(studentID)) return i;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//���s�b�h�^��-1
		return -1;
	}
}
