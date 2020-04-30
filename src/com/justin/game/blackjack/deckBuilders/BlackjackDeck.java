package com.justin.game.blackjack.deckBuilders;

//Importing ArrayList from java.util for the deck ArrayList
import java.util.ArrayList;

/****************************************************************************
 * <b>Title</b>: BlackjackDeck.java <b>Project</b>: blackjack <b>Description: </b>
 * BlackjackDeck is an object that will create an ArrayList that contains cards with the standard values used to player blackjack <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 17, 2020
 * @updates:
 ****************************************************************************/


import com.justin.game.blackjack.data.CardVO;

public class BlackjackDeck {

	private ArrayList<CardVO> deck = new ArrayList<CardVO>();

	/**
	 * Enum that stores the labels and the values of cards for the game of blackjack
	 * 
	 * @author justinjeffrey
	 *
	 */
	private enum Value {
		Ace(1, 11), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(10),
		Queen(10), King(10);

		private ArrayList<Integer> value;

		Value(Integer... i) {
			ArrayList<Integer> possibleValues = new ArrayList<Integer>();
			for (int z : i) {
				possibleValues.add(z);
			}
			this.value = possibleValues;
		}

		/**
		 * @return the value
		 */
		public ArrayList<Integer> getValue() {
			return value;
		}

	}

	private enum Suit {
		Clubs, Spades, Hearts, Diamonds;
	}

	/**
	 * Build then return the deck
	 * 
	 * @return the deck
	 */
	public ArrayList<CardVO> getDeck() {
		// Build the deck
		for (Suit s : Suit.values()) {
			for (Value v : Value.values()) {
				CardVO card = new CardVO();
				card.setSuit(s.toString());
				card.setLabel(v.toString());
				card.setValues(v.getValue());
				deck.add(card);
			}
		}
		// Return the deck
		return deck;
	}
}
