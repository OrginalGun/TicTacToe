
/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Server.GameServer;

/**
 * the class use to create server's user interface
 * 
 * @author 783901
 *
 */
public class ServerGUI
{

	private static JFrame frame;
	private static GameServer runServer;

	/**
	 * create the frame for server UI
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		frame = new JFrame("UTRAL TICTACTOE SERVER");
		frame.setBounds(100, 100, 200, 200);
		frame.add(new JLabel("SERVER"), BorderLayout.NORTH);
		JButton start = new JButton("START");
		start.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				runServer = new GameServer();
				runServer.start();
			}
		});
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
//				runServer.closeServer();
				System.exit(0);
			}
		});
		frame.add(start);

		frame.setVisible(true);
	}

}
