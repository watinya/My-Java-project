//uva10783 - Odd Sum
import java.util.Scanner;

public class A410877019_ex1_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();//��J�@���X������
		for(int T = 1; T <= testCase; T++) {//��T������
			int num1 = sc.nextInt();//��J�_�l�Ʀr
			int num2 = sc.nextInt();//��J�פ�Ʀr
			
			int sum = 0;
			for(int nowNum = num1; nowNum <= num2; nowNum++) {//�p�⵪��
				if(nowNum % 2 != 0) {//�˴��O���O�_��
					sum += nowNum;//�N�ثe���_�ƥ[�J�`�M
				}
			}
			
			System.out.println("Case " + T + ": " + sum);//��X
		}

	}

}
