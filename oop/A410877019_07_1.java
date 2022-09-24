//uva10056 - What is the Probability
import java.util.Scanner;

public class A410877019_07_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		
		while(testCase-- > 0) {
			int player = sc.nextInt();//共有多少玩家
			double win = sc.nextDouble();//成功機率
			int number = sc.nextInt();//成功的玩家編號
			double lose = 1 - win;
			
			//分別計算全部成功的機率和條件下成功的可能性
			double probability = (Math.pow(lose, number-1)) * win;
			double chanceOfWinning = (1 - Math.pow(lose, player));
			
			//判斷成功機率是否為0，根據情況利用數學公式計算後輸出
			if(win == 0)
				System.out.println("0.0000");
			else
				System.out.printf("%.4f\n", probability /chanceOfWinning);
		}
	}

}
