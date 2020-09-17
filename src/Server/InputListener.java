/**
 * 
 */
package Server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Observerable 
 * @author Cuong Nguyen 783901
 *
 */
public class InputListener implements Runnable
{
	private ObjectInputStream ois;
	List<PropertyChangeListener> observersList = new ArrayList<>();
	Socket socket = null;
	public int clientId;
	/**
	 * load the observersList 
	 * @param clientId player id 
	 * @param socket socket 
	 * @param observer propertychangeListener
	 */
	public InputListener(int clientId, Socket socket, PropertyChangeListener observer)
	{
		this.clientId = clientId;
		this.socket = socket;
		observersList.add(observer);

	}
	/**
	 * load the observersList
	 * @param socket
	 * @param ob socket 
	 */
	public InputListener(Socket socket, PropertyChangeListener ob)
	{
		this.socket = socket;
		observersList.add(ob);
	}
	/**
	 * receiveve the object 
	 */
	@Override
	public void run()
	{
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			while (true) {
				Object pack = ois.readObject();
				System.out.println("Pack "+ pack.toString());
				notifyListeners(pack);

			}
		} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Can't recieve Object");
			e.printStackTrace();
		}

	}
	/**
	 * notify change
	 * @param o
	 */
	private void notifyListeners(Object o)
	{
		for (PropertyChangeListener listener : observersList) {
			listener.propertyChange(new PropertyChangeEvent(this, null, null, o));
		}
	}

}
