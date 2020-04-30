package com.justin.game.blackjack.data;

// Importing ArrayList from java.util for the values array
import java.util.ArrayList;

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
	private ArrayList<Integer> values = new ArrayList<Integer>();
	private boolean visible = true;

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the suit
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * @param suit the suit to set
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String describeCard() {
		return label + " of " + suit;
	}

	/**
	 * @return the values
	 */
	public ArrayList<Integer> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(ArrayList<Integer> values) {
		this.values = values;
	}

}
