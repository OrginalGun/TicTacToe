/**
 * 
 */
package player;

import java.io.Serializable;

/**
 * Hold the reset request
 * @author Cuong Nguyen 783901
 *
 */
public class resetMessage implements Serializable
{
	
	private static final long serialVersionUID = -3617161236409498368L;
	String resetRequest;
	/**
	 * set reset request command 
	 * @param a
	 */
	public resetMessage(String a)
	{
		resetRequest = a;
	}
	/* return reset request as a String
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return resetRequest;
	}
	
	
}
