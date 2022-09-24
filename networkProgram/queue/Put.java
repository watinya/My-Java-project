
public class Put extends Thread {
	Queue queue;
	
	Put(Queue queue) {
		super();
		this.queue = queue;
	}
	
	//override run
	public void run() {
		while(true) {
			putRandomNum();
		}
	}
	
	//隨機產生一個數並放入queue
	void putRandomNum() {
		synchronized(queue) {
			//如果queue的size大於等於5則上鎖
			if(queue.size() >= Queue.MAX_SIZE) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int randomNum = (int)(Math.random() * 100);
			queue.add(randomNum);
			System.out.println(randomNum + "\t[Put Number]");
			queue.notify();//拿回鑰匙到queue所屬位置
		}
	}
}
