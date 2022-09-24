//uva1644 - Prime Gap
import java.util.Scanner;

public class A410877019_13_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(sc.hasNext()) {
			//輸入要計算的數，如為0則結束
			int num = sc.nextInt();
			if(num == 0) break;
			
			//計算prime gap，並輸出
			int ans = findMax(num) - findMin(num);
			System.out.println(ans);
		}
	}

	//找出小於輸入的數的第一個質數，並return
	static int findMin(int num) {
		for (int i = num; i >= 2; i--){
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++){
                if (i % j == 0){
                    flag = false;
                }
            }
            if (flag) {
            	return i;
            }
        }
		return 0;
	}
	
	//找出大於輸入的數的第一個質數，並return
	static int findMax(int num) {
		final int MAX_PRIMENUM = 1299709;
		for (int i = num; i <= MAX_PRIMENUM; i++){
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++){
                if (i % j == 0){
                    flag = false;
                }
            }
            if (flag) {
            	return i;
            }
        }
		return 0;
	}
}
