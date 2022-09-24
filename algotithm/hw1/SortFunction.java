
public class SortFunction {
	
	//進行insertion sort
	static void insertionSort(int[][] data, int[] sortInfo) {
		int score = 1;
		int compare = sortInfo[0];
		int change = sortInfo[1];
		
		//跟前面的數比較，把較小的數往前，並計算比較和交換的次數
		for(int i = 1; i < data.length; i++) {
			int[] key = data[i];
			int j = i-1;
			while(j >= 0 && data[j][score] > key[score]) {
				data[j+1] = data[j];
				j = j - 1;
				compare += 1;
				change += 1;
			}
			data[j + 1] = key;
			if(j >= 0) compare += 1;
		}
		sortInfo[0] = compare;
		sortInfo[1] = change;
	}
	
	//進行merge sort
	static void mergeSort(int[][] data, int p, int r, int[] sortInfo) {
		if(p < r) {
			int q = (p + r) / 2;
			mergeSort(data, p, q, sortInfo);
			mergeSort(data, q+1, r, sortInfo);
			merge(data, p, q, r, sortInfo);
		}
	}
	
	//將資料merge
	static void merge(int[][] data, int p, int q, int r, int[] sortInfo) {
		int compare = sortInfo[0];
		int change = sortInfo[1];
		int Llength = q - p + 1;
		int Rlength = r - q;
		
		//將data分一半，並分別存入左右陣列
		int[][] Ldata = new int[Llength+1][2];
		int[][] Rdata = new int[Rlength+1][2];
		for(int i = 0; i < Llength; i++) {
			Ldata[i][0] = data[p+i][0];
			Ldata[i][1] = data[p+i][1];
		}
		for(int j = 0; j < Rlength; j++) {
			Rdata[j][0] = data[q+1+j][0];
			Rdata[j][1] = data[q+1+j][1];
		}
		
		Ldata[Llength][1] = Integer.MAX_VALUE;
		Rdata[Rlength][1] = Integer.MAX_VALUE;
		
		//比較左右兩陣列數的大小，並計算比較和交換次數
		int i = 0;
		int j = 0;
		for(int k = p; k <= r; k++) {
			if(Ldata[i][1] <= Rdata[j][1]) {
				if(j != Rlength) {
					compare += 1;
				}
				data[k] = Ldata[i];
				i++;
			}
			else{
				if(i != Llength) {
					compare += 1;
					change += 1;
				}
				data[k] = Rdata[j];
				j++;
			}
		}
		sortInfo[0] = compare;
		sortInfo[1] = change;
	}
	
	//進行radix sort
	static void radixSort(int[][] data, int length) {
		int max = getMax(data, length);
		
		for(int i = 1; max/i > 0; i*=10) {
			countingSort(data, length, i);
		}
	}
	
	//進行counting sort
	static void countingSort(int[][] data, int length, int num) {
		int[][] sortedData = new int[length][2];
		int[] count = new int[10];
		int score = 1;
		
		//計算目前位數的數量
		for(int i = 0; i < length; i++) {
			int n = (data[i][score] / num) % 10;
			count[n]++;
		}
		
		//將總數加總
		for(int i = 1; i < 10; i++) {
			count[i] += count[i-1];
		}
		
		//排序
		for(int i = length-1; i >= 0; i--) {
			int n = (data[i][score]/num) % 10;
			sortedData[count[n] -1] = data[i];
			count[(data[i][score]/num) % 10]--;
		}
		
		//將排序過的陣列儲存回原本的陣列
		for(int i = 0; i < length; i++) {
			data[i] = sortedData[i];
		}
	}
	
	//
	static int getMax(int[][] arr, int n) {
		int max = arr[0][1];
		for(int i = 1; i < arr[0].length; i++) {
			if(arr[i][1] > max) max = arr[i][1];
		}
		
		return max;
	}
}
