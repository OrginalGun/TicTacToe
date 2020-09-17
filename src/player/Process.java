/**
 * 
 */
package player;

import java.io.Serializable;

/**
 * use to contain the move of one player 
 * @author 783901
 *
 */
public class Process implements Serializable
{

	private static final long serialVersionUID = -1053289235615585443L;
	int row;
	int col;
	int winner = 0;
	int draws = 0;
	String resetgame;

	/**
	 * set the value for row, column and winner 
	 * @param r row
	 * @param c column 
	 * @param check winner 
	 */
	public Process(int r, int c, int check)
	{
		row = r;
		col = c;
		winner = check;
	}
	/**
	 * set the value for row, column and winner and checkDraw number
	 * @param r rows 
	 * @param c columns 
	 * @param check winner 
	 * @param checkDraw draw number
	 */
	public Process(int r, int c, int check, int checkDraw)
	{
		row = r;
		col = c;
		winner = check;
		draws = checkDraw;
	}

	/*
	 * return  row, column, winner number and drawCheck number as String 
	 * and separate these variables by a space
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return row + " " + col + " " + winner + " " + draws;
	}
}
