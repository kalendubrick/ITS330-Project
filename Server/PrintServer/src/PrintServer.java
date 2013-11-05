/* 
 * Kalen Dubrick
 */

import java.net.*;
import java.io.*;

public class PrintServer 
{
	private int port, MaxConnections;
	
	public PrintServer() {}
	public PrintServer(int port, int maxConnections)
	{
		this.port = port;
		this.MaxConnections = maxConnections;
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
		int i = 0;
		
		try
		{
			// building a listening server socket
			ServerSocket listener = new ServerSocket(port);
			Socket serverSpecific;
			
			while (i < MaxConnections)
			{
				// accept for any connection from client
				serverSpecific = listener.accept();
				i++;
				//end to end connection (i.e., a socket) with client
				handleConnection(serverSpecific); // communicate with client by using this section
			}
			
			listener.close();
		}
		catch (Exception e)
		{
			
		}
	}
	
	public void handleConnection(Socket server) throws IOException
	{
		// build a BufferReader object so you can read strings, line by line
		BufferedReader in = (new BufferedReader
				(new InputStreamReader(server.getInputStream())));
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		
		System.out.println("Printer Server: Got a connection from " + 
				server.getInetAddress().getHostName() + "\n" +
				"with first line '" + in.readLine() + "'");
		
		out.println("comes from Printer Server");
		server.close();
	}
}
