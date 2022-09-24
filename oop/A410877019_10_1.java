//uva10813 - Traditional BINGO
import java.util.Scanner;

public class A410877019_10_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//��J�@���X������M�ۤv��bingo�d�Ʀr
		int testCase = sc.nextInt();
		while(testCase-- > 0) {
			int[][] myNum = new int[5][5];
			for(int i = 0; i < myNum.length; i++) {
				for(int j = 0; j < myNum[i].length; j++) {
					if(i == 2 && j == 2) continue;
					myNum[i][j] = sc.nextInt();
				}
			}
			
			//�̷ө�X�����X�y���ǿ�J�Ʀr
			int[] bingoNum = new int[75];
			for(int i = 0; i < bingoNum.length; i++) {
				bingoNum[i] = sc.nextInt();
			}
			
			//�̷Ӹ��X�y���ǡA�аO�ۤv��bingo�d�A�çP�_���L�s�u
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
	
	//�p�ۤv��bingo�d�W����X���Ʀr�A�h�аOtrue�ø��X
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

	//�ˬd����B��C�B�׽u���L�s�u�A�p�G���^��true�A�S���h�^��false
	static boolean isBingo(boolean[][] check) {
		//�ˬd�C�@��C���L�s�u
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
		
		//�ˬd�C�@���榳�L�s�u
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
		
		//�ˬd���׽u���L�s�u
		boolean marked = true;
		for(int i = 0; i < check.length; i++) {
			if(check[i][i] != true) {
				marked = false;
				break;
			}
		}	
		if(marked) return true;
		
		//�ˬd�k�׽u���L�s�u
		marked = true;
		for(int i = 0; i < check.length; i++) {
			if(check[i][check.length-1-i] != true) {
				marked = false;
				break;
			}
		}	
		if(marked) return true;
		
		//�p�G���S���h�^��false
		return false;
	}
	
}
