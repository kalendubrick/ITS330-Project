import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadedPrintClientAdvNet extends Client implements Runnable
{
	private int ID, threadID;
	private String host;
	private int port;
	private Job job;
	//private ObjectOutputStream objOut;
	// private ObjectInputStream objIn;
	private Random ran;
	
	public ThreadedPrintClientAdvNet(int i, String host, int port)
	{
		this.threadID = i;
		this.ID = i;
		this.host = host;
		this.port = port;
		// create job for this thread
		this.ran = new Random();
		this.job = new Job(this.ID, 3);
	}
	
	public void run()
	{
		connect();
	}
	
	public void connect()
	{
		try
		{
			Socket client = new Socket(this.host, this.port);
			handleConnectionObj(client);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void handleConnectionObj(Socket client) throws Exception
	{
		ObjectOutputStream objOut = new ObjectOutputStream
				(new BufferedOutputStream(client.getOutputStream(), 2048));
		ObjectInputStream objIn = new ObjectInputStream
				(new BufferedInputStream(client.getInputStream()));

		objOut.writeObject(this.job);
		objOut.flush();
		objOut.close();
		objIn.close();
		try {Thread.sleep(3000);}catch (Exception e) {}
		client.close();
	}
}
