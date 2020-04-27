package com.justin.game.blackjack.data;

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
	private ArrayList<Integer> possibleValues = new ArrayList<Integer>();
	private String visible;
	
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
	 * @return the possibleValues
	 */
	public ArrayList<Integer> getPossibleValues() {
		return possibleValues;
	}
	/**
	 * @param possibleValues the possibleValues to set
	 */
	public void setPossibleValues(ArrayList<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}
	/**
	 * @return the visible
	 */
	public String getVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(String visible) {
		this.visible = visible;
	}
}
