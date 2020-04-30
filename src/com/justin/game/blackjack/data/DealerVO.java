package com.justin.game.blackjack.data;

//Importing ArrayList from java.util for the deck Array
import java.util.ArrayList;

/****************************************************************************
 * <b>Title</b>: Dealer.java <b>Project</b>: blackjack <b>Description: </b> The
 * DealerVO is a object that is used to store and interact with the dealers deck
 * object<b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class DealerVO extends PlayerVO {

	private ArrayList<CardVO> deck = new ArrayList<CardVO>();

	/**
	 * @return the deck
	 */
	public ArrayList<CardVO> getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(ArrayList<CardVO> deck) {
		this.deck = deck;
	}
}
