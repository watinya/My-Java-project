//uva118 - Mutant Flatworld Explorers
import java.util.Scanner;

public class A410877019_03_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sideX = sc.nextInt();//輸入x邊界
		int sideY = sc.nextInt();//輸入y邊界
		
		int[][] recordLost = new int[51][51];//建立記錄掉出邊界的矩陣
		for(int i = 0; i < 51; i++)//將矩陣內的數設定為0
			for(int j = 0; j < 51; j++)
				recordLost[i][j] = 0;
		
		while(sc.hasNext()) {
			int x = sc.nextInt();//輸入初始的x值
			int y = sc.nextInt();//輸入初始的y值
			if(x == 0 && y == 0) break;//當輸入0 0時結束程式
			
			char direction = sc.next().charAt(0);//輸入初始面向的方向
			String instruction = sc.next();//輸入指令
			
			for(int i = 0; i < instruction.length(); i ++) {//一個一個判斷指令
				if(instruction.charAt(i) == 'F') {//如指令為F
					int nowX = x, nowY = y;
					
					switch(direction) {//根據面向的位置決定移動的方向
						case 'N':
							nowY++;
							break;
						case 'W':
							nowX--;
							break;
						case 'S':
							nowY--;
							break;
						case 'E':
							nowX++;
							break;
					}
					
					if(nowX > sideX || nowX < 0 || nowY > sideY || nowY < 0) {//如果超過邊界
						if(recordLost[x][y] == 0) {//判斷超過邊界前的位置有沒有被記錄過
							recordLost[x][y] = 1;//紀錄超過邊界前的位置
							
							System.out.println(x + " " + y + " " + direction + " LOST");//輸出
							break;
						}
					}
					else {//沒有超過邊界則將移動後的結果覆蓋
						x = nowX;
						y = nowY;
					}
				}
				else {//如指令為R或F
					direction = judgePosition(instruction.charAt(i), direction);//進入副程式判斷旋轉的方向
				}
				
				if(i == instruction.length() - 1)//判斷是否為最後一個指令，如果是則輸出結果
					System.out.println(x + " " + y + " " + direction);
			}
		}
	}
	
	//根據指令R或F和當前面向的方向決定要轉向哪個方位，最後返回旋轉後的方向
	public static char judgePosition(char cmd, char position) {
		switch(cmd) {
			case 'R':
				switch(position) {
					case 'N':
						position = 'E';
						break;
					case 'W':
						position = 'N';
						break;
					case 'S':
						position = 'W';
						break;
					case 'E':
						position = 'S';
						break;
				}
				break;
				
			case 'L':
				switch(position) {
					case 'N':
						position = 'W';
						break;
					case 'W':
						position = 'S';
						break;
					case 'S':
						position = 'E';
						break;
					case 'E':
						position = 'N';
						break;
				}
				break;
		}
		return position;
	}

}
