/**
 * 
 */
package Server;

import java.io.Serializable;

/**
 * Called when the Server want to communicate with Players 
 * @author Cuong Nguyen 783901
 *
 */
public class Notice implements Serializable
{
	
	private static final long serialVersionUID = -1869561192298415572L;
	int turn;
	/**
	 * set the player turn 
	 * @param i player turn
	 */
	public Notice(int i)
	{
		turn = i;
	}

	/** 
	 * return the message 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String notice = Integer.toString(turn);
		return notice;
	}
}
