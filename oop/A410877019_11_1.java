//uva706 - LC-Display
import java.util.ArrayList;
import java.util.Scanner;

public class A410877019_11_1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//�إߤ@�ӼƦr�Ϊ����}�C
		int [][] numberShape = {{1, 3, 0, 3, 1}, {0, 2, 0, 2, 0},
								{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1},
								{0, 3, 1, 2, 0}, {1, 1, 1, 2, 1},
								{1, 1, 1, 3, 1}, {1, 2, 0, 2, 0},
								{1, 3, 1, 3, 1}, {1, 3, 1, 2, 1}};
		
		while(sc.hasNext()) {
			//��J�Ʀr���j�p�M�n��X���Ʀr�A�p��0 0�h����
			int size = sc.nextInt();
			String num = sc.next();
			if(size == 0 && num.equals("0")) break;
			String[] str = num.split("");
			
			//�q�W��U�P�_�ÿ�X
			for(int i = 0; i < 5; i++) {
				ArrayList<String> temp = new ArrayList<String>();
				
				//�p����u�h�i�Jhorizontal�Ƶ{���ÿ�X
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
				
				//�p����u�h�i�Jstraight�Ƶ{���îھ�size�j�p��X
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
			//�C������̫ᴫ��
			System.out.println();
		}
	}

	//�Q���ƪ��r���M����
	static String repeat(String str, int n) {
		String temp = new String(new char[n]).replace("\0", str);
	    return temp;
	}
	
	//�ھڿ�J���Ʀr�N��ӧΪ�����u�Ϯצs�JArrayList
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
	
	//�ھڿ�J���Ʀr�N��ӧΪ������u�Ϯצs�JArrayList
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
