import java.util.ArrayList;
import java.util.Scanner;

//��X�@�Ӧr�ꤤ(�Ѥj�g�^��r�զ�)�A�S�w���ת��U�إi��զX

/*�Ĥ@�C���@�ӥѤj�g�^��r�զ����r��A�r�ꪺ�r��������
 * ���]�Ӧr����׬��B 0<M<=26�A�ĤG�C���@���N(0<N<=M)
 */
public class MT_410877019_Q1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String string = sc.nextLine();
		String[] str = new String[string.length()];
		str = string.split("");
		
		int n = sc.nextInt();
		ArrayList<String> arr = new ArrayList<>();
		arrange(str, 0, n, arr);
	}
	
	public static void arrange(String[] str, int index, int n, ArrayList<String> arr) {
		if(arr.size() == n) {
			for(String i:arr) {
				System.out.print(i);
			}
			System.out.println();
			return;
		}
		
		for(int i = index; i < str.length; i++) {
			arr.add(str[i]);
			arrange(str, i+1, n, arr);
			arr.remove(str[i]);
		}
	}
}
