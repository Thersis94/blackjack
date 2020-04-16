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
	
//	private Card card[] = new Card[52];
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	
	public void shuffleDeck() {
		
		//randomize the deck ArrayList
		Collections.shuffle(deck);
		
		
	}
	
	
	public Card[] dealCards() {
		
		//return array of two cards
		
		return null;
	}
	
	
	public Card dealCard() {
		
		Card dealtCard = deck.get(0);
		deck.remove(0);
		
		//return a card object
		return dealtCard;
	}
	
	public void createNewDeck() {
		
		String[] suits = {"clubs", "diamonds", "hearts", "spades"};
		
		String[] labels = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king",};
		
		int[] cardValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10 , 10 };
		
		//create all of the cards.
		for(int c = 0; c<52; c++) {
			
			for(int s = 0; s<suits.length;s++) {
				
				for(int l = 0; l<labels.length;l++) {
					Card card = new Card();
					card.setSuit(suits[s]);
					card.setLabel(labels[l]);
					card.setValue(cardValues[l]);
					
					deck.add(card);
				}
			}
		}
		
		
		
		
	}
}
