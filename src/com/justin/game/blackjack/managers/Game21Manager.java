package com.justin.game.blackjack.managers;

//Importing ArrayList from java.util for handling the PlayerVO list
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.DealerVO;
import com.justin.game.blackjack.data.PlayerVO;
import com.justin.game.blackjack.data.PotVO;

/****************************************************************************
 * <b>Title</b>: DealerManager.java <b>Project</b>: blackjack <b>Description:
 * </b> Managing class for the game of 21. Handles functionality of the game sequencing methods <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class Game21Manager {

	private List<PlayerVO> players;
	private DealerVO dealer;
	private Map<String, PotVO> gamePot;
	private DealerPlayerManager dpm;

	public Game21Manager(List<PlayerVO> players, DealerVO dealer) {
		this.players = players;
		this.dealer = dealer;
		this.gamePot = new LinkedHashMap<>();
		this.dpm = new DealerPlayerManager(dealer);
	}

	/**
	 * Deals out all of the starting cards to each player for the game of blackjack
	 */
	public void dealCards() {
		dpm.shuffleDeck();
		for (PlayerVO player : players) {
			PlayerManager pm = new PlayerManager(player);
			while (player.getHand().numOfCardsInHand() < 2) {
				if (player.isDealer() && player.getHand().numOfCardsInHand() == 0) {
					// Deal a card with visible set to false
					CardVO dealersFirstCard = dpm.dealCard();
					dealersFirstCard.setVisible(false);
					pm.getNewCard(dealersFirstCard);
				}
				pm.getNewCard(dpm.dealCard());
			}
		}

	}

	/**
	 * Iterate through players and initialize their managers and PotVO
	 */
	public void playGame() {

		// Iterate through players and initialize a PotVO for each player then run the
		// players turn
		for (PlayerVO player : players) {
			gamePot.put(player.getUserName(), new PotVO());
			PlayerManager pm = null;
			if (player.isDealer())
				pm = new DealerPlayerManager(dealer);
			else
				pm = new PlayerManager(player);

			playerTurn(player, pm);
		}
	}

	/**
	 * Run the sequence for the players turn
	 * 
	 * @param player receives the PlayerVo
	 * @param pm     receives the PlayerManager
	 */
	public void playerTurn(PlayerVO player, PlayerManager pm) {

		player.setStatus("playing");

		int bet = pm.placeBet(dealer);
		// Add bet amount to pot from player and dealer if > 0
		gamePot.get(player.getUserName()).setChips(gamePot.get(player.getUserName()).getChips() + dpm.matchBet(bet) + bet);

		while (true) {
			if (pm.requestAnotherCard(dealer)) {
				// Player is dealt another card
				pm.getNewCard(dpm.dealCard());
				// Check for a bust
				if (player.getStatus().equals("bust"))
					break;
			} else {
				break;
			}
		}
	}

	/**
	 * Iterate through all of the players and compare their hands to the dealers
	 * hand
	 */
	public void calculateWinners() {
		// Calculate winners and set their status
		for (PlayerVO player : players) {
			// Reveal all of dealers cards
			for (CardVO card : dealer.getHand().getHand()) {
				if (!card.isVisible()) {
					card.setVisible(true);
				}
			}
			// Check player status
			if (player.getStatus().equals("bust")) {
				player.setStatus("lost");
			} else if (player.getStatus().equals("staying") && dealer.getStatus().equals("bust")) {
				player.setStatus("won");
			} else {
				// If both the player and the dealer have viable hands compare them
				if (Collections.max(player.getHand().getPossibleHandValues()) > Collections
						.max(dealer.getHand().getPossibleHandValues())) {
					player.setStatus("won");
				} else if (Collections.max(player.getHand().getPossibleHandValues()) == Collections
						.max(dealer.getHand().getPossibleHandValues())) {
					player.setStatus("tie");
				} else {
					player.setStatus("lost");
				}
					
			}
		}
	}

	/**
	 * Transfers the value of the pot to the applicable players/dealer
	 */
	public void payOutWinners() {
		for (PlayerVO player : players) {
			// Pay out winnings
			PlayerManager pm = null;
			if (player.isDealer()) {
				break;
			}else {
				pm = new PlayerManager(player);
			}
			int winnings = 0;
			if (player.getStatus().equals("won")) {
				winnings = gamePot.get(player.getUserName()).getChips();
				pm.collectWinnings(gamePot.get(player.getUserName()).getChips());
			} else if (player.getStatus().equals("tie")) {
				pm.collectWinnings(gamePot.get(player.getUserName()).getChips() / 2);// Pay out half to player
				gamePot.get(player.getUserName()).setChips(gamePot.get(player.getUserName()).getChips() / 2);
				dpm.collectWinnings(gamePot.get(player.getUserName()).getChips());// Pay out the rest to the dealer
			} else {
				dpm.collectWinnings(gamePot.get(player.getUserName()).getChips());// Pay out the pot to dealer
			}
			gamePot.get(player.getUserName()).setChips(0); // Set pot to zero
			pm.roundEndMessage(winnings, dealer);
		}
	}

	/**
	 * Iterates through all of the players and returns their cards to the deck
	 */
	public void returnHandsToDeck() {
		ArrayList<CardVO> cardsToReturn = new ArrayList<CardVO>();

		for (PlayerVO player : players) {
			PlayerManager pm = new PlayerManager(player);
			ArrayList<CardVO> playersCards = pm.returnHand();
			for (CardVO card : playersCards) {
				cardsToReturn.add(card);
			}
		}
		dpm.returnCardsToDeck(cardsToReturn);
	}
}