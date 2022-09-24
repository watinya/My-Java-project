public class Client {

	public static void main(String[] args) {
		Queue queue = new Queue();
		new Put(queue).start();
		new Get(queue).start();
	}
}
