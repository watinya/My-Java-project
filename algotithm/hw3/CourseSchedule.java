import java.util.ArrayList;

public class CourseSchedule {
	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	ArrayList<Integer> answer = new ArrayList<Integer>();
	
	//�N��J�����M����s�JArray
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
	
	//�P�_
	boolean judgeSchedule(int courseNum, int[][] prerequire) {	
		//�إߨC��Ҫ�ArrayList
		for(int i = 0; i < courseNum; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		//�N���M����[��Ӱ�Ҫ�ArrayList��(�ݥ��ǲߪ��ҵ{)
		for(int i = 0; i < prerequire.length; i++) {
			int course = prerequire[i][0];
			int prerequisite = prerequire[i][1];
			graph.get(course).add(prerequisite);
		}
		
		//�Q��dfs�ƧǡA�çP�_�O�_�i����
		int[] visited = new int[courseNum];
		for(int i = 0; i < courseNum; i++) {
			if(dfs(i, graph, visited)) {
				return false;
			}
		}
		return true;
	}

	//dfs�Ƨ�
	private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited){
		//�P�_�O�_���L���I
		//�p�{�b���I��1��ܦ��j��s�b�A�L�k�����ƽ�
		if(visited[curr] == 1) return true;
		//�p�{�b���I��2��ܸ��I�w����
		if(visited[curr] == 2) return false;
		
		//�����w�g�L�{�b���I�A�í��N�L�����M����
		visited[curr] = 1;
		for(int next : graph.get(curr)) {
			if(dfs(next, graph, visited)) {
				return true;
			}
		}
		
		//�����{�b���I�w�����A�å[�ianswer��ArrayList��
		visited[curr] = 2;
		answer.add(curr);
		return false;
	}
}
