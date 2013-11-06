
public class ServerTest {
	
	public void TestQueueThreadedServerAdvNet()
	{
		int port = 8088;
		QueueThreadedPrintServerAdvNet ps = new QueueThreadedPrintServerAdvNet(port, 5000);
		
		ps.listen(); // ask a server to listen for connections from clients
		System.out.println("server is terminated");
	}
	
	public static void main(String[] args) 
	{
		ServerTest st = new ServerTest();
		st.TestQueueThreadedServerAdvNet();

	}

}
