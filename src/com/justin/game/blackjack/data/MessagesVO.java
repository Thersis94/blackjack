package com.justin.game.blackjack.data;

//Importing HashMap from java.util for the messages Map
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

		messages.put("newPlayerOption", "Would you like to add another player to the game? 'yes' or 'no'\n");
		messages.put("betAmount", "How much would you like to bet?\n");
		messages.put("getAnotherCard", "Would you like another card?\n");
		messages.put("startANewRound", "Would you like to start another round?\n");
		messages.put("firstName", "Please enter your first name.\n");
		messages.put("lastName", "Please enter your last name.\n");
		messages.put("userName", "Please enter your userName.\n");
		messages.put("currentPlayerHand", "%s you have %s \nThe dealer is showing %s \n");
		messages.put("wouldYouLikeToBet", "You currently have %s chips. Would you like to raise your bet?\n");
		messages.put("notEnoughFunds", "You do not have enough chips to place that bet.\n");
		messages.put("gameWon", "You Won %s chips! Congratulations!\n");
		messages.put("gameLost", "You Lost!\n");
		messages.put("gameTied", "You Tied!\n");

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
