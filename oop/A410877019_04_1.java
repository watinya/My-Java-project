//uva105 - The Skyline Problem
import java.util.Scanner;

public class A410877019_04_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] record = new int[10001];
		int min = 10000;
		int max = 0;
		
		while(sc.hasNext()) {
			//輸入大樓起始x值、高度y及結束x值
			int leftX = sc.nextInt();
			int high = sc.nextInt();
			int rightX = sc.nextInt();
			
			//如果輸入為0 0 0則跳出
			if(leftX == 0 && high == 0 && rightX == 0) break;
			
			//當目前的大樓高度高於前一個紀錄的值，則取代原本的值
			for(int i = leftX; i < rightX; i++) {
				if(high > record[i]) record[i] = high;
			}
			
			//紀錄最左邊和最右邊的邊界
			if(leftX < min) min = leftX;
			if(rightX > max) max = rightX;
		}
		
		//如果在x上的大樓高度和前一個x的高度不相同，則輸出目前的x和y位置
		for(int i = min; i <= max; i++) {
				if(record[i] != record[i-1])
					System.out.printf(i + " " + record[i] + " ");
		}
	}
}
