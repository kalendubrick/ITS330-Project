
public class ClientTest {

	public ClientTest(){}
	
	public void testAdvNetworks(){	//Lab 7	passing data objects
		String host = "localhost";
	    int port = 8088;  
	    ThreadedPrintClientAdvNet pc2 = new ThreadedPrintClientAdvNet(1, host,port);
	    pc2.connect();		
	}
//	
	
	public void testThreadedClient(){	//Lab 7	passing data objects
		String host = "205.215.116.66";
	    int port = 8088;  
	    for (int i = 0; i < 10; i++)
	    {
	    	ThreadedPrintClientAdvNet pc = new ThreadedPrintClientAdvNet(i, host, port);
	    	Thread t = new Thread(pc);
	    	t.start();
	    }		
	}
	
	public static void main(String args[]){	
		
		ClientTest ct = new ClientTest();
		//ct.testOOAD();
		//ct.testNetworks();
		//ct.testThreads();
		//ct.testUtil(0, 1, 10);
		//ct.testFileIO(0, 1, 10);
		//ct.demoSyn();
		//ct.testAdvNetworks();
		ct.testThreadedClient();
		
	}
}
