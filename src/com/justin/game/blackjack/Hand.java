package com.justin.game.blackjack;

import java.util.ArrayList;

/****************************************************************************
 * <b>Title</b>: Hand.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> Hand class is a class for holding a collection of cards that belong to a palayer instance
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 15, 2020
 * @updates:
 ****************************************************************************/
public class Hand {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public String displayCards() {
		String cardLabelList = hand.get(0).returnlabel();
		//Return a list of the Card Labels that are in the players hand
		for(int i=1;i<hand.size();i++) {
			cardLabelList = cardLabelList + ", " + hand.get(i).returnlabel();
		}
		return cardLabelList;
	}
	
	public int handValue() {
		
		//Calculate the highest possible value without going over 21 and return it
		
		return -1;
	}
	
	public int numOfCardsInHand() {
		System.out.println("numOfCardsInHand Hand Object");
		return hand.size();
	}
	
	public void addCardToHand(Card newCard) {
		
		//Add new card to the 'hand' array list
		hand.add(newCard);
	}
}
