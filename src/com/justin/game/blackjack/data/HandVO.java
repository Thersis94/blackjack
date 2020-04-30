package com.justin.game.blackjack.data;

//Importing ArrayList from java.util for the hand Array
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
	 * @return the hand
	 */
	public ArrayList<CardVO> getHand() {
		return hand;
	}

	/**
	 * @param hand the hand to set
	 */
	public void setHand(ArrayList<CardVO> hand) {
		this.hand = hand;
	}

	/**
	 * Return the number of cards in the hand
	 * 
	 * @return Number of cards in hand
	 */
	public int numOfCardsInHand() {
		return hand.size();
	}

	/**
	 * HandVO toString method is a method that returns a String describing the
	 * CardVO objects stored in the hand
	 * 
	 * @return String the lists the labels of the cards in the hand object
	 */
	public String toString() {
		String handLabels = "";
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).isVisible()) {
				handLabels = handLabels + "\n" + hand.get(i).describeCard();
			}
		}
		return handLabels + "\n";
	}

	/**
	 * Empties the hand Array
	 */
	public void clearHand() {
		hand.clear();
	}

	/**
	 * @return the possibleHandValues as an array list
	 */
	public ArrayList<Integer> getPossibleHandValues() {

		ArrayList<Integer> possibleHandValues = new ArrayList<Integer>();

		possibleHandValues = hand.get(0).getValues();

		// Calculate all possible hand values
		for (int i = 1; i < hand.size(); i++) {
			ArrayList<Integer> newPossibleValues = new ArrayList<Integer>();
			for (int phv : possibleHandValues) {
				for (int pcv : hand.get(i).getValues()) {
					// Add every possible hand value to the array if it is under 22
					if (phv + pcv < 22)
						newPossibleValues.add(phv + pcv);
				}
				// Overwrite the possibleHandValues with the new values
				possibleHandValues = newPossibleValues;
			}
		}
		return possibleHandValues;
	}
}