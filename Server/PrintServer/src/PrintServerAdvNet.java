/* 
 * Kalen Dubrick
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class PrintServerAdvNet
{
	private int port, MaxConnections;
	private Vector<Job> JobQueue;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	
	public PrintServerAdvNet() {}
	public PrintServerAdvNet(int port, int maxConnections)
	{
		this.port = port;
		this.MaxConnections = maxConnections;
		this.JobQueue = new Vector<Job>();
	}
	
	public int getPort()
	{
		return port;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
	
	public int getMax()
	{
		return MaxConnections;
	}
	
	public void setMax(int maxConnections)
	{
		this.MaxConnections = maxConnections;
	}
	
	public void listen() // standard name for server method
	{
		System.out.println("The server is running");
		
		int i = 0;
		
		try
		{
			ServerSocket listener = new ServerSocket(this.port);
			
			while (i < MaxConnections)
			{
				// instead of simply call a method, we need to create thread to handle
				// accept for any connection from client
				Socket serverSideSocket;
				serverSideSocket = listener.accept();
				i++;
				//end to end connection (i.e., a socket) with client
				handleConnectionObj(serverSideSocket); // communicate with client by using this section
			}
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
	}
	
	public void handleConnectionObj(Socket server) throws IOException
	{
		this.objOut = new ObjectOutputStream
						(new BufferedOutputStream(server.getOutputStream(), 2048));
		objOut.flush();
		this.objIn = new ObjectInputStream
						(new BufferedInputStream(server.getInputStream()));
		
		try
		{
			Job job = (Job)objIn.readObject();
			this.JobQueue.add(job);
			System.out.println("The Job ID is " + job.getJobID() + 
					" and the number of ops is " + job.getOPNumber());
			
			if (job == null)
			{
				System.out.println("A null job got added");
			}
			else
			{
				int opn = job.getOPNumber();
				Vector<Operation> opv = job.getOPs();
				
				for (int i = 0; i < opn; i++)
				{
					Operation op = opv.elementAt(i);
					
					if (op.getOPID() == 1)
					{
						System.out.println("The operation type is 1");
					}
					else if (op.getOPID() == 2)
					{
						System.out.println("The operation type is 2");
					}
					else
					{
						System.out.println("The operation type is 0");
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		
		objOut.close();
        objIn.close();
        server.close();    
	}
}
