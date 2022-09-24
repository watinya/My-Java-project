import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GetData {
	private static String dataLocation = "data\\grade1.txt";//資料路徑
	
	//讀路徑的資料並存入陣列中
	static String[][] readFile() throws IOException {
		String line;
		ArrayList<String> temp = new ArrayList<String>();
		
		//讀檔
		InputStreamReader read = new InputStreamReader(new FileInputStream(dataLocation), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		while((line = reader.readLine()) != null) {
			temp.add(line);
		}
		reader.close();
		
		//存入陣列
		String[][] arr = new String[temp.size()][temp.get(0).split(" ").length];
		for(int i = 0; i < temp.size(); i++) {
			arr[i] = temp.get(i).split(" ");
		}
		
		return arr;
	}
	
	//將輸入的科目名稱之分數存入陣列
	static int[][] getData(String subjectName) {
		try {
			String[][] arr = readFile();
			int temp = 0;
			for(int i = 1; i < arr[0].length; i+=2) {
				if(arr[0][i].equals(subjectName)) temp = i+1;
			}
			
			int[][] data = new int[arr.length][2];
			for(int i = 0; i < arr.length; i++) {
				data[i][0] = Integer.parseInt(arr[i][0]);//學號
				data[i][1] = Integer.parseInt(arr[i][temp]);//分數
			}
			
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//判斷科目有無存在
	static int getSubject(String subjectName) {
		try {
			String[][] data = readFile();
			for(int i = 1; i < data[0].length; i+=2) {
				//如科目存在則回傳該科目資料位置
				if(data[0][i].equals(subjectName)) return i;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//不存在則回傳-1
		return -1;
	}
	
	//判斷學生存不存在
	static int getStudent(String studentID) {
		try {
			String[][] data = readFile();
			for(int i = 0; i < data.length; i++) {
				//如科目存在則回傳該學生資料位置
				if(data[i][0].equals(studentID)) return i;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//不存在則回傳-1
		return -1;
	}
}
