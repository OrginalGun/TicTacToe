/**
 * 
 */
package player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *The Message 
 * @author Cuong Nguyen 783901
 *
 */
public class Message implements Serializable
{

	private String username;
	private String content;
	/**
	 * Set username 
	 * Set Message
	 * @param name user name
	 * @param message message
	 */
	public Message(String name, String message)
	{
		this.username = name;
		this.content = message;
	}
	/**
	 * 
	 */
	public Message()
	{

	}
	/**
	 * set the content  
	 * @param a content
	 */
	public Message(String a)
	{
		content = a;
	}

	/**
	 * get the username 
	 * @return username user's name
	 */
	private String getUsername()
	{
		return username;
	}

	/**
	 * set the user name
	 * @param username the user's name 
	 */
	protected void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * get content 
	 * @return the message content 
	 */
	private String getContent()
	{
		return content;
	}

	/**
	 * set the message content 
	 * @param content the content to set
	 */
	protected void setContent(String content)
	{
		this.content = content;
	}

	/*
	 * return the user name and message 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{

		return username + "@ " + content;

	}

}
