//uva10415 - Eb Alto Saxophone Player
import java.util.Scanner;

public class MT_410877019_Q2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt();
		String eat = sc.nextLine();
		while(testCase-- > 0){
			String s = sc.nextLine();
			String[] ss = s.split("");
			
			int[] num = new int[10];
			int[] Cnum = new int[10];
			int[] c = {0, 1, 1, 1, 0, 0, 1, 1, 1, 1};
			int[] d = {0, 1, 1, 1, 0, 0, 1, 1, 1, 0};
			int[] e = {0, 1, 1, 1, 0, 0, 1, 1, 0, 0};
			int[] f = {0, 1, 1, 1, 0, 0, 1, 0, 0, 0};
			int[] g = {0, 1, 1, 1, 0, 0, 0, 0, 0, 0};
			int[] a = {0, 1, 1, 0, 0, 0, 0, 0, 0, 0};
			int[] b = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] C = {0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
			int[] D = {1, 1, 1, 1, 0, 0, 1, 1, 1, 0};
			int[] E = {1, 1, 1, 1, 0, 0, 1, 1, 0, 0};
			int[] F = {1, 1, 1, 1, 0, 0, 1, 0, 0, 0};
			int[] G = {1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
			int[] A = {1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
			int[] B = {1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
			
			int[] ans = new int[10];
			
			for(int i = 0; i < ss.length; i++){
				if(ss[i].equals("c"))Cnum = c;
				else if(ss[i].equals("d"))Cnum = d;
				else if(ss[i].equals("e"))Cnum = e;
				else if(ss[i].equals("f"))Cnum = f;
				else if(ss[i].equals("g"))Cnum = g;
				else if(ss[i].equals("a"))Cnum = a;
				else if(ss[i].equals("b"))Cnum = b;
				else if(ss[i].equals("C"))Cnum = C;
				else if(ss[i].equals("D"))Cnum = D;
				else if(ss[i].equals("E"))Cnum = E;
				else if(ss[i].equals("F"))Cnum = F;
				else if(ss[i].equals("G"))Cnum = G;
				else if(ss[i].equals("A"))Cnum = A;
				else if(ss[i].equals("B"))Cnum = B;
			
				for(int j = 0; j < 10; j++){
					if(num[j] != Cnum[j] && Cnum[j] == 1){
						ans[j]++;
					}
				}
				num = Cnum;
			}
			
			for(int i:ans){
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

}
