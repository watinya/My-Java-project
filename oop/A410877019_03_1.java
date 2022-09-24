//uva118 - Mutant Flatworld Explorers
import java.util.Scanner;

public class A410877019_03_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sideX = sc.nextInt();//��Jx���
		int sideY = sc.nextInt();//��Jy���
		
		int[][] recordLost = new int[51][51];//�إ߰O�����X��ɪ��x�}
		for(int i = 0; i < 51; i++)//�N�x�}�����Ƴ]�w��0
			for(int j = 0; j < 51; j++)
				recordLost[i][j] = 0;
		
		while(sc.hasNext()) {
			int x = sc.nextInt();//��J��l��x��
			int y = sc.nextInt();//��J��l��y��
			if(x == 0 && y == 0) break;//���J0 0�ɵ����{��
			
			char direction = sc.next().charAt(0);//��J��l���V����V
			String instruction = sc.next();//��J���O
			
			for(int i = 0; i < instruction.length(); i ++) {//�@�Ӥ@�ӧP�_���O
				if(instruction.charAt(i) == 'F') {//�p���O��F
					int nowX = x, nowY = y;
					
					switch(direction) {//�ھڭ��V����m�M�w���ʪ���V
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
					
					if(nowX > sideX || nowX < 0 || nowY > sideY || nowY < 0) {//�p�G�W�L���
						if(recordLost[x][y] == 0) {//�P�_�W�L��ɫe����m���S���Q�O���L
							recordLost[x][y] = 1;//�����W�L��ɫe����m
							
							System.out.println(x + " " + y + " " + direction + " LOST");//��X
							break;
						}
					}
					else {//�S���W�L��ɫh�N���ʫ᪺���G�л\
						x = nowX;
						y = nowY;
					}
				}
				else {//�p���O��R��F
					direction = judgePosition(instruction.charAt(i), direction);//�i�J�Ƶ{���P�_���઺��V
				}
				
				if(i == instruction.length() - 1)//�P�_�O�_���̫�@�ӫ��O�A�p�G�O�h��X���G
					System.out.println(x + " " + y + " " + direction);
			}
		}
	}
	
	//�ھګ��OR��F�M��e���V����V�M�w�n��V���Ӥ��A�̫��^����᪺��V
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
