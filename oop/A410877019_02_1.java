//uva10041 - Vito's Family
import java.util.Scanner;
import java.util.Arrays;

public class A410877019_02_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();//輸入共有幾筆測資
		while(testCase-- > 0) {
			int num = sc.nextInt();//輸入該筆測資有幾個數
			
			int array[] = new int[num];//建立一個陣列
			for(int i = 0; i < num; i++) {//跑迴圈將輸入的數字存入陣列中
				array[i] = sc.nextInt();
			}
			
			Arrays.sort(array);//將陣列中的數字由小到大排列
			int sum = 0;
			for(int j = 0; j < num; j++) {//將陣列中的每個數分別減去中位數，取絕對值並相加
				sum += Math.abs(array[num/2] - array[j]);
			}
			
			System.out.println(sum);//輸出結果
		}
	}

}
