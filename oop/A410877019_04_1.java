//uva105 - The Skyline Problem
import java.util.Scanner;

public class A410877019_04_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] record = new int[10001];
		int min = 10000;
		int max = 0;
		
		while(sc.hasNext()) {
			//��J�j�Ӱ_�lx�ȡB����y�ε���x��
			int leftX = sc.nextInt();
			int high = sc.nextInt();
			int rightX = sc.nextInt();
			
			//�p�G��J��0 0 0�h���X
			if(leftX == 0 && high == 0 && rightX == 0) break;
			
			//��ثe���j�Ӱ��װ���e�@�Ӭ������ȡA�h���N�쥻����
			for(int i = leftX; i < rightX; i++) {
				if(high > record[i]) record[i] = high;
			}
			
			//�����̥���M�̥k�䪺���
			if(leftX < min) min = leftX;
			if(rightX > max) max = rightX;
		}
		
		//�p�G�bx�W���j�Ӱ��שM�e�@��x�����פ��ۦP�A�h��X�ثe��x�My��m
		for(int i = min; i <= max; i++) {
				if(record[i] != record[i-1])
					System.out.printf(i + " " + record[i] + " ");
		}
	}
}
