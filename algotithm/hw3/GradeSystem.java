
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class GradeSystem {
	private static HashMap<String, TreeMap<String, Integer>> studentGrade;
		
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
	public static String searchAllGrade(String StudentNo) {
		try {
			String grade = "";
			studentGrade = File.readFile();
			if(studentGrade.get(StudentNo) == null) return null;
			
			for(String subject : studentGrade.get(StudentNo).keySet()) {
				grade = grade.concat(subject + " " + studentGrade.get(StudentNo).get(subject) + " ");
			}
			
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
	 *  �R���Y�ǥ͸��
	 * @param src �Y�ǥ;Ǹ�
	 * @throws NoStudentException 
	 * 
	 *  Time estimate  O(1) 
	 *  Example:
	 *  deleteGrade ("97501")   �R�� 97501 �ǥͪ����Z
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
	
	//���o�n�ƧǬ�ت��ǥ;Ǹ��P���Z���
	static int[][] getStudentGrade(String subjectName) {
		try {
			studentGrade = File.readFile();
			ArrayList<Object> temp = new ArrayList<Object>();
			
			//�N��Ʀs�itemp���Ȧs
			for(String studentNo : studentGrade.keySet()) {
				if(searchGrade(studentNo, subjectName) != -1) {
					temp.add(studentNo);
					temp.add(studentGrade.get(studentNo).get(subjectName));
				}
			}			
			
			//�N����নint�æs�J�}�C
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
	
	//�P�_�b�Ҧ��ǥͤ����S���Ӭ�ؤ����Z
	static boolean searchSubject(String subjectName) {
		try {
			studentGrade = File.readFile();
			for(String StudentNo : studentGrade.keySet()) {
				if(studentGrade.get(StudentNo).get(subjectName) != null) {
					return true;
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//���o�Y��ت��Ҧ��ǥͤ����Z
	static int[] getGrade(String subjectName) {
		int[][] arr = getStudentGrade(subjectName);
		int[] data = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			data[i] = arr[i][1];
		}
		return data;
	}
	
	//��X�Y��ئ��Z���̪����W�l�ǦC
	static int longestIncreasingSubsequence(int[] arr) {
		int[] lis = new int[arr.length];
		lis[0] = 1;
		
		for(int i = 1; i < arr.length; i++) {
			lis[i] = 1;
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && lis[j] >= lis[i]) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		Arrays.sort(lis);
		return lis[lis.length - 1];
	}
}
