import java.util.ArrayList;
import java.util.Scanner;

//找出一個字串中(由大寫英文字組成)，特定長度的各種可能組合

/*第一列為一個由大寫英文字組成的字串，字串的字元不重複
 * 假設該字串長度為且 0<M<=26，第二列為一整數N(0<N<=M)
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
