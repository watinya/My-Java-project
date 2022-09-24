//uva696 - How Many Knights
import java.util.Scanner;

public class A410877019_08_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			int row = sc.nextInt();
			int column = sc.nextInt();
			int knights = 0;
			
			//�p�G��J��0 0�h����
			if(row == 0 && column == 0) break;
			
			//�ھڿ�J���ƥΤ��P���p��覡
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
			
			//��X
			System.out.println(knights + " knights may be placed on a " + row + " row " + column + " column board.");
		}
	}
	
	//�p����ΦC���@�ӵ���2�ɡA�t�@�Ӽư�4���l��
	public static int count(int num) {
		if(num == 1)
			return 2;
		else if(num == 2 || num == 3)
			return 4;
		else
			return 0;
	}
}
