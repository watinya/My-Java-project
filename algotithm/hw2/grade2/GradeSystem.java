package grade2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class GradeSystem {
	private static HashMap<String, TreeMap<String, Integer>> studentGrade;
		
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
		try {
			studentGrade = File.readFile();
			if(studentGrade.get(StudentNo) == null) return -1;
			else if(studentGrade.get(StudentNo).get(subject) == null) return -1;
			else return studentGrade.get(StudentNo).get(subject);
			
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
		try {
			String grade = "";
			studentGrade = File.readFile();
			if(studentGrade.get(StudentNmb) == null) return null;
			
			for(String subject : studentGrade.get(StudentNmb).keySet()) {
				grade = grade.concat(subject + " " + studentGrade.get(StudentNmb).get(subject) + " ");
			}
			
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
		try {
			studentGrade = File.readFile();
			TreeMap<String, Integer> grade = new TreeMap<String, Integer>();
			String[] arr = src.split(" ");
			
			for(int i = 1; i < arr.length-1; i+=2) {
				int score = Integer.parseInt(arr[i+1]);
				grade.put(arr[i], score);
			}
			studentGrade.put(arr[0], grade);
			
			File.writeFile(studentGrade);
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
		try {
			studentGrade = File.readFile();
			studentGrade.remove(src);
			
			File.writeFile(studentGrade);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//取得要排序科目的成績資料
	static int[][] getSortData(String subjectName) {
		try {
			studentGrade = File.readFile();
			ArrayList<Object> temp = new ArrayList<Object>();
			
			//將資料存進temp中暫存
			for(String studentNo : studentGrade.keySet()) {
				if(searchGrade(studentNo, subjectName) != -1) {
					temp.add(studentNo);
					temp.add(studentGrade.get(studentNo).get(subjectName));
				}
			}			
			
			//將資料轉成int並存入陣列
			int[][] arr = new int[temp.size() / 2][2];
			int count = 0;
			for(int i = 0; i < arr.length; i++) {
				arr[i][0] = Integer.valueOf((String) temp.get(count));
				arr[i][1] = (int) temp.get(count+1);
				count+=2;
			}
			
			return arr;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
