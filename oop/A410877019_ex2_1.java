//uva10929 - You can say 11
import java.util.Scanner;

public class A410877019_ex2_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String string = sc.nextLine();//��J�r��
			
			if(string.equals("0")) break;//�P�_��J�O�_��0
			
			int odd  = 0, even = 0;
			for(int i = 0; i < string.length(); i++) {//�p��_�Ʀ�M���Ʀ�Ʀr���X
				//�P�_i���_���A�N��i�Ӧr���ন�Ʀr
				if(i % 2 == 0) even += string.charAt(i) - 48;
				else odd += string.charAt(i) - 48;
			}
			
			switch(Math.abs(even - odd) % 11) {//�P�_�_�Ʀ�X�M���Ʀ�X���t�O�_��11������
				case 0://�l�Ƭ�0�A�O11������
					System.out.println(string + " is a multiple of 11.");
					break;
				default://�l�Ƥ���0�A���O11������
					System.out.println(string + " is not a multiple of 11.");
					break;
			}
		}

	}

}
