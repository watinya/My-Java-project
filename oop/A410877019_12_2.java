//uva1368 - DNA Consensus String
import java.util.Scanner;

public class A410877019_12_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//輸入有幾筆測資
		int testCase = sc.nextInt();
		while(testCase-- > 0) {
			//輸入有幾組DNA和DNA的長度
			int num = sc.nextInt();
			int DNAlength = sc.nextInt();
			
			//輸入每筆DNA的組合並存入陣列
			char[][] DNA = new char[num][DNAlength];
			for(int i = 0; i < num; i++) {
				DNA[i] = sc.next().toCharArray();
			}
			
			char[] ACGT = {'A', 'C', 'G', 'T'};
			int hamming = 0;
			//從第一個字開始判斷每組第i個字ACGT出現的次數
			for(int i = 0; i < DNAlength; i++) {
				int[] count = new int[4];
				for(int j = 0; j < num; j++) {
					count[judge(DNA[j][i])]++;
				}
				
				//找出出現次數最大的字母
				int max = count[0];
				int position = 0;
				for(int m = 1; m < count.length; m++) {
					if(count[m] > max) {
						position = m;
						max = count[m];
					}	
				}
				
				//輸出出現次數最大的字母並計算漢明距離
				System.out.print(ACGT[position]);
				hamming += num-max;
			}
			
			//輸出漢明距離
			System.out.println("\n"+hamming);
		}
	}

	//判斷字母為ACGT哪一個並記錄
	static int judge(char str) {
		switch (str) {
			case 'A':
				return 0;
			case 'C':
				return 1;
			case 'G':
				return 2;
			default:
				return 3;
		}
	}
}
