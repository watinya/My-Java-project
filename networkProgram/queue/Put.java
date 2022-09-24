
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
	
	//�H�����ͤ@�Ӽƨé�Jqueue
	void putRandomNum() {
		synchronized(queue) {
			//�p�Gqueue��size�j�󵥩�5�h�W��
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
			queue.notify();//���^�_�ͨ�queue���ݦ�m
		}
	}
}
