//uva10056 - What is the Probability
import java.util.Scanner;

public class A410877019_07_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		
		while(testCase-- > 0) {
			int player = sc.nextInt();//�@���h�֪��a
			double win = sc.nextDouble();//���\���v
			int number = sc.nextInt();//���\�����a�s��
			double lose = 1 - win;
			
			//���O�p��������\�����v�M����U���\���i���
			double probability = (Math.pow(lose, number-1)) * win;
			double chanceOfWinning = (1 - Math.pow(lose, player));
			
			//�P�_���\���v�O�_��0�A�ھڱ��p�Q�μƾǤ����p����X
			if(win == 0)
				System.out.println("0.0000");
			else
				System.out.printf("%.4f\n", probability /chanceOfWinning);
		}
	}

}
