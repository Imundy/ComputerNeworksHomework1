/* Author :Ian Mundy
 * ian.m.mundy@vadnerbilt.edu
 * 2/13/2014
 * CS283
 * Some code taken from CS283 in class examples and refitted for assignment
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	private static final int PORT = 8888;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("ServerSocket created");
		System.out.println("Waiting for client connection on port " + PORT);
		while (true) {
			
			Socket cs = ss.accept();
			//System.out.println("Client connected");

			BufferedReader r = new BufferedReader(new InputStreamReader(
					cs.getInputStream()));
			String line;
			PrintStream out = new PrintStream(cs.getOutputStream());
			//read the line and then write a response back to the server
			while ((line = r.readLine()) != null) {
				line = line.toUpperCase();
				out.println(line);
				
			}
			//System.out.println("Client disconnected");
			r.close();
		}
		
	}
}

