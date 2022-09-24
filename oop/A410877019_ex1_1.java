//uva10783 - Odd Sum
import java.util.Scanner;

public class A410877019_ex1_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();//輸入共有幾筆測資
		for(int T = 1; T <= testCase; T++) {//第T筆測資
			int num1 = sc.nextInt();//輸入起始數字
			int num2 = sc.nextInt();//輸入終止數字
			
			int sum = 0;
			for(int nowNum = num1; nowNum <= num2; nowNum++) {//計算答案
				if(nowNum % 2 != 0) {//檢測是不是奇數
					sum += nowNum;//將目前的奇數加入總和
				}
			}
			
			System.out.println("Case " + T + ": " + sum);//輸出
		}

	}

}
