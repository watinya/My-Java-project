import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class GradeSystem {
	
	public static void main(String[] args) throws IOException {
		HashMap<String, TreeMap<String, Integer>> g = File.readFile();
		insertGrade("97501 DS 92 DM 67 LA 56");
		System.out.println("success!");
	}
		
	public GradeSystem(){}
	
	/** SearchGrade
	 *  搜尋學生的科目成績
	 * @param StudentNo 學生學號 
	 * @param subject   科目
	 * @return 學生科目成績
	 *  
	 *  Time estimate O(1) 
	 *  Example:
	 *  searchGrade("97501","DS");
	 *  回傳：80
	 * @throws NoSubjectException 
	 */
	public static int searchGrade(String StudentNo, String subject) {
		HashMap<String, TreeMap<String, Integer>> data;
		try {
			int score = 0;
			data = File.readFile();
			score = data.get(StudentNo).get(subject);
			
			return score;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	/** searchAllGrade
	 * 查詢某學生所有成績
	 * @param StudentNo 學生學號 
	 * @return <String> 所有科目及成績
	 * 
	 *  Time estimate O(n) 其中 n 為某學生總共修課幾門課的個數
	 *  Example:
	 *  searchAllGrade("97501")
	 *  回傳 "DS 80 DM 76 LA 63"
	 * @throws NoStudentException 
	 */
	public static String searchAllGrade(String StudentNmb) {
		HashMap<String, TreeMap<String, Integer>> data;
		try {
			String grade = "";
			data = File.readFile();
			for(String subject : data.get(StudentNmb).keySet()) {
				grade = grade.concat(subject + " " + data.get(StudentNmb).get(subject) + " ");
			}
			
			grade = grade.substring(0, grade.length()-1);//刪除最後一個空格
			return grade;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/** insertGrade
	 * 新增某學生成績
	 * @param src 使用者輸入的某學生學號成績等
	 * 
	 * 	Time estimate O(n) 其中 n 為某學生總共修課幾門課的個數
	 * 	Example:
	 * 	insertGrade("97501 DS 92 DM 67 LA 56")
	 */
	public static void insertGrade(String src){
		HashMap<String, TreeMap<String, Integer>> data;
		try {
			data = File.readFile();
			TreeMap<String, Integer> grade = new TreeMap<String, Integer>();
			String[] arr = src.split(" ");
			for(int i = 1; i < arr.length-1; i+=2) {
				int score = Integer.parseInt(arr[i+1]);
				grade.put(arr[i], score);
			}
			data.put(arr[0], grade);
			
			File.writeFile(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
     /** deleteGrade
	 *  刪除某學生資料
	 * @param src 某學生學號
	 * @throws NoStudentException 
	 * 
	 *  Time estimate  O(1) 
	 *  Example:
	 *  deleteGrade ("97501")   刪除 97501 學生的成績
	 */
	public static void deleteGrade(String src) {
		HashMap<String, TreeMap<String, Integer>> data;
		try {
			data = File.readFile();
			data.remove(src);
			
			File.writeFile(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//
	static int[][] getSortData(String subjectName) {
		HashMap<String, TreeMap<String, Integer>> data;
		try {
			data = File.readFile();
			ArrayList<Object> temp = new ArrayList<Object>();
			
			for(String studentNo : data.keySet()) {
				if(searchGrade(studentNo, subjectName) != -1) {
					temp.add(studentNo);
					temp.add(data.get(studentNo).get(subjectName));
				}
			}
			
			int[][] arr = new int[temp.size()/2][2];
			for(int i = 0; i < arr.length; i+=2) {
				arr[i][0] = (int) temp.get(i);
				arr[i][1] = (int) temp.get(i+1);
			}
			return arr;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
