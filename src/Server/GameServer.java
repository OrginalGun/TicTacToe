package Server;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;



/**
 * 
 */

/**
 * Observer 
 * Create the Server for Tictactoe game
 * @author Cuong Nguyen 000 783901
 *
 */
public class GameServer extends Thread
{

	private  ServerSocket serversocket = null; 
	private  Socket		 socket = null;
	private  ArrayList<Socket> socketlist = new ArrayList<>();
	private static int PORT_NUMBER = 1234;

	/**
	 * Start running server and connect server to clientHandler 
	 * @param args
	 */
	public void run()
	{
		try {
			serversocket = new ServerSocket(PORT_NUMBER);
			System.out.println("Server is up");
		while(true)
		{
			socket = serversocket.accept();
			socketlist.add(socket);
			System.out.println(socket.getInetAddress()+ " is connect");
			if (socketlist.size() == 2)
			{
				ClientHandler clh = new ClientHandler(socketlist.get(0),socketlist.get(1));
				Thread thread = new Thread(clh);
				thread.start();
				socketlist.clear();
			}
			System.out.println("Server is waiting for next connection");
		}
		} 
		catch (IOException e) 
		{
			
//			try {
//				socket.close();
//				serversocket.close();
//				System.out.println("server is closed");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			e.printStackTrace();
		}
	}
	/*
	 *close server 
	 */
	public void closeServer()
	{
		try {
			socket.close();
			serversocket.close();
		}
		catch (IOException e) 
		{
			System.out.println("server can't close");
			e.printStackTrace();
		}
	}
}