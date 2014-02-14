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


public class SimpleClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		for(int i = 0; i <= 100; i++){
			Socket s = new Socket("localhost", 8888);
			//System.out.println("Client is connected to the server");
			
			//Send the string to the server
			PrintStream ps = new PrintStream(s.getOutputStream());
			ps.println("caps lock is cruise control for cool.");
			
			//read back response
			BufferedReader r = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			String line;
			line = r.readLine();
			System.out.println(line);
				
			
			ps.close();
		}
	}
	
}
