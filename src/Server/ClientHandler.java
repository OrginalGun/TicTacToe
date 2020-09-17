/**
 * 
 */
package Server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

import player.ServerCommunication;

/**
 * Observer 
 * use as a game room 
 * @author Cuong Nguyen 783901
 *
 */
public class ClientHandler implements Runnable, PropertyChangeListener
{
	private Socket socket01;
	private Socket socket02;
	private static int CLIENT_ID_01 = 1;
	private static int CLIENT_ID_02 = 2;
	private InputListener inListen1;
	private InputListener inListen2;
	private ObjectOutputStream oos1;
	private ObjectOutputStream oos2;

	/**
	 * Create ObjectoutputStream 1 and 2
	 * 
	 * @param socket01 socket connect to inputListener 1
	 * @param socket02 socket connect to inputListener 2
	 */
	public ClientHandler(Socket socket01, Socket socket02)
	{
		this.socket01 = socket01;
		this.socket02 = socket02;
		try {
			OutputStream os1 = socket01.getOutputStream();
			OutputStream os2 = socket02.getOutputStream();
			oos1 = new ObjectOutputStream(os1);
			oos2 = new ObjectOutputStream(os2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * create inputListener 1 and 2
	 */
	@Override
	public void run()
	{
		inListen1 = new InputListener(CLIENT_ID_01, socket01, this);
		inListen2 = new InputListener(CLIENT_ID_02, socket02, this);
		Thread firstThread = new Thread(inListen1);
		Thread secondThread = new Thread(inListen2);
		firstThread.start();
		secondThread.start();
		while (socket01.isConnected() && socket02.isConnected())
			;

	}

	/**
	 * receive object from client players
	 */
	@Override
    public synchronized void  propertyChange(PropertyChangeEvent evt)
	{

		int getId = ((InputListener) evt.getSource()).clientId;

		if (getId == CLIENT_ID_01) {
			try {
				if (evt.getNewValue() instanceof ServerCommunication) {
					Notice send = new Notice(111);
					oos1.writeObject(send);
				} else {
					oos2.writeObject(evt.getNewValue());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				if (evt.getNewValue() instanceof ServerCommunication) {
					Notice send = new Notice(222);
					oos2.writeObject(send);
				} else {
					oos1.writeObject(evt.getNewValue());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
