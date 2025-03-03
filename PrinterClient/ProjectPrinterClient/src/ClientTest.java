
public class ClientTest {

	public ClientTest(){}
	
	public void testAdvNetworks(){	//Lab 7	passing data objects
		String host = "localhost";
	    int port = 8088;  
	    ThreadedPrintClientAdvNet pc2 = new ThreadedPrintClientAdvNet(1, host,port);
	    pc2.connect();		
	}
//	
	
	public void testThreadedClient(String ip, int portNum, int clients){	//Lab 7	passing data objects
		String host = ip;
	    int port = portNum;  
	    for (int i = 0; i < clients; i++)
	    {
	    	ThreadedPrintClientAdvNet pc = new ThreadedPrintClientAdvNet(i, host, port);
	    	Thread t = new Thread(pc);
	    	t.start();
	    }	
	    try
	    {
	    	Thread.sleep(2000);
	    	ThreadedPrintClientAdvNet pc1 = new ThreadedPrintClientAdvNet(-1, host, port);
	    	Thread t = new Thread(pc1);
	    	t.start();
	    }
	    catch(Exception e) { e.printStackTrace(); }
	    try
	    {
	    	Thread.sleep(2000);
	    	ThreadedPrintClientAdvNet pc2 = new ThreadedPrintClientAdvNet(-2, host, port);
	    	Thread t = new Thread(pc2);
	    	t.start();
	    }
	    catch(Exception e) { e.printStackTrace(); }
	}
	
	public static void main(String args[]){	
		
		if (args.length < 3 || args[0] == "-h")
		{
			System.out.println("You must provide a value for IP Address, port number, and number of clients\n");
			System.out.println("IE: java -jar client.jar 123.456.789.234 8088 1000");
			System.out.println("Where 123.456.789.234 is the IP address of the server you are trying to send print clients to");
			System.out.println("Where 8088 is the port number the server is running on that you are trying to send print clients to");
			System.out.println("Where 1000 is the number of print clients you want to send to the server");
		}
		else
		{
			String ip = args[0];
			int port = Integer.valueOf(args[1]);
			int clients = Integer.valueOf(args[2]);
			
			ClientTest ct = new ClientTest();
			ct.testThreadedClient(ip, port, clients);
		}
		
		
//		if (args[1] == null)
//		{
//			System.out.println("You must provide a value for port number");
//			System.out.println("IE: java -jar client.jar 123.456.789.234 8088 1000");
//			System.out.println("Where 8088 is the port number the server is running on that you are trying to send print clients to");
//		}
//		
//		if (args[2] == null)
//		{
//			System.out.println("You must provide a value for number of clients to send");
//			System.out.println("IE: java -jar client.jar 123.456.789.234 8088 1000");
//			System.out.println("Where 1000 is the number opf print clients you want to send to the server");
//		}
		
//		String ip = args[0];
//		int port = Integer.valueOf(args[1]);
//		int clients = Integer.valueOf(args[2]);
		
		
		//ct.testOOAD();
		//ct.testNetworks();
		//ct.testThreads();
		//ct.testUtil(0, 1, 10);
		//ct.testFileIO(0, 1, 10);
		//ct.demoSyn();
		//ct.testAdvNetworks();
		
		
		
		
	}
}
