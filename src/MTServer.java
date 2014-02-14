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


public class MTServer {

	private static final int PORT = 8889;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("ServerSocket created");
		System.out.println("Waiting for client connection on port " + PORT);
		while (true) {
			
			Socket cs = ss.accept();
			//ystem.out.println("Server Thread Spawning");
			new ServerThread(cs).start();
			
		}
	}
	
	/**
	 * Run method handles server calls.
	 * Must be givena  socket to be initialized to.
	 * 
	 * @author Ian Mundy
	 *
	 */
	private static class ServerThread extends Thread{
		
		Socket socket_;
		
		ServerThread(Socket socket){
			socket_ = socket;
		}
		
		public void run() {
			try{
				BufferedReader r = new BufferedReader(new InputStreamReader(
						socket_.getInputStream()));
				String line;
				PrintStream out = new PrintStream(socket_.getOutputStream());
				//read the line and then write a response back to the server
				while ((line = r.readLine()) != null) {
					line = line.toUpperCase();
					out.println(line);
					
				}
				//System.out.println("Client disconnected");
				r.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
}
	

