import java.net.*;
import java.io.*;
import java.util.*;

public class PrintClientAdvNet extends Client
{
	private int ID, threadID;
	private String host;
	private int port;
	private Job job;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private Random ran;
	
	public PrintClientAdvNet(int i, String host, int port)
	{
		this.threadID = 1;
		this.ID = i;
		this.host = host;
		this.port = port;
		// create job for this thread
		this.ran = new Random();
		this.job = new Job(this.ID, this.ran.nextInt(3) + 1);
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
		this.objOut = new ObjectOutputStream
				(new BufferedOutputStream(client.getOutputStream(), 2048));
		this.objIn = new ObjectInputStream
				(new BufferedInputStream(client.getInputStream()));

		objOut.writeObject(this.job);
		objOut.flush();
		objOut.close();
		objIn.close();
		try {Thread.sleep(3000);}catch (Exception e) {}
		client.close();
	}
}
