//uva11054 - Wine trading in Gergovia
import java.util.Scanner;

public class A410877019_09_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			//輸入有幾戶住家，如為0則結束
			int num = sc.nextInt();
			if(num == 0) break;
			
			//輸入每戶住家要賣出或買入的紅酒數，並存入陣列
			long[] bottle = new long[num];
			for(int i = 0; i < num; i ++) {
				bottle[i] = sc.nextLong();
			}
			
			//從第二戶開始，將前一戶的需求數量加進sum中，並記錄運輸的成本至result
			long result = 0;
			long sum = 0;
			for(int i = 1; i < num; i ++) {
				sum += bottle[i-1];
				result += Math.abs(sum);
			}
			
			//輸出結果
			System.out.println(result);
		}
	}

}
