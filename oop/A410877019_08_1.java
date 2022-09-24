//uva696 - How Many Knights
import java.util.Scanner;

public class A410877019_08_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			int row = sc.nextInt();
			int column = sc.nextInt();
			int knights = 0;
			
			//如果輸入為0 0則結束
			if(row == 0 && column == 0) break;
			
			//根據輸入的數用不同的計算方式
			if(row == 0 || column == 0) {
				knights = 0;
			}
			else if(row == 1 || column == 1) {
				knights = row * column;
			}
			else if(row == 2 || column == 2) {
				int maxNum = Math.max(row, column);
				knights = (int)(maxNum / 4) * 4 + count(maxNum % 4);
			}
			else {
				knights = (row * column + 1) / 2;
			}
			
			//輸出
			System.out.println(knights + " knights may be placed on a " + row + " row " + column + " column board.");
		}
	}
	
	//計算當行或列有一個等於2時，另一個數除4的餘數
	public static int count(int num) {
		if(num == 1)
			return 2;
		else if(num == 2 || num == 3)
			return 4;
		else
			return 0;
	}
}
