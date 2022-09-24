import java.util.Scanner;

//找出一個字串中(由大寫英文字組成)，各個字元的各種可能排列
//一個由大寫英文字組成的字串(字串長度最多26個字元)
//輸入為 #，則結束程式
public class A410877019_06_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String string = sc.nextLine();
			
			//如果輸入的字串為#則結束
			if(string.equals("#")) break;
			
			//將輸入的字串轉成字元陣列，並進入副程式
			char[] str = string.toCharArray();
			arrange(str, 0);
			
			//輸出換行
			System.out.println();
		}
	}
	
	//將字元兩兩互換，用遞迴，如果判斷到最後則輸出，並回到前一層
	public static void arrange(char[] str, int index) {
		//如果要判斷的字元為最後一個，則輸出，並返回前一層
		if(index == str.length - 1) {
			System.out.print(str);
			System.out.print(" ");
			return;
		}
		
		//從前面開始交換，一開始i=index等於自己跟自己交換
		//判斷到最後則輸出並回到前一層，然後再跑迴圈跟下一個字i+1交換
		for(int i = index; i < str.length; i++) {
			changePosition(str, index, i);//交換陣列中i和index的位置
			arrange(str, index + 1);//進入下一個字
			changePosition(str, index, i);//將兩個字的位置換回來，避免順序亂掉
		}
	}
	
	//將兩個字的位置互換
	public static void changePosition(char[] str, int index, int i) {
		char temp = str[index];
		str[index] = str[i];
		str[i] = temp;
	}
	
}
