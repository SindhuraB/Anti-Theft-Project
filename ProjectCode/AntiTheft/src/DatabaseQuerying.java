import java.util.ArrayList;
import java.net.*;
import java.io.*;

// Coded by: Chris Guerra and Labeeba Rana
// This class creates a connection and queries the database

public class DatabaseQuerying{
	// Initialize client socket and input/output streams
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	
	static int accColChanged; // Number of columns changed from updating ACCOUNT table
	static int userColChanged; // Number of columns changed from updating TAGUSER table
	
	public String run(String requestInfo)
	{
		String result = "";
		try
		{
			// Connect to database server and setup input/output streams
			InetAddress serverAddress = InetAddress.getByName("serverConnection.dynu.net");
			socket = new Socket(serverAddress.getHostAddress(), 80);
			System.out.println("Connected: " + serverAddress);
			input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			output = new DataOutputStream(socket.getOutputStream());
			System.out.println("Sending request: " + requestInfo);
			
			// Send request to server
			try
			{
				System.out.println("Making request");
				// Write sql string for query to output
				output.writeUTF(requestInfo);
				output.flush();
			}
			catch(IOException i)
			{
				System.out.println(i);
			}
			result = input.readUTF();
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
		
		System.out.println("Closing server connection.");
		try
		{
			// Close socket connection and input/output streams
			input.close();
			output.close();
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
		return result;
	}
}