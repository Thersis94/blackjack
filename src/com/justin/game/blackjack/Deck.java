package com.justin.game.blackjack;

import java.util.ArrayList;
import java.util.Collections;

/****************************************************************************
 * <b>Title</b>: Deck.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> Deck class that will hold and handle methods for the creation and transfer of card objects
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 14, 2020
 * @updates:
 ****************************************************************************/
public class Deck {
	
	private ArrayList<Card> deck = new ArrayList<Card>();//ArrayList for storing ever Card object
	
	/**
	 * Method for shuffling the order of the deck ArrayList
	 */
	public void shuffleDeck() {
		
		//randomize the deck ArrayList
		Collections.shuffle(deck);
	}

	/**
	 * Method for dealing a single card from the top of the deck
	 * @return a Card object from the deck
	 */
	public Card dealCard() {
		
		Card dealtCard = deck.get(0);//Store the top Card from the deck
		deck.remove(0);//Remove the top Card from the deck
		
		//Return the stored Card object
		return dealtCard;
	}
	
	/**
	 * Method for creating all of the Cards that will be stored in the deck
	 */
	public void createNewDeck() {
		
		String[] suits = {"clubs", "diamonds", "hearts", "spades"};//String array of the card suits
		String[] labels = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king",};//String array of the card labels
		int[] cardValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10 , 10 };//Integer array of the card values
		
		//Iterate for each card in a deck
		for(int c = 0; c<52; c++) {
			//Iterate through the suits array
			for(int s = 0; s<suits.length;s++) {
				//Iterate through the labels array
				for(int l = 0; l<labels.length;l++) {
					Card card = new Card();//Create new Card instance
					card.setSuit(suits[s]);//Set suits
					card.setLabel(labels[l]);//Set labels
					card.setValue(cardValues[l]);//Set values
					//Add the Card object to the deck ArrayList
					deck.add(card);
				}
			}
		}
	}
	
	/**
	 * Method for adding a card object to the deck ArrayList
	 * @param Recieves a Card object to add to the deck
	 */
	public void addCardToDeck(Card cardToAdd) {
		deck.add(cardToAdd);
	}
}
