package com.justin.game.blackjack;

import java.util.HashMap;

/****************************************************************************
 * <b>Title</b>: Messages.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> Messages VO that contains a collection of game messages and a method for returning them
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 14, 2020
 * @updates:
 ****************************************************************************/
public class Messages {
	
	//HashMap for storing game messages
	HashMap<String, String> messages = new HashMap<>();
	
	/**
	 * Method that stores the game messages in the messages HashMap
	 */
	public void setGameMessages() {
		
		messages.put("tempMessage", "Hello, this is a test message.");
		messages.put("newPlayerOption", "Would you like to add another player to the game?");
		messages.put("newPlayer", "Please enter your name");
	}
	
	/**
	 * Method used to abstract message HashMap.
	 * 
	 * @return string containing requested message as a string
	 */
	public String returnMessage(String requestedMessageName) {
		
		//Pull requested message from the HashMap and return it
		String requestedMessageContent = messages.get(requestedMessageName);		
		return requestedMessageContent;
		
	}
}
