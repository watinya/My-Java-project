//uva11917 - Do Your Own Homework
import java.util.Scanner;

public class A410877019_12_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int LATE = 5;
		
		//��J���X������
		int testCase = sc.nextInt();
		for(int cases = 1; cases <= testCase; cases++) {
			//��J�������꦳�X�Ӭ��
			int subjectNum = sc.nextInt();
			
			//��J��ئW�٤Χ����һݤѼ�
			String[] subject = new String[subjectNum];
			int[] requireDay = new int[subjectNum];
			for(int i = 0; i < subjectNum; i++) {
				subject[i] = sc.next();
				requireDay[i] = sc.nextInt();
			}

			//��J�I�����ζ�ú��@�~�W��
			int deadline = sc.nextInt();
			String homework = sc.next();
			
			//�ھڧ����ѼƩMú������M�w�έ��ӿ�X
			int index = judge(subject, homework);
			if(index == -1 || requireDay[index] > deadline + LATE) {
				System.out.printf("Case %d: Do your own homework!\n", cases);
			}
			else if(requireDay[index] <= deadline) {
				System.out.printf("Case %d: Yesss\n", cases);
			}
			else if(requireDay[index] <= deadline + LATE){
				System.out.printf("Case %d: Late\n", cases);
			}
		}
	}
	
	//�P�_��ú�檺�@�~�M��ئ��S���۲Ū�
	static int judge(String[] array, String homework) {
		for(int i = 0; i < array.length; i++) {
			if(homework.equals(array[i]))
				return i;
		}
		return -1;
	}

}
