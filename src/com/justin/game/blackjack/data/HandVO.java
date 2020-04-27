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
	 * @return Number of cards in hand
	 */
	public int numOfCardsInHand() {
		return hand.size();
	}
	
	/**
	 * Empties the hand Array
	 */
	public void clearHand() {
	hand.clear();
	}
}
