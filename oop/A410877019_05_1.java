//uva572 - Oil Deposits
import java.util.Scanner;

public class A410877019_05_1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			char[][] area = new char[100][100];
			
			//輸入油田的長和寬
			int row = sc.nextInt();
			int column = sc.nextInt();
			if(row == 0) break;
			sc.nextLine();
			
			//輸入油田分布位置並轉成字元陣列
			for(int i = 0; i < row; i++) {
				String line = sc.nextLine();
				area[i] = line.toCharArray();
			}
			
			//一個一個字元判斷，如為油田則進入副程式判斷
			int sum = 0;
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < column; j++) {
					if(area[i][j] == '@') {
						judgeAround(i, j, area, row, column);
						sum++;
					}
				}
			}
			
			//輸出
			System.out.println(sum);
		}
	}
	
	//判斷油田周圍是否還有油田，如果有則繼續深入判斷
	static void judgeAround(int i, int j, char[][] area, int row, int column) {
		if(i < 0 || i >= row || j < 0 || j >= column)//如超出範圍則返回
			return;
		if(area[i][j] == '*')//如不為油田則返回
			return;
		area[i][j] = '*';//將已經判斷過的油田取代
		
		//判斷四周是不是油田，是則深入判斷
		for(int m = -1; m <= 1; m++) {
			for(int n = -1; n <= 1; n++) {
				if(m != 0 || n != 0)
					judgeAround(i+m, j+n, area, row, column);
			}
		}
	}
}
