//uva11461 - Square Numbers
import java.util.Scanner;

public class A410877019_01_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int firstNum = sc.nextInt();
		int secondNum = sc.nextInt();
		while(firstNum != 0 && secondNum != 0) {//判斷輸入的兩個數是否為0
			
			double result1 = Math.sqrt(firstNum);//將第一個數開根號
			double result2 = Math.sqrt(secondNum);//將第二個數開根號
			
			if(result1 != (int)result1) result1 += 1;//如第一個數不為完全平方根則加一
			
			System.out.println((int)result2 - (int)result1 + 1);//計算範圍並輸出結果
		}
		 
	}

}