package com.justin.game.blackjack.data;

import java.util.ArrayList;

/****************************************************************************
 * <b>Title</b>: Hand.java <b>Project</b>: blackjack <b>Description: </b> Hand
 * class is a class for holding a collection of cards that belong to a player
 * instance <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 15, 2020
 * @updates:
 ****************************************************************************/
public class HandVO {

	private ArrayList<CardVO> hand = new ArrayList<CardVO>();

	/**
	 * Method for returning the infoString of a single Card object
	 * 
	 * @return a string describing the variable of a single card object
	 */
	public String displayCard() {
		String cardInfoString = hand.get(0).returnInfoString();
		return cardInfoString;
	}

	/**
	 * Method for returning the infoString of multiple Card objects
	 * 
	 * @return a string describing the variables of two or more card objects
	 */
	public String displayCards() {
		String cardLabelList = hand.get(0).returnInfoString();
		// Return a list of the Card Labels that are in the players hand
		for (int i = 1; i < hand.size(); i++) {
			if (i + 1 == hand.size()) {
				cardLabelList = cardLabelList + "\n" + hand.get(i).returnInfoString();
			} else {
				cardLabelList = cardLabelList + "\n" + hand.get(i).returnInfoString();
			}
		}
		return cardLabelList;
	}

	/**
	 * Return the value of a hand object
	 * 
	 * @return the value of a hand object as a int
	 */
	public int handValue() {
		int currentHandValue = 0;
		int aceCount = 0;

		// Loop through all cards in hand
		for (int i = 0; i < hand.size(); i++) {
			currentHandValue += hand.get(i).returnValue();// Add the current card to the currentHandValue variable
			// If the card is a ace increase the ace counter
			if (hand.get(i).returnValue() == 1) {
				aceCount++;
			}
		}
		// While the ace count is greater than 0 and the currentHandValue plus 10 is
		// less than or equal to 21
		while (aceCount > 0 && currentHandValue + 10 <= 21) {
			currentHandValue += 10;// Add 10 to the currentHandValue
			aceCount--;// Reduce the ace count by one
		}
		return currentHandValue;// return the currentHandValue
	}

	/**
	 * Method for displaying the number of cards in a hand object
	 * 
	 * @return the number of cards in a hand object as an integer
	 */
	public int numOfCardsInHand() {
		return hand.size();
	}

	/**
	 * Method for adding a card to the hand ArrayList
	 * 
	 * @param Recieves a Card object to add to the hand ArrayList
	 */
	public void addCardToHand(CardVO newCard) {

		// Add new card to the 'hand' array list
		hand.add(newCard);
	}

	/**
	 * Method for returning and removing a card from the hand ArrayList
	 * 
	 * @return a card object from the top of the hand
	 */
	public CardVO returnCard() {
		CardVO cardToReturn = hand.get(0);
		hand.remove(0);
		return cardToReturn;
	}
}
