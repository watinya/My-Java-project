//uva572 - Oil Deposits
import java.util.Scanner;

public class A410877019_05_1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			char[][] area = new char[100][100];
			
			//��J�o�Ъ����M�e
			int row = sc.nextInt();
			int column = sc.nextInt();
			if(row == 0) break;
			sc.nextLine();
			
			//��J�o�Ф�����m���ন�r���}�C
			for(int i = 0; i < row; i++) {
				String line = sc.nextLine();
				area[i] = line.toCharArray();
			}
			
			//�@�Ӥ@�Ӧr���P�_�A�p���o�Ыh�i�J�Ƶ{���P�_
			int sum = 0;
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < column; j++) {
					if(area[i][j] == '@') {
						judgeAround(i, j, area, row, column);
						sum++;
					}
				}
			}
			
			//��X
			System.out.println(sum);
		}
	}
	
	//�P�_�o�ЩP��O�_�٦��o�СA�p�G���h�~��`�J�P�_
	static void judgeAround(int i, int j, char[][] area, int row, int column) {
		if(i < 0 || i >= row || j < 0 || j >= column)//�p�W�X�d��h��^
			return;
		if(area[i][j] == '*')//�p�����o�Ыh��^
			return;
		area[i][j] = '*';//�N�w�g�P�_�L���o�Ш��N
		
		//�P�_�|�P�O���O�o�СA�O�h�`�J�P�_
		for(int m = -1; m <= 1; m++) {
			for(int n = -1; n <= 1; n++) {
				if(m != 0 || n != 0)
					judgeAround(i+m, j+n, area, row, column);
			}
		}
	}
}
