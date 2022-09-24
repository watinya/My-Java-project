//uva10813 - Traditional BINGO
import java.util.Scanner;

public class A410877019_10_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//輸入共有幾筆測資和自己的bingo卡數字
		int testCase = sc.nextInt();
		while(testCase-- > 0) {
			int[][] myNum = new int[5][5];
			for(int i = 0; i < myNum.length; i++) {
				for(int j = 0; j < myNum[i].length; j++) {
					if(i == 2 && j == 2) continue;
					myNum[i][j] = sc.nextInt();
				}
			}
			
			//依照抽出的號碼球順序輸入數字
			int[] bingoNum = new int[75];
			for(int i = 0; i < bingoNum.length; i++) {
				bingoNum[i] = sc.nextInt();
			}
			
			//依照號碼球順序，標記自己的bingo卡，並判斷有無連線
			boolean[][] check = new boolean[5][5];
			check[2][2] = true;
			for(int i = 0; i < bingoNum.length; i++) {
				markNumber(bingoNum[i], myNum, check);
				if(isBingo(check)) {
					System.out.printf("BINGO after %d numbers announced\n", i+1);
					break;
				}
			}
		}

	}
	
	//如自己的bingo卡上有抽出的數字，則標記true並跳出
	static void markNumber(int num, int[][] myNum, boolean[][] check) {
		loop:
		for(int i = 0; i < myNum.length; i++) {
			for(int j = 0; j < myNum[i].length; j++) {
				if(myNum[i][j] == num) {
					check[i][j] = true;
					break loop;
				}
			}
		}
	}

	//檢查直行、橫列、斜線有無連線，如果有回傳true，沒有則回傳false
	static boolean isBingo(boolean[][] check) {
		//檢查每一橫列有無連線
		for(int i = 0; i < check.length; i++) {
			boolean marked = true;
			for(int j = 0; j < check[i].length; j++) {
				if(check[i][j] != marked) {
					marked = false;
					break;
				}
			}
			
			if(marked) return true;
		}
		
		//檢查每一直行有無連線
		for(int j = 0; j < check.length; j++) {
			boolean marked = true;
			for(int i = 0; i < check[j].length; i++) {
				if(check[i][j] != marked) {
					marked = false;
					break;
				}
			}
			
			if(marked) return true;
		}
		
		//檢查左斜線有無連線
		boolean marked = true;
		for(int i = 0; i < check.length; i++) {
			if(check[i][i] != true) {
				marked = false;
				break;
			}
		}	
		if(marked) return true;
		
		//檢查右斜線有無連線
		marked = true;
		for(int i = 0; i < check.length; i++) {
			if(check[i][check.length-1-i] != true) {
				marked = false;
				break;
			}
		}	
		if(marked) return true;
		
		//如果都沒有則回傳false
		return false;
	}
	
}
