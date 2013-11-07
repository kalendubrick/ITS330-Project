/* 
 * Kalen Dubrick
 */

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class QueueThreadedPrintServerAdvNet implements Runnable
{
	private int port, MaxConnections;
	private ConcurrentLinkedQueue<Job> JobQueue;
	
	public QueueThreadedPrintServerAdvNet() {}
	public QueueThreadedPrintServerAdvNet(int port, int maxConnections)
	{
		this.port = port;
		this.MaxConnections = maxConnections;
		this.JobQueue = new ConcurrentLinkedQueue<Job>();
		
		QueuePrintThread pt = new QueuePrintThread ("PrintThread 1", this.JobQueue);
		pt.start();
		
		QueuePrintThread pt2 = new QueuePrintThread ("PrintThread 2", this.JobQueue);
		pt2.start();
		
		QueueComputeThread ct = new QueueComputeThread ("ComputeThread 1", this.JobQueue);
		ct.start();
		
		QueueComputeThread ct2 = new QueueComputeThread ("ComputeThread 2", this.JobQueue);
		ct2.start();
		
		// start your computing thread here
		
	}
	
	public void run()
	{
		QueueConnection currentThread = (QueueConnection)Thread.currentThread();
		try
		{
			handleConnectionObj(currentThread.getSocket());
		}
		catch (Exception e) { e.printStackTrace(); }
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
				//handleConnectionObj(serverSideSocket); // communicate with client by using this section
				QueueConnection cn = new QueueConnection(this, serverSideSocket, i);
				cn.start();
			}
			
			listener.close();
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
	}
	
	public void handleConnectionObj(Socket server) throws IOException
	{
		ObjectOutputStream objOut = new ObjectOutputStream
						(new BufferedOutputStream(server.getOutputStream(), 2048));
		objOut.flush();
		ObjectInputStream objIn = new ObjectInputStream
						(new BufferedInputStream(server.getInputStream()));
		
		try
		{
			Job job = (Job)objIn.readObject();
			this.JobQueue.add(job);
			System.out.println("The Job ID is " + job.getJobID() + 
					" and the number of ops is " + job.getOPNumber());
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

class QueueConnection extends Thread
{
	private Socket serverSideSocket;
	
	public QueueConnection(Runnable obj, Socket s, int id)
	{
		super(obj, "server thread " + id);
		serverSideSocket = s;
	}
	
	public Socket getSocket()
	{
		return serverSideSocket;
	}
}

class QueuePrintThread extends Thread
{
	private ConcurrentLinkedQueue<Job> jobQ;
	private boolean stop = false;
	
	public QueuePrintThread(ConcurrentLinkedQueue<Job> queue)
	{
		super("ThePrintingThread");
		this.jobQ = queue;
	}
	
	public QueuePrintThread(String threadName, ConcurrentLinkedQueue<Job> queue)
	{
		super(threadName);
		this.jobQ = queue;
	}
	
	public void run()
	{
		while(!stop)
		{
			while (!jobQ.isEmpty())
			{
				int i;
				int size = jobQ.size();
				for (i = 0; i < size; i++)
				{
					Job job = jobQ.remove();
				
					ConcurrentLinkedQueue<Operation> opQ = job.getOPs();
					
					Operation op = opQ.remove();
						
					if (op.getOPID() == 1)
					{
						// print the job description to simulate the printing
						System.out.println("The operation type is 1 for Job " + op.getJobID() +
											"\nAnother quality job from " + Thread.currentThread().getName());
							
						if (opQ.isEmpty()) // check again after running the op
							job.setIsDone(true);
					}
					else
						opQ.add(op);
					
					if (!job.isDone())
						jobQ.add(job);
				}		
			}
			
			try
			{
				QueuePrintThread.sleep(1000);
			}
			catch (Exception e) { e.printStackTrace(); }
			// if no job, thread block itself to yield CPU
		}
	}
}

class QueueComputeThread extends Thread
{
	private ConcurrentLinkedQueue<Job> jobQ;
	private boolean stop = false;
	
	public QueueComputeThread(ConcurrentLinkedQueue<Job> queue)
	{
		super("TheComputingThread");
		this.jobQ = queue;
	}
	
	public QueueComputeThread(String threadName, ConcurrentLinkedQueue<Job> queue)
	{
		super(threadName);
		this.jobQ = queue;
	}
	
	public void run()
	{
		while(!stop)
		{
			while (!jobQ.isEmpty())
			{
				int i;
				int size = jobQ.size();
				for (i = 0; i < size; i++)
				{
					Job job = jobQ.remove();
				
					ConcurrentLinkedQueue<Operation> opQ = job.getOPs();
					
					Operation op = opQ.remove();
						
					if (op.getOPID() == 2)
					{
						// do some computation here
							
						if (opQ.isEmpty()) // check again after running the op
							job.setIsDone(true);
					}
					else
						opQ.add(op);
					
					if (!job.isDone())
						jobQ.add(job);
				}		
			}
			
			try
			{
				QueuePrintThread.sleep(1000);
			}
			catch (Exception e) { e.printStackTrace(); }
			// if no job, thread block itself to yield CPU
		}
	}
}
