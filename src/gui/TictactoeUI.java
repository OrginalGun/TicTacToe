/**

 * 
 */
package gui;

import javax.imageio.ImageIO;

import javax.swing.*;

import player.GamePlayers;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * USER INTERFACE FOR OF THE PROGRAM
 * 
 * @author Cuong Nguyen 000783901
 * 
 */
public class TictactoeUI
{
	private JFrame frame;
	private JButton ub;
	private final static int ROW_SIZE = 20;
	private final static int COL_SIZE = 20;
	private static List<List<JButton>> listOfButton = new ArrayList<List<JButton>>();
	private JButton startbutton;
	private JButton resetbutton;
	private JTextArea inputMessage;
	private static JTextArea showBoard;
	private static String UN;
	private static GamePlayers client;
	private static int myTurn;
	private static ActionListener gameButtonAction;
	private static JLabel mainNotice;

	/**
	 * create entire user interface for the application and call the loadUbList
	 * method
	 */
	public TictactoeUI()
	{
		LoadUbList();
		insertUserInterface();

	}

	/**
	 * load the list which contains buttons in game board
	 */
	private void LoadUbList()
	{
		for (int i = 0; i < ROW_SIZE; i++) {
			List<JButton> ublist = new ArrayList<JButton>();

			for (int a = 0; a < COL_SIZE; a++) {
				ub = new JButton();
				setButtonAppe01(ub, i, a);
				ublist.add(ub);
			}
			listOfButton.add(ublist);
		}

	}

	/**
	 * set characteristics for buttons in game board
	 * 
	 * @param button JButton
	 * @param row    row
	 * @param col    column
	 */
	private static void setButtonAppe01(JButton button, int row, int col)
	{
		String name = row + " " + col;
		button.setName(name);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setBorderPainted(true);
		gameButtonAction = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					String a = button.getName();
					System.out.println(a);
					gameButtonState(1);
					mainNotice.setText("WAIT FOR YOUR OPPONENT MOVE");

					if (myTurn == 1) {
						button.setIcon(new ImageIcon(ImageIO.read(new FileInputStream(new File("res/x-button.png")))
								.getScaledInstance(25, 25, 10)));
					} else if (myTurn == 2) {
						button.setIcon(new ImageIcon(ImageIO.read(new FileInputStream(new File("res/o-button.png")))
								.getScaledInstance(25, 25, 10)));
					}
					client.gernerateMove(a);

				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Can't display ");
					e1.printStackTrace();
				}

			}
		};
		button.addActionListener(gameButtonAction);

	}

	/**
	 * set characteristics for start and reset buttons
	 * 
	 * @param start start button
	 * @param reset reset button
	 */
	private void setButtonAppe02(JButton start, JButton reset)
	{

		start.setPreferredSize(new Dimension(100, 50));
		reset.setPreferredSize(new Dimension(100, 50));
		start.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				client = new GamePlayers();
				if (myTurn == 1) {

					mainNotice.setText("YOU GO FIRST");
//					JOptionPane.showMessageDialog(null, "YOU GO FIRST");
				} else if (myTurn == 2) {
					mainNotice.setText("YOUR TURN IS SECOND");
//					JOptionPane.showMessageDialog(null, "WAIT FOR YOUR OPPONENT MOVE");
				}
				start.setEnabled(false);

			}
		});

		reset.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

