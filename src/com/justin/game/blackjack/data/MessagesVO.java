package com.justin.game.blackjack.data;

import java.util.HashMap;

/****************************************************************************
 * <b>Title</b>: Messages.java <b>Project</b>: blackjack <b>Description: </b>
 * Messages VO that contains a collection of game messages and a method for
 * returning them <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 14, 2020
 * @updates:
 ****************************************************************************/
public class MessagesVO {

	// HashMap for storing game messages
	HashMap<String, String> messages = new HashMap<>();

	/**
	 * Method that stores the game messages in the messages HashMap
	 */
	public void setGameMessages() {

		messages.put("tempMessage", "Hello, this is a test message.");
		messages.put("newPlayerOption", "Would you like to add another player to the game? 'yes' or 'no'.");
		messages.put("newPlayer", "Please enter your name");
		messages.put("youHaveBeenDealt", " you have been dealt \n \n");
		messages.put("dealerHasBeenDealt", " has been dealt ");
		messages.put("dealersFirstCard", "\nThe dealer is showing a \n");
		messages.put("playerGameChoice", "\nWould you like to 'raise', 'hit' or 'stay'?");
		messages.put("errorInputNotFound", "I'm sorry but we have no options that match your input. Please try again.");
		messages.put("bankBalance", "You currently have ");
		messages.put("betAmount", "How much would you like to bet?");
		messages.put("notEnoughFunds", "You do not have enough chips to place that bet.");
		messages.put("currentValueOfPot", "The current value of the pot is now ");
		messages.put("bust", "Bust!");
		messages.put("exitOption", "Type 'continue' to continue playing or type 'exit' to quit.");
		messages.put("tie", "That is a push.");
		messages.put("win", "You won!");
		messages.put("lost", "You lost!");
		messages.put("youWon", "You won ");
		messages.put("bracket", "-----------------------------------------------------------------------");
	}

	/**
	 * Method used to abstract message HashMap.
	 * 
	 * @return string containing requested message as a string
	 */
	public String returnMessage(String requestedMessageName) {

		// Pull requested message from the HashMap and return it
		String requestedMessageContent = messages.get(requestedMessageName);
		return requestedMessageContent;
	}
}
