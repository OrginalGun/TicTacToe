/**
 * 
 */
package player;

import java.io.Serializable;

/**
 * Is call players want to communicate with Server 
 * 
 * @author 783901
 *
 */
public class ServerCommunication implements Serializable
{

	private static final long serialVersionUID = 2273182337142318085L;
	String connectNotice;

	/**
	 * set the connectNotice message 
	 * @param in message
	 */
	public ServerCommunication(String in)
	{
		connectNotice = in;
	}

	/*
	 * Return the message
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return connectNotice;
	}

}
