
public class Get extends Thread{
	Queue queue;
	
	Get(Queue queue){
		super();
		this.queue = queue;
	}
	
	//override run
	public void run() {
		while(true) {
			getFirstNum();
		}
	}
	
	//取出queue中的第一個數
	void getFirstNum() {
		synchronized(queue) {
			//如果queue的size小於等於0則上鎖
			if(queue.size() <= 0) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(queue.get(0) + "\t\t[Get Number]");
			queue.remove(0);//將該數移除
			queue.notify();//拿回鑰匙到queue所屬位置
		}
	}
}
