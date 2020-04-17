package com.justin.game.blackjack.process;

import java.util.Scanner;

/****************************************************************************
 * <b>Title</b>: UserInterface.java <b>Project</b>: blackjack <b>Description:
 * </b> The UserInterface class is used to display text and receive input from a
 * user. <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 15, 2020
 * @updates:
 ****************************************************************************/
public class UserInterface {

	private Scanner userInput = new Scanner(System.in);

	/**
	 * Method for closing the Scanner input stream. Only use when exiting the game!
	 */
	public void closeScanner() {
		// Close the Scanner input stream
		userInput.close();
	}

	/**
	 * Handles the displaying of messages to the user and the collection of user
	 * input
	 * 
	 * @param message          This param contains a String the will be displayed to
	 *                         the user
	 * @param userInputRequest This param determines whether or not to run the
	 *                         collectUserInput method
	 * @return If userInputRequest is true this will return the values entered by
	 *         the user
	 */
	public String display(String message, boolean userInputRequest) {
		// Display message to user
		System.out.println(message);

		// If user input is requested then run collectUserInput
		if (userInputRequest) {
			// capture the return from collectUserInput
			return collectUserInput();
		} else {
			return null;
		}
	}

	/**
	 * Handles collecting information from the user
	 * 
	 * @return This method returns a String containing the user input
	 */
	private String collectUserInput() {
		// Run user input method. Scanner Class will return a string. Parse it as an
		// integer where needed
		String inputLine = userInput.nextLine();
		return inputLine;

	}
}
