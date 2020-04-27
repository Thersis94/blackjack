package com.justin.game.blackjack.managers;

import java.util.ArrayList;
import java.util.Collections;

import com.justin.game.blackjack.data.AccountVO;
import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.DealerVO;
import com.justin.game.blackjack.data.PlayerVO;

/****************************************************************************
 * <b>Title</b>: DealerPlayerManager.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> CHANGE ME!!
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class DealerPlayerManager extends PlayerManager {
	
	private DealerVO dealer = new DealerVO();

	/**
	 * @param player
	 */
	public DealerPlayerManager(PlayerVO player) {
		super(player);
	}
	
	/**
	 * When a bet is made by a player the dealer will match the bet
	 * @param bet amount
	 * @return The matched bet amount as an int
	 */
	public int matchBet(int bet) {
		AccountVO dealerAccount = dealer.getAccount();
		int dealerAccountAfterBet = dealerAccount.getChips();
		dealerAccount.setChips(dealerAccountAfterBet);
		return bet;
	}
	
	/**
	 * Randomize the order of the CardVOs in the deck
	 */
	public void shuffleDeck() {
		
		//get the deck
		ArrayList<CardVO> deck = dealer.getDeck();
		
		//shuffle the deck
		Collections.shuffle(deck);
		
		//set the deck
		dealer.setDeck(deck);
	}
	
	public CardVO dealCard() {
		CardVO card = dealer.getDeck().get(0);
		ArrayList<CardVO> newDeck = dealer.getDeck();
		newDeck.remove(0);
		dealer.setDeck(newDeck);
		return card;
	}
	
	public int placeBet() {
		
		//Dealer override for placing bets always return 0
		
		return 0;
	}
	
	public boolean requestAnotherCard() {
		
		while(dealer.getBestPossibleHand()<17) {
			return true;
		}

		return false;
	}
	
	public void returnCardsToDeck(ArrayList<CardVO> cards) {
		for(CardVO card : cards) {
			ArrayList<CardVO> deck = dealer.getDeck();
			deck.add(card);
			dealer.setDeck(deck);
		}
	}

}
