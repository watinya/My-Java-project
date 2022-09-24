//uva11054 - Wine trading in Gergovia
import java.util.Scanner;

public class A410877019_09_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			//��J���X���a�A�p��0�h����
			int num = sc.nextInt();
			if(num == 0) break;
			
			//��J�C���a�n��X�ζR�J�����s�ơA�æs�J�}�C
			long[] bottle = new long[num];
			for(int i = 0; i < num; i ++) {
				bottle[i] = sc.nextLong();
			}
			
			//�q�ĤG��}�l�A�N�e�@�᪺�ݨD�ƶq�[�isum���A�ðO���B�骺������result
			long result = 0;
			long sum = 0;
			for(int i = 1; i < num; i ++) {
				sum += bottle[i-1];
				result += Math.abs(sum);
			}
			
			//��X���G
			System.out.println(result);
		}
	}

}