//				start.setEnabled(true);
				if (myTurn == 1) {

					mainNotice.setText("YOU GO FIRST");
//					JOptionPane.showMessageDialog(null, "YOU GO FIRST");
				} else if (myTurn == 2) {
					mainNotice.setText("WAIT FOR YOUR OPPONENT MOVE");
//					JOptionPane.showMessageDialog(null, "WAIT FOR YOUR OPPONENT MOVE");
				}
				reLoadUbList();
				client.resetGame();
				JOptionPane.showMessageDialog(null, "Game is resets");
				gameButtonState(2);

			}
		});
	}

	/**
	 * creating the main frame
	 */
	private void insertUserInterface()
	{
		frame = new JFrame("UTRAL TICTACTOE");

		frame.setBounds(100, 100, 1000, 600);
		frame.setResizable(false);
		frame.add(WestP(), BorderLayout.WEST);
		frame.add(EastP(), BorderLayout.EAST);

		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		frame.setVisible(true);
	}

	/**
	 * the panel which contain all of component on left side
	 * 
	 * @return panel main left panel
	 */
	private static JPanel WestP()
	{
		int index = 0;

		JPanel panel = new JPanel();
//		{
//
//			@Override
//			protected void paintComponent(Graphics g)
//			{
//
//				super.paintComponent(g);
//				try {
//					g.drawImage(ImageIO.read(new FileInputStream(new File("img/Jungle.jpg"))), 0, 0, 750, 750, null);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		};

		panel.setBackground(Color.pink);
		panel.setLayout(new GridLayout(20, 20));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		for (index = 0; index < ROW_SIZE; ++index) {

			for (int i1 = 0; i1 < COL_SIZE; ++i1) {
				panel.add(TableUnit(index, i1));
			}
		}

		return panel;

	}

	/**
	 * small grid in the game board
	 * 
	 * @param row row
	 * @param col column
	 * @return panel panel of button in game board
	 */
	private static JPanel TableUnit(int row, int col)
	{

		JPanel panel = new JPanel(new GridLayout(1, 1));

		JButton button = listOfButton.get(col).get(row);
//		JButton button = new JButton();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(30, 30));
		panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

		panel.add(button);

//		panel.add(lable);

		return panel;
	}

	/**
	 * the main east panel which hold all of the component on right side
	 * 
	 * @return panel main right panel
	 */
	private JPanel EastP()
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 600));
//		panel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		panel.add(EastTopP(), BorderLayout.NORTH);
		panel.add(EastCenP(), BorderLayout.CENTER);
		panel.add(EastSouthP(), BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * the panel that contain game title and instructing message
	 * 
	 * @return panel right top panel
	 */
	private JPanel EastTopP()
	{
		JPanel panel = new JPanel();

		JLabel gamename = new JLabel();
		mainNotice = new JLabel("");
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.WHITE);
		gamename.setFont(new Font("TimesRoman", 3, 25));
		gamename.setText("ULTRA TICTACTOE");
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		panel.setPreferredSize(new Dimension(250, 100));
		panel.add(gamename);
		panel.add(mainNotice);
		return panel;
	}

	/**
	 * the panel contain start button and reset button
	 * 
	 * @return panel right centre panel
	 */
	private JPanel EastCenP()
	{
		JPanel panel = new JPanel(new FlowLayout(1, 20, 20));
		startbutton = new JButton("Start");
		resetbutton = new JButton("Reset");

		setButtonAppe02(startbutton, resetbutton);

		panel.setPreferredSize(new Dimension(400, 100));
//		panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		panel.add(startbutton);
		panel.add(resetbutton);

		return panel;
	}

	/**
	 * panel contain send button, input message area and text area which is used to
	 * display message.
	 * 
	 * @return panel right bottom panel
	 */
	private JPanel EastSouthP()
	{
		JPanel panel = new JPanel(new GridLayout(2, 1));
		JPanel top = new JPanel();
		JPanel bottom = new JPanel(new FlowLayout(1, 7, 7));
		JButton sendButton = new JButton("SEND");
		inputMessage = new JTextArea(5, 17);
		showBoard = new JTextArea(15, 23);
		showBoard.setEditable(false);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(showBoard);

//		top.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		showBoard.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		inputMessage.setBorder(BorderFactory.createLineBorder(Color.black, 1));
//		bottom.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		top.add(scroll);
		bottom.add(inputMessage);
		bottom.add(sendButton);
		panel.add(top);
		panel.add(bottom);

		sendButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String inputMesString = inputMessage.getText();
				System.out.println("InputMessage" + inputMesString);

				updateShowBoard(inputMesString, 1);
				client.gernerateMes(inputMesString);

			}

		});

		return panel;
	}

	/**
	 * display opponent chat message
	 * 
	 * @param message message
	 * @param id      player id
	 */
	public static void updateShowBoard(String message, int id)
	{
		if (message.contains("#&@*#(@&((!&39&#3KSJW82DSWDYA33#$")) {
			int p = message.indexOf("#&@*#(@&((!&39&#3KSJW82DSWDYA33#$");
			UN = message.substring(0, p);
		} else {

			if (id == 1) {
				if (showBoard.getText() == null) {
					showBoard.setText(UN + "@ " + message + "\n");
				} else {
					showBoard.append(UN + "@ " + message + "\n");
				}

			} else {
				if (showBoard.getText() == null) {
					showBoard.setText(message + "\n");
				} else {
					showBoard.append(message + "\n");
				}
			}
		}
	}

	/**
	 * display opponent move
	 * 
	 * @param row
	 * @param col
	 */
	public static void updateGamePlay(int row, int col, int check, int drawC)
	{

		try {

			if (myTurn == 1) {
				gameButtonState(2);
				listOfButton.get(row).get(col).setIcon(new ImageIcon(
						ImageIO.read(new FileInputStream(new File("res/o-button.png"))).getScaledInstance(25, 25, 10)));

				listOfButton.get(row).get(col).setEnabled(false);
			} else if (myTurn == 2) {
				gameButtonState(2);
				listOfButton.get(row).get(col).setIcon(new ImageIcon(
						ImageIO.read(new FileInputStream(new File("res/x-button.png"))).getScaledInstance(25, 25, 10)));
				listOfButton.get(row).get(col).setEnabled(false);

			}
			mainNotice.setText("YOUR MOVE");

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "can't display");
			e.printStackTrace();
		}
		if (drawC == 1119) {
			mainNotice.setText("DRAW");

			JOptionPane.showMessageDialog(null, "DRAW, press reset button to play again");
			gameButtonState(1);

		}
		if (check == 1) {
			mainNotice.setText("YOU ARE LOSE");
			JOptionPane.showMessageDialog(null, "you need to press reset button to play again");
			gameButtonState(1);

		}

	}

	/**
	 * reset the game if the opponent click the reset button
	 * 
	 * @param r text message
	 */
	public static void updateReset(String r)
	{

		JOptionPane.showMessageDialog(null, "Game is reset by your friend\n");
		reLoadUbList();
		if (myTurn == 1) {
			gameButtonState(2);
			mainNotice.setText("YOUR MOVE");

		} else if (myTurn == 2) {
			gameButtonState(1);
			mainNotice.setText("GAME IS RESET! WAIT FOR YOUR OPPONENT MOVE");
		}

	}

	/**
	 * set who play first who play after
	 * 
	 * @param turn player turn
	 */
	public static void setGameTurn(String turn)
	{
		String t = turn;
		if (t.equals("111")) {
			gameButtonState(2);
			myTurn = 1;
			mainNotice.setText("YOU GO FIRST");
		} else if (t.equals("222")) {
			myTurn = 2;
			gameButtonState(1);
		}
	}

	/**
	 * set the state of buttons in game board (on/off)
	 * 
	 * @param sw is considered as a switch which turn on/of the game board
	 */
	private static void gameButtonState(int sw)
	{
		if (sw == 1) {
			for (int i = 0; i < ROW_SIZE; i++) {

				for (int a = 0; a < COL_SIZE; a++) {

					listOfButton.get(i).get(a).setEnabled(false);
					listOfButton.get(i).get(a).setForeground(Color.green);
					System.out.println("diable button");

				}

			}
		} else if (sw == 2) {
			for (int i = 0; i < ROW_SIZE; i++) {

				for (int a = 0; a < COL_SIZE; a++) {
					if (listOfButton.get(i).get(a).getIcon() == null) {
						listOfButton.get(i).get(a).setEnabled(true);
					}
				}

			}
		}
	}

	/**
	 * notice the player that he/she won the game
	 * 
	 * @param a the number to verify winner check is true
	 */
	public static void winner(int a)
	{
		if (a == 1) {
			mainNotice.setText("YOU WIN THE GAME");
			JOptionPane.showMessageDialog(null, "To Play Again Press RESET BUTTON");
			gameButtonState(1);

		}
	}

	/**
	 * reload the the list of buttons in the game board
	 * 
	 */
	private static void reLoadUbList()
	{
		for (int i = 0; i < ROW_SIZE; i++) {

			for (int a = 0; a < COL_SIZE; a++) {
				listOfButton.get(i).get(a).setIcon(null);

			}
		}
	}

	/**
	 * notice game draw
	 * 
	 * @param a number
	 */
	public static void setDraw(int a)
	{

		mainNotice.setText("DRAW");

		JOptionPane.showMessageDialog(null, "DRAW, press reset button to play again");
		gameButtonState(1);
	}

}
