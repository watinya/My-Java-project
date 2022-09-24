//uva11321 - Sort! Sort!! and Sort!!!
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class A410877019_10_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			//輸入此筆測資共有幾個測試數字和除數，如為0 0則結束
			int testNum = sc.nextInt();
			int divisor = sc.nextInt();
			System.out.println(testNum + " " + divisor);
			if(testNum == 0 && divisor == 0) break;
			
			//根據有幾種餘數建立一個二維ArrayList
			final int MAX_REMINDER = divisor - 1;
			ArrayList<ArrayList<Integer>> numList = new ArrayList<>();
	        for (int i = 0; i < MAX_REMINDER * 2 + 1; ++i) {
	            ArrayList<Integer> list = new ArrayList<>();
	            numList.add(list);
	        }
	        
	        //依照餘數的不同存入numList中
			int num, reminder;
			for(int i = 0; i < testNum; i++) {
				num = sc.nextInt();
				reminder = num % divisor;
				numList.get(reminder + MAX_REMINDER).add(num);
			}
			
			//將numList中的數字依照題目要求排序並存入sortedList中
			ArrayList<Integer> sortedList = new ArrayList<Integer>();
			for(int i = 0; i < numList.size(); i++) {
				sort(numList.get(i), sortedList);
			}
			
			//輸出
			for(int i = 0; i < sortedList.size(); i++) {
				System.out.println(sortedList.get(i));
			}
		}
		
	}
	
	//將餘數相同的數字依照題目要求排序，並加進sortedList中
	static void sort(ArrayList<Integer> numList, ArrayList<Integer> sortedList) {
		//先將numList中的數字照大小排列
		Collections.sort(numList);
		
		//判斷數字為奇數或偶數
		//如為偶數則直接加在尾端，如為奇數則加在前一個sortedList的尾端
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
