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
	 *  �j�M�ǥͪ���ئ��Z
	 * @param StudentNo �ǥ;Ǹ� 
	 * @param subject   ���
	 * @return �ǥͬ�ئ��Z
	 *  
	 *  Time estimate O(1) 
	 *  Example:
	 *  searchGrade("97501","DS");
	 *  �^�ǡG80
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
	 * �d�߬Y�ǥͩҦ����Z
	 * @param StudentNo �ǥ;Ǹ� 
	 * @return <String> �Ҧ���ؤΦ��Z
	 * 
	 *  Time estimate O(n) �䤤 n ���Y�ǥ��`�@�׽ҴX���Ҫ��Ӽ�
	 *  Example:
	 *  searchAllGrade("97501")
	 *  �^�� "DS 80 DM 76 LA 63"
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
			
			grade = grade.substring(0, grade.length()-1);//�R���̫�@�ӪŮ�
			return grade;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/** insertGrade
	 * �s�W�Y�ǥͦ��Z
	 * @param src �ϥΪ̿�J���Y�ǥ;Ǹ����Z��
	 * 
	 * 	Time estimate O(n) �䤤 n ���Y�ǥ��`�@�׽ҴX���Ҫ��Ӽ�
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
	 *  �R���Y�ǥ͸��
	 * @param src �Y�ǥ;Ǹ�
	 * @throws NoStudentException 
	 * 
	 *  Time estimate  O(1) 
	 *  Example:
	 *  deleteGrade ("97501")   �R�� 97501 �ǥͪ����Z
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
