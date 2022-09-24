//uva11321 - Sort! Sort!! and Sort!!!
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class A410877019_10_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			//��J��������@���X�Ӵ��ռƦr�M���ơA�p��0 0�h����
			int testNum = sc.nextInt();
			int divisor = sc.nextInt();
			System.out.println(testNum + " " + divisor);
			if(testNum == 0 && divisor == 0) break;
			
			//�ھڦ��X�ؾl�ƫإߤ@�ӤG��ArrayList
			final int MAX_REMINDER = divisor - 1;
			ArrayList<ArrayList<Integer>> numList = new ArrayList<>();
	        for (int i = 0; i < MAX_REMINDER * 2 + 1; ++i) {
	            ArrayList<Integer> list = new ArrayList<>();
	            numList.add(list);
	        }
	        
	        //�̷Ӿl�ƪ����P�s�JnumList��
			int num, reminder;
			for(int i = 0; i < testNum; i++) {
				num = sc.nextInt();
				reminder = num % divisor;
				numList.get(reminder + MAX_REMINDER).add(num);
			}
			
			//�NnumList�����Ʀr�̷��D�حn�D�ƧǨæs�JsortedList��
			ArrayList<Integer> sortedList = new ArrayList<Integer>();
			for(int i = 0; i < numList.size(); i++) {
				sort(numList.get(i), sortedList);
			}
			
			//��X
			for(int i = 0; i < sortedList.size(); i++) {
				System.out.println(sortedList.get(i));
			}
		}
		
	}
	
	//�N�l�ƬۦP���Ʀr�̷��D�حn�D�ƧǡA�å[�isortedList��
	static void sort(ArrayList<Integer> numList, ArrayList<Integer> sortedList) {
		//���NnumList�����Ʀr�Ӥj�p�ƦC
		Collections.sort(numList);
		
		//�P�_�Ʀr���_�Ʃΰ���
		//�p�����ƫh�����[�b���ݡA�p���_�ƫh�[�b�e�@��sortedList������
		int len = sortedList.size();
		for(int i:numList) {
			if(i % 2 == 0) {
				sortedList.add(i);
			}
			else {
				sortedList.add(len, i);
			}
		}
	}
}
