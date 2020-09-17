/**

 * 
 */
package player;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Server.InputListener;
import Server.Notice;
import gui.TictactoeUI;

/**
 * Observerable 
 * Contain the functions in players side 
 * @author Cuong Nguyen 783901
 *
 */
public class GamePlayers implements PropertyChangeListener
{
	static Socket socket;
	private static ObjectOutputStream oos;
	private InputListener inListener;
//	private Message m;
	private Movement gamemove;
	private String UserName = "";

	/**
	 * Generate the connection to server
	 */
	public GamePlayers()
	{
		String serverip = JOptionPane.showInputDialog("Server address: ");
		String portnumber = JOptionPane.showInputDialog("Port number: ");
		int convertPortnumber = Integer.parseInt(portnumber);
		try {
			socket = new Socket(serverip, convertPortnumber);
			oos = new ObjectOutputStream(socket.getOutputStream());
			inListener = new InputListener(socket, this);
			Thread ilConnect = new Thread(inListener);
			ilConnect.start();
			ServerCommunication sc = new ServerCommunication("a");
			oos.writeObject(sc);
			String nameInput = JOptionPane.showInputDialog("Enter Username: ");
			UserName = nameInput;
//			m = new Message();
//			m.setUsername(nameInput);
			nameInput += "#&@*#(@&((!&39&#3KSJW82DSWDYA33#$";
			TictactoeUI.updateShowBoard(nameInput, 1);
			gamemove = new Movement();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * send message to inputListener
	 * 
	 * @param mes message
	 */
	public void gernerateMes(String mes)

	{
		try {
//			System.out.println("mes " + mes);
//			m.setContent(mes);
			Message a = new Message(UserName, mes);
//			System.out.println("toStringinGP "+ m);
			oos.writeObject(a);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can't send this message");
			e.printStackTrace();
		}
	}

	/**
	 * Send the move to another players
	 * 
	 * @param buttonName move button name
	 */
	public void gernerateMove(String buttonName)
	{
		String[] position = buttonName.split(" ");
		int ck = 0;
		int row = Integer.parseInt(position[0]);
		int col = Integer.parseInt(position[1]);
		try {

			if (gamemove.updateMove(row, col) == true) {
				ck = 1;
				Process process = new Process(row, col, ck);
				oos.writeObject(process);
				TictactoeUI.winner(ck);

			} else

			{
				Process process = new Process(row, col, ck);

				oos.writeObject(process);
			}

			if (gamemove.draws == true) {
				int a = 1119;
				Process process = new Process(row, col, ck, a);
				oos.writeObject(process);
				TictactoeUI.setDraw(a);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Receiver the message, move and reset request from another player
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		Object receive = evt.getNewValue();
		if (receive instanceof Message) {
			String displayMessage = receive.toString();
			System.out.println("displayMessage: " + displayMessage);
			TictactoeUI.updateShowBoard(displayMessage, 2);

		} else if (receive instanceof Process) {
			String[] pos = ((Process) receive).toString().split(" ");
			int row = Integer.parseInt(pos[0]);
			int col = Integer.parseInt(pos[1]);
			int check = Integer.parseInt(pos[2]);
			int drawCheck = Integer.parseInt(pos[3]);

			TictactoeUI.updateGamePlay(row, col, check, drawCheck);
//			if(reset.equals("resetGG"))
//			{
//				gamemove.resetList();
//			}
		} else if (receive instanceof Notice) {
			String turn = ((Notice) receive).toString();
			TictactoeUI.setGameTurn(turn);

		} else if (receive instanceof resetMessage) {
			String res = ((resetMessage) receive).toString();
			if (res.equals("resetGG")) {

				TictactoeUI.updateReset(res);
				gamemove = new Movement();
			}
		}

	}

	/**
	 *used to reset the game 
	 */
	public void resetGame()
	{

		gamemove = new Movement();
		String resetCommand = "resetGG";
		resetMessage resetRequest = new resetMessage(resetCommand);
		try {
			oos.writeObject(resetRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
