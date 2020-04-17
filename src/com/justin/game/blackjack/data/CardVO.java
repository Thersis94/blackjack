package com.justin.game.blackjack.data;

/****************************************************************************
 * <b>Title</b>: Card.java <b>Project</b>: blackjack <b>Description: </b> Card
 * class that will hold the values for individual cards <b>Copyright:</b>
 * Copyright (c) 2020 <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 15, 2020
 * @updates:
 ****************************************************************************/
public class CardVO {

	private String label;
	private String suit;
	private int value;

	/**
	 * Method for setting the label variable
	 * 
	 * @param Recieves the Label for the label variable as a String
	 */
	public void setLabel(String newLabel) {
		label = newLabel;
	}

	/**
	 * Method for setting the suit variable
	 * 
	 * @param Recieves the suit for the suit variable as a String
	 */
	public void setSuit(String newSuit) {
		suit = newSuit;
	}

	/**
	 * Method for setting the cardValue variable
	 * 
	 * @param Recieves the cardValue for the value variable as a int
	 */
	public void setValue(int cardValue) {
		value = cardValue;
	}

	/**
	 * Method for returning the label of the card
	 * 
	 * @return the label of the card as a String
	 */
	public String returnlabel() {

		// return the label of the card
		return label;
	}

	/**
	 * Method for returning the suit of the card
	 * 
	 * @return the suit of the card as a String
	 */
	public String returnSuit() {

		// return the suit of the card
		return suit;
	}

	/**
	 * Method for returning the value of the card
	 * 
	 * @return the value of the card as a int
	 */
	public int returnValue() {

		// return the value of the card
		return value;
	}

	/**
	 * Method for creating and returning a String that describes the card
	 * 
	 * @return a string with the label and the suit of the card
	 */
	public String returnInfoString() {
		return label + " of " + suit;
	}
}
