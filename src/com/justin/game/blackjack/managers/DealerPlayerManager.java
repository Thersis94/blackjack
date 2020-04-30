package com.justin.game.blackjack.managers;

//Importing ArrayList from java.util for handling the deck Array
import java.util.ArrayList;
import java.util.Collections;

import com.justin.game.blackjack.data.AccountVO;
import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.DealerVO;

/****************************************************************************
 * <b>Title</b>: DealerPlayerManager.java <b>Project</b>: blackjack
 * <b>Description: </b> CHANGE ME!! <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class DealerPlayerManager extends PlayerManager {

	private DealerVO dealer = new DealerVO();

	DealerPlayerManager(DealerVO dealer) {
		super(dealer);
		this.dealer = dealer;
	}

	/**
	 * When a bet is made by a player the dealer will match the bet
	 * 
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

		// get the deck
		ArrayList<CardVO> deck = dealer.getDeck();

		// shuffle the deck
		Collections.shuffle(deck);

		// set the deck
		dealer.setDeck(deck);
	}

	/**
	 * Return a single card from the deck
	 * 
	 * @return a CardVO from the deck
	 */
	public CardVO dealCard() {
		CardVO card = dealer.getDeck().get(0);
		ArrayList<CardVO> newDeck = dealer.getDeck();
		newDeck.remove(0);
		dealer.setDeck(newDeck);
		return card;
	}

	/**
	 * Dealer Override for the place bet method.
	 * 
	 * @return dealer always returns 0
	 */
	@Override
	public int placeBet(DealerVO dealer) {
		// Dealer override for placing bets always return 0
		return 0;
	}

	/**
	 * Dealer override for the requestCard method
	 * 
	 * @return a boolean representing whether the dealer will take another card or
	 *         not
	 */
	@Override
	public boolean requestAnotherCard(DealerVO dealer) {

		int dealersBestHand = 0;
		for (int i : dealer.getHand().getPossibleHandValues()) {
			if (i > dealersBestHand) {
				dealersBestHand = i;
			}
		}

		while (dealersBestHand < 17) {
			return true;
		}
		if (dealer.getHand().getPossibleHandValues().size() == 0)
			dealer.setStatus("bust");
		dealer.setStatus("staying");
		return false;
	}

	/**
	 * Empty stub to avoid displaying message to the users that only apply to the
	 * dealer
	 */
	@Override
	public void roundEndMessage(int winnings, DealerVO dealer) {
		// Empty override for dealer message
	}

	/**
	 * Receives an ArrayList of CardVOs and adds them to DealerVO deck
	 * 
	 * @param cards
	 */
	public void returnCardsToDeck(ArrayList<CardVO> cards) {
		for (CardVO card : cards) {
			ArrayList<CardVO> deck = dealer.getDeck();
			deck.add(card);
			dealer.setDeck(deck);
		}
	}
}
