import java.util.Scanner;

//��X�@�Ӧr�ꤤ(�Ѥj�g�^��r�զ�)�A�U�Ӧr�����U�إi��ƦC
//�@�ӥѤj�g�^��r�զ����r��(�r����׳̦h26�Ӧr��)
//��J�� #�A�h�����{��
public class A410877019_06_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String string = sc.nextLine();
			
			//�p�G��J���r�ꬰ#�h����
			if(string.equals("#")) break;
			
			//�N��J���r���ন�r���}�C�A�öi�J�Ƶ{��
			char[] str = string.toCharArray();
			arrange(str, 0);
			
			//��X����
			System.out.println();
		}
	}
	
	//�N�r����⤬���A�λ��j�A�p�G�P�_��̫�h��X�A�æ^��e�@�h
	public static void arrange(char[] str, int index) {
		//�p�G�n�P�_���r�����̫�@�ӡA�h��X�A�ê�^�e�@�h
		if(index == str.length - 1) {
			System.out.print(str);
			System.out.print(" ");
			return;
		}
		
		//�q�e���}�l�洫�A�@�}�li=index����ۤv��ۤv�洫
		//�P�_��̫�h��X�æ^��e�@�h�A�M��A�]�j���U�@�Ӧri+1�洫
		for(int i = index; i < str.length; i++) {
			changePosition(str, index, i);//�洫�}�C��i�Mindex����m
			arrange(str, index + 1);//�i�J�U�@�Ӧr
			changePosition(str, index, i);//�N��Ӧr����m���^�ӡA�קK���Ƕñ�
		}
	}
	
	//�N��Ӧr����m����
	public static void changePosition(char[] str, int index, int i) {
		char temp = str[index];
		str[index] = str[i];
		str[i] = temp;
	}
	
}
