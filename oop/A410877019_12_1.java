//uva11917 - Do Your Own Homework
import java.util.Scanner;

public class A410877019_12_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int LATE = 5;
		
		//輸入有幾筆測資
		int testCase = sc.nextInt();
		for(int cases = 1; cases <= testCase; cases++) {
			//輸入此筆測資有幾個科目
			int subjectNum = sc.nextInt();
			
			//輸入科目名稱及完成所需天數
			String[] subject = new String[subjectNum];
			int[] requireDay = new int[subjectNum];
			for(int i = 0; i < subjectNum; i++) {
				subject[i] = sc.next();
				requireDay[i] = sc.nextInt();
			}

			//輸入截止日期及須繳交作業名稱
			int deadline = sc.nextInt();
			String homework = sc.next();
			
			//根據完成天數和繳交期限決定用哪個輸出
			int index = judge(subject, homework);
			if(index == -1 || requireDay[index] > deadline + LATE) {
				System.out.printf("Case %d: Do your own homework!\n", cases);
			}
			else if(requireDay[index] <= deadline) {
				System.out.printf("Case %d: Yesss\n", cases);
			}
			else if(requireDay[index] <= deadline + LATE){
				System.out.printf("Case %d: Late\n", cases);
			}
		}
	}
	
	//判斷需繳交的作業和科目有沒有相符的
	static int judge(String[] array, String homework) {
		for(int i = 0; i < array.length; i++) {
			if(homework.equals(array[i]))
				return i;
		}
		return -1;
	}

}
