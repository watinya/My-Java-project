//uva13190 - Rockabye Tobby
import java.util.Scanner;

public class A410877019_09_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//��J�@���X������
		int testCase = sc.nextInt();
		while(testCase-- > 0) {
			//��J�����Ī��ƶq�γ̤֦Y�Ħ���
			int givenNumber = sc.nextInt();
			int leastNumber = sc.nextInt();
			
			String[] medicineName = new String[givenNumber];
			int[] medicineFrequency = new int[givenNumber];
			int[] temp = new int[givenNumber];
			
			//��J�Ī��W�٤ΦY���W�v
			for(int i = 0; i < givenNumber; i++) {
				medicineName[i] = sc.next();
				medicineFrequency[i] = sc.nextInt();
				temp[i] = medicineFrequency[i];
			}
			
			//��X�ثe���ӦY�����ġA�ÿ�X�A�B�N�Y�����Ī��U���Y�Įɶ�����
			for(int count = 0; count < leastNumber; count++) {
				int min = findMin(medicineFrequency, givenNumber);
				System.out.println(medicineFrequency[min] + " " + medicineName[min]);
				medicineFrequency[min] += temp[min];
			}
		}
	}

	//��X�̤p���ɶ�
	public static int findMin(int[] array, int num) {
		int min = 0;
		for(int i = 1; i < num; i++) {
			if(array[i] < array[min])
				min = i;
		}
		return min;
	}
}
