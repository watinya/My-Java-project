//uva11461 - Square Numbers
import java.util.Scanner;

public class A410877019_01_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int firstNum = sc.nextInt();
		int secondNum = sc.nextInt();
		while(firstNum != 0 && secondNum != 0) {//�P�_��J����ӼƬO�_��0
			
			double result1 = Math.sqrt(firstNum);//�N�Ĥ@�Ӽƶ}�ڸ�
			double result2 = Math.sqrt(secondNum);//�N�ĤG�Ӽƶ}�ڸ�
			
			if(result1 != (int)result1) result1 += 1;//�p�Ĥ@�ӼƤ�����������ګh�[�@
			
			System.out.println((int)result2 - (int)result1 + 1);//�p��d��ÿ�X���G
		}
		 
	}

}