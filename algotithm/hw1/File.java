import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.TreeMap;

public class File {
	private static String dataLocation = "data\\grade1.txt";//��Ƹ��|	
	
	//Ū�ɱN�Ǹ��Φ��Z��Ʀs�JHashMap
	static HashMap<String, TreeMap<String, Integer>> readFile() throws IOException {
		String line;
		HashMap<String, TreeMap<String, Integer>> studentGrade = new HashMap<String, TreeMap<String, Integer>>();   //�H�Ǹ��� key, ��ئ��Z��@ value
		
		//Ū��
		InputStreamReader read = new InputStreamReader(new FileInputStream(dataLocation), "utf-8");
		BufferedReader reader = new BufferedReader(read);
		while((line = reader.readLine()) != null) {
			TreeMap<String, Integer> grade = new TreeMap<String, Integer>();
			String[] arr = line.split(" ");
			for(int i = 1; i < arr.length-1; i+=2) {
				int score = Integer.parseInt(arr[i+1]);
				grade.put(arr[i], score);
			}
			studentGrade.put(arr[0], grade);
		}
		reader.close();
		return studentGrade;
	}
	
	//�g��
	static void writeFile(HashMap<String, TreeMap<String, Integer>> data) throws IOException {
		String writeText = "";
		for(String studentNo : data.keySet()) {
			writeText = writeText.concat(studentNo + " ");
			
			for(String subject : data.get(studentNo).keySet()) {
				writeText = writeText.concat(subject + " " + data.get(studentNo).get(subject) + " ");
			}
			writeText = writeText.substring(0, writeText.length()-1);
			writeText = writeText.concat("\n");
		}
		
		FileOutputStream writerStream = new FileOutputStream(dataLocation);    
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
		writer.write(writeText);
		writer.close(); 
    }
}
