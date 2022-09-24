
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
	
	//���Xqueue�����Ĥ@�Ӽ�
	void getFirstNum() {
		synchronized(queue) {
			//�p�Gqueue��size�p�󵥩�0�h�W��
			if(queue.size() <= 0) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(queue.get(0) + "\t\t[Get Number]");
			queue.remove(0);//�N�ӼƲ���
			queue.notify();//���^�_�ͨ�queue���ݦ�m
		}
	}
}
