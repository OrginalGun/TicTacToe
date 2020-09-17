/**
 * 
 */
package player;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

/**
 * use to find the winner and check the draw
 * 
 * @author Cuong Nguyen 783901
 *
 */
public class Movement
{

	private List<List<String>> gameMoveList = new ArrayList<List<String>>();
	private final int ROW_SIZE = 20;
	private final int COL_SIZE = 20;
	private String defautMove = "-";
	private String xMove = "X";
//	private String oMove = "O";
	private String winner;
	private int newrow;
	private int newcol;
	public boolean draws = false;

	/**
	 * Run the loadGameMoveList
	 */
	public Movement()
	{
		loadGameMoveList();
	}
	/**
	 * load the list which will contain a list of moves 
	 */
	private void loadGameMoveList()
	{
		int col, row;

		for (row = 0; row < ROW_SIZE; row++) {
			List<String> collumlist = new ArrayList<String>();
			for (col = 0; col < COL_SIZE; col++) {
				collumlist.add(defautMove);
			}
			gameMoveList.add(collumlist);
		}

	}

	/**
	 * Update the move to gameMoveList and check game results
	 * @param row rows 
	 * @param col columns
	 * 
	 */
	public boolean updateMove(int row, int col)
	{
		if (gameMoveList.get(row).get(col).equals(defautMove)) {
			newrow = row;
			newcol = col;
			gameMoveList.get(row).set(col, xMove);
			if (checkResult(row, col) == true) {
				return true;
			}
			if (checkDraws() == true) {
				draws = true;
			} else {
				draws = false;
			}
		}
		return false;
	}
	/**
	 * the method is used to return new row and new columns as a String, these variables
	 * are separated by a space
	 * @return new row, new columns as a String
	 */
	public String newMove()
	{
		return newrow + " " + newcol;

	}
	/**
	 * get the gameMovelist
	 * @return gameMoveList the list contains moves
	 */
	public List<List<String>> getGameMoveList()
	{
		return this.gameMoveList;
	}
	/**
	 * get the winner as a String
	 * @param a the winner number
	 * @return winner number
	 */
	public String getWinner(String a)
	{
		winner = a;
		return winner;
	}

	/**
	 *  Checking for the winner if one of the player win the game it will return true.
	 *  If not it returns false 
	 * @param row rows 
	 * @param col columns
	 * @return boolean which check for the winner
	 */
	protected boolean checkResult(int row, int col)
	{


		if (checkHorizontal(row, col) == true) {
			return true;
		} else if (checkVertical(row, col) == true) {
			return true;
		} else if (checkCross1(row, col) == true) {
			return true;
		} else if (checkCross2(row, col) == true) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * check the result in particular row of the new move 
	 * @param r rows
	 * @param c columns 
	 * @return boolean if true there is player win the game
	 */
	private boolean checkHorizontal(int r, int c)
	{
		int collum = c;
		int rows = r;
		int k = 0;

		for (int i = c; i < COL_SIZE - 1; i++) {
			collum++;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(r).get(collum))
					&& gameMoveList.get(r).get(c) != "-")
			{
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;

			}
		}
		k = 0;
		collum = c;
		for (int i = c; i > 0; i--) {
			collum--;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(r).get(collum))
					&& gameMoveList.get(r).get(c) != "-")
			{
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;

			}
		}
		return false;

	}

	/**
	 * Check the result in particular column of the new move 
	 * @param r rows 
	 * @param c columns
	 * @return boolean if true there is a player win the game 
	 */
	private boolean checkVertical(int r, int c)
	{
		int collum = c;
		int rows = r;
		int k = 0;

		for (int i = r; i < ROW_SIZE - 1; i++) {
			rows++;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(rows).get(c)) && gameMoveList.get(r).get(c) != "-") {
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;
			}
		}
		k = 0;
		rows = r;
		for (int i = r; i > 0; i--) {
			rows--;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(rows).get(c)) && gameMoveList.get(r).get(c) != "-") {
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;

			}
		}

		return false;
	}

	/**
	 * check the result in increment diagonal line of the new move 
	 * @param r rows 
	 * @param c columns 
	 * @return boolean if true there is a player win the game
	 */
	private boolean checkCross1(int r, int c)
	{
		int collum = c;
		int rows = r;
		int k = 0;

		for (int i = r, a = c; i < ROW_SIZE - 1 && a < COL_SIZE - 1; i++, a++) {
			rows++;
			collum++;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(rows).get(collum))
					&& gameMoveList.get(r).get(c) != "-")
			{
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;
			}
		}
		k = 0;
		rows = r;
		collum = c;
		for (int i = r, a = c; i != 0 && a > 0; i--, a--) {
			rows--;
			collum--;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(rows).get(collum))
					&& gameMoveList.get(r).get(c) != "-")
			{
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check the result in decrement diagonal line of the new move 
	 * @param r rows
	 * @param c columns 
	 * @return boolean if true there is a player win the game
	 */
	private boolean checkCross2(int r, int c)
	{
		int collum = c;
		int rows = r;
		int k = 0;

		for (int i = r, a = c; i < ROW_SIZE - 1 && a > 0; i++, a--) {
			rows++;
			collum--;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(rows).get(collum))
					&& gameMoveList.get(r).get(c) != "-")
			{
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;
			}
		}
		k = 0;
		rows = r;
		collum = c;
		for (int i = r, a = c; i > 0 && a < COL_SIZE - 1; i--, a++) {
			rows--;
			collum++;
			if (gameMoveList.get(r).get(c).equals(gameMoveList.get(rows).get(collum))
					&& gameMoveList.get(r).get(c) != "-")
			{
				k++;
			} else {
				k = 0;
			}
			if (k == 4) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check for draw game
	 * @return boolean draw game if true
	 */
	private boolean checkDraws()
	{
		int count = 0;
		for (int i = 0; i < ROW_SIZE; i++) {
			for (int c = 0; i < COL_SIZE; i++) {
				if (gameMoveList.get(i).get(c).equals(xMove)) {
					count++;
				}

			}
		}
		if (count == 200) {
			return true;
		} else {
			count = 0;
			return false;
		}

	}
	/**
	 * reload the list if there is a reset request 
	 */
	public void resetList()
	{
		loadGameMoveList();

	}

}
