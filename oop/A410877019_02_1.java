//uva10041 - Vito's Family
import java.util.Scanner;
import java.util.Arrays;

public class A410877019_02_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();//��J�@���X������
		while(testCase-- > 0) {
			int num = sc.nextInt();//��J�ӵ����꦳�X�Ӽ�
			
			int array[] = new int[num];//�إߤ@�Ӱ}�C
			for(int i = 0; i < num; i++) {//�]�j��N��J���Ʀr�s�J�}�C��
				array[i] = sc.nextInt();
			}
			
			Arrays.sort(array);//�N�}�C�����Ʀr�Ѥp��j�ƦC
			int sum = 0;
			for(int j = 0; j < num; j++) {//�N�}�C�����C�ӼƤ��O��h����ơA������Ȩìۥ[
				sum += Math.abs(array[num/2] - array[j]);
			}
			
			System.out.println(sum);//��X���G
		}
	}

}
