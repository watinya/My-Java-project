//uva706 - LC-Display
import java.util.ArrayList;
import java.util.Scanner;

public class A410877019_11_1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//建立一個數字形狀的陣列
		int [][] numberShape = {{1, 3, 0, 3, 1}, {0, 2, 0, 2, 0},
								{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1},
								{0, 3, 1, 2, 0}, {1, 1, 1, 2, 1},
								{1, 1, 1, 3, 1}, {1, 2, 0, 2, 0},
								{1, 3, 1, 3, 1}, {1, 3, 1, 2, 1}};
		
		while(sc.hasNext()) {
			//輸入數字的大小和要輸出的數字，如為0 0則結束
			int size = sc.nextInt();
			String num = sc.next();
			if(size == 0 && num.equals("0")) break;
			String[] str = num.split("");
			
			//從上到下判斷並輸出
			for(int i = 0; i < 5; i++) {
				ArrayList<String> temp = new ArrayList<String>();
				
				//如為橫線則進入horizontal副程式並輸出
				if(i == 0 || i == 2 || i == 4) {
					for(int j = 0; j < str.length; j++) {
						int n = Integer.parseInt(str[j]);
						horizontal(numberShape[n][i], size, temp);
					}
					for(int m = 0; m < temp.size(); m++) {
						System.out.print(temp.get(m));
						if(m != temp.size()-1) System.out.print(" ");
					}
					System.out.println();
				}
				
				//如為橫線則進入straight副程式並根據size大小輸出
				else {
					for(int j = 0; j < str.length; j++) {
						int n = Integer.parseInt(str[j]);
						straight(numberShape[n][i], size, temp);
					}
					for(int a = 0; a < size; a++) {
						for(int m = 0; m < temp.size(); m++) {
							System.out.print(temp.get(m));
							if(m != temp.size()-1) System.out.print(" ");
						}
						System.out.println();
					}
				}
				
			}
			//每筆測資最後換行
			System.out.println();
		}
	}

	//想重複的字元和長度
	static String repeat(String str, int n) {
		String temp = new String(new char[n]).replace("\0", str);
	    return temp;
	}
	
	//根據輸入的數字將對照形狀的橫線圖案存入ArrayList
	static void horizontal(int i, int size, ArrayList<String> arr) {
		String repeated;
		switch(i) {
			case 0:
				repeated = repeat(" ", size+2);
				arr.add(repeated);
				break;
			case 1:
				repeated = repeat("-", size);
				arr.add(" " + repeated + " ");
				break;
		}	
	}
	
	//根據輸入的數字將對照形狀的直線圖案存入ArrayList
	static void straight(int i, int size, ArrayList<String> arr) {
		String repeated;
		switch(i) {
			case 1:
				repeated = repeat(" ", size+1);
				arr.add("|" + repeated);
				break;
			case 2:
				repeated = repeat(" ", size+1);
				arr.add(repeated + "|");
				break;
			case 3:
				repeated = repeat(" ", size);
				arr.add("|" + repeated + "|");
				break;
		}
	}
}
