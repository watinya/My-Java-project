//uva13190 - Rockabye Tobby
import java.util.Scanner;

public class A410877019_09_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//輸入共有幾筆測資
		int testCase = sc.nextInt();
		while(testCase-- > 0) {
			//輸入給予藥的數量及最少吃藥次數
			int givenNumber = sc.nextInt();
			int leastNumber = sc.nextInt();
			
			String[] medicineName = new String[givenNumber];
			int[] medicineFrequency = new int[givenNumber];
			int[] temp = new int[givenNumber];
			
			//輸入藥的名稱及吃藥頻率
			for(int i = 0; i < givenNumber; i++) {
				medicineName[i] = sc.next();
				medicineFrequency[i] = sc.nextInt();
				temp[i] = medicineFrequency[i];
			}
			
			//找出目前應該吃哪種藥，並輸出，且將吃完的藥的下次吃藥時間紀錄
			for(int count = 0; count < leastNumber; count++) {
				int min = findMin(medicineFrequency, givenNumber);
				System.out.println(medicineFrequency[min] + " " + medicineName[min]);
				medicineFrequency[min] += temp[min];
			}
		}
	}

	//找出最小的時間
	public static int findMin(int[] array, int num) {
		int min = 0;
		for(int i = 1; i < num; i++) {
			if(array[i] < array[min])
				min = i;
		}
		return min;
	}
}
