import java.util.ArrayList;

public class CourseSchedule {
	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	ArrayList<Integer> answer = new ArrayList<Integer>();
	
	//將輸入的先決條件存入Array
	int[][] toArray(String str){
		str = str.replace("[", "").replace("]", "");
		String[] s = str.split(",");
		int[][] result = new int[s.length/2][2];
		
		for(int i = 0; i < s.length/2; i++) {
			result[i][0] = Integer.parseInt(s[i*2].trim());
			result[i][1] = Integer.parseInt(s[i*2+1].trim());
		}
		
		return result;
	}
	
	//判斷
	boolean judgeSchedule(int courseNum, int[][] prerequire) {	
		//建立每堂課的ArrayList
		for(int i = 0; i < courseNum; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		//將先決條件加到該堂課的ArrayList中(需先學習的課程)
		for(int i = 0; i < prerequire.length; i++) {
			int course = prerequire[i][0];
			int prerequisite = prerequire[i][1];
			graph.get(course).add(prerequisite);
		}
		
		//利用dfs排序，並判斷是否可完成
		int[] visited = new int[courseNum];
		for(int i = 0; i < courseNum; i++) {
			if(dfs(i, graph, visited)) {
				return false;
			}
		}
		return true;
	}

	//dfs排序
	private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited){
		//判斷是否走過該點
		//如現在的點為1表示有迴圈存在，無法完成排課
		if(visited[curr] == 1) return true;
		//如現在的點為2表示該點已完成
		if(visited[curr] == 2) return false;
		
		//紀錄已經過現在的點，並迭代他的先決條件
		visited[curr] = 1;
		for(int next : graph.get(curr)) {
			if(dfs(next, graph, visited)) {
				return true;
			}
		}
		
		//紀錄現在的點已完成，並加進answer的ArrayList中
		visited[curr] = 2;
		answer.add(curr);
		return false;
	}
}
