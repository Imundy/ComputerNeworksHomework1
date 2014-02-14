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
import java.net.Socket;
import java.net.UnknownHostException;



public class BenchmarkClient {

	public static void main(String[] args) throws UnknownHostException, IOException  {
		//run both implementations
		long serialTime = serialServer();
		long threadedTime = serialServer();
		
		//print out the results
		System.out.println("\n\n Serial Time Elapsed: " + serialTime + "ns");
		System.out.println(" Threaded Time Elapsed: " + threadedTime + "ns");
	}
	
	/**
	 * Runs the serial implementation of our server
	 * 
	 * @return total time elapsed in nanoseconds
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static long serialServer() throws UnknownHostException, IOException{
		long startTime = System.nanoTime();
		Socket s = new Socket("localhost", 8888);
		PrintStream ps = new PrintStream(s.getOutputStream());
		for(int i = 0; i <= 1000; i++){	
			
			//System.out.println("Client is connected to the server");
			
			//Send the string to the server
			
			ps.println("caps lock is cruise control for cool.");
			
			//read back response
			BufferedReader r = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			String line;
			line = r.readLine();
			System.out.println(line);
				
			
			
		}
		ps.close();
		long endTime = System.nanoTime();
		return (endTime - startTime);
	}
	
	
	/**
	 * Runs the threaded implementation of our server
	 * Spawns three threads and waits for them to join
	 * 
	 * @return total time elapsed in nanoseconds
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static long MTServer() throws UnknownHostException, IOException{
		long startTime = System.nanoTime();
		
		//spawn three threads
		ClientThread c1 = new ClientThread();
		ClientThread c2 = new ClientThread();
		ClientThread c3 = new ClientThread();
		c1.start();
		c2.start();
		c3.start();
		
		//wait for threads to join
		try{
			c1.join();
			c2.join();
			c3.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		
		long endTime = System.nanoTime();
		return (endTime - startTime);
	}
	
	/**
	 * Run method executes calls to the multithreaded server
	 * @author Ian Mundy
	 *
	 */
	private static class ClientThread extends Thread{
		
		public void run(){
			try{
				Socket s = new Socket("localhost", 8889);
				PrintStream ps = new PrintStream(s.getOutputStream());
				for(int i = 0; i <= 334; i++){	
					
					//System.out.println("Client is connected to the server");
					
					//Send the string to the server
					
					ps.println("caps lock is cruise control for cool.");
					
					//read back response
					BufferedReader r = new BufferedReader(new InputStreamReader(
							s.getInputStream()));
					String line;
					line = r.readLine();
					System.out.println(line);
						
					
					
				}
				ps.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
	}
	
	
}
