package com.justin.game.blackjack;
/****************************************************************************
 * <b>Title</b>: Card.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> Card class that will hold the values for individual cards
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 15, 2020
 * @updates:
 ****************************************************************************/
public class Card {

	private String label;
	private String suit;
	private int value;
	
	public void setLabel(String newLabel) {
		label = newLabel;
	}
	
	public void setSuit(String newSuit) {
		suit = newSuit;
	}

	public void setValue(int cardValue) {
		value = cardValue;
	}
	
	public String returnlabel() {
		
		//return the label of the card
		return label;
	}
	
	
	public String returnSuit() {
		
		//return the suit of the card
		return suit;
	}
	
	
	public int returnValue() {
		
		//return the value of the card
		return value;
	}
	
	public String returnInfoString() {
		return label + " of " + suit;
	}
}
