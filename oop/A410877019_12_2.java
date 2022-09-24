//uva1368 - DNA Consensus String
import java.util.Scanner;

public class A410877019_12_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//��J���X������
		int testCase = sc.nextInt();
		while(testCase-- > 0) {
			//��J���X��DNA�MDNA������
			int num = sc.nextInt();
			int DNAlength = sc.nextInt();
			
			//��J�C��DNA���զX�æs�J�}�C
			char[][] DNA = new char[num][DNAlength];
			for(int i = 0; i < num; i++) {
				DNA[i] = sc.next().toCharArray();
			}
			
			char[] ACGT = {'A', 'C', 'G', 'T'};
			int hamming = 0;
			//�q�Ĥ@�Ӧr�}�l�P�_�C�ղ�i�ӦrACGT�X�{������
			for(int i = 0; i < DNAlength; i++) {
				int[] count = new int[4];
				for(int j = 0; j < num; j++) {
					count[judge(DNA[j][i])]++;
				}
				
				//��X�X�{���Ƴ̤j���r��
				int max = count[0];
				int position = 0;
				for(int m = 1; m < count.length; m++) {
					if(count[m] > max) {
						position = m;
						max = count[m];
					}	
				}
				
				//��X�X�{���Ƴ̤j���r���íp��~���Z��
				System.out.print(ACGT[position]);
				hamming += num-max;
			}
			
			//��X�~���Z��
			System.out.println("\n"+hamming);
		}
	}

	//�P�_�r����ACGT���@�ӨðO��
	static int judge(char str) {
		switch (str) {
			case 'A':
				return 0;
			case 'C':
				return 1;
			case 'G':
				return 2;
			default:
				return 3;
		}
	}
}
