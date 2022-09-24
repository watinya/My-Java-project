//uva10929 - You can say 11
import java.util.Scanner;

public class A410877019_ex2_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			String string = sc.nextLine();//块﹃
			
			if(string.equals("0")) break;//耞块琌0
			
			int odd  = 0, even = 0;
			for(int i = 0; i < string.length(); i++) {//璸衡计㎝案计计
				//耞i案盢材iじ锣Θ计
				if(i % 2 == 0) even += string.charAt(i) - 48;
				else odd += string.charAt(i) - 48;
			}
			
			switch(Math.abs(even - odd) % 11) {//耞计㎝案计畉琌11计
				case 0://緇计0琌11计
					System.out.println(string + " is a multiple of 11.");
					break;
				default://緇计ぃ0ぃ琌11计
					System.out.println(string + " is not a multiple of 11.");
					break;
			}
		}

	}

}
