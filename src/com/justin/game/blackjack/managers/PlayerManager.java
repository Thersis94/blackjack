package com.justin.game.blackjack.managers;

//Importing ArrayList from java.util to handle the hand ArrayList
import java.util.ArrayList;

import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.DealerVO;
import com.justin.game.blackjack.data.HandVO;
import com.justin.game.blackjack.data.MessagesVO;
import com.justin.game.blackjack.data.PlayerVO;
import com.justin.game.blackjack.process.UserInterface;

/****************************************************************************
 * <b>Title</b>: PlayerManager.java <b>Project</b>: blackjack <b>Description:
 * </b> CHANGE ME!! <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class PlayerManager {

	private MessagesVO messages = new MessagesVO();
	UserInterface ui = new UserInterface();
	private PlayerVO player;

	public PlayerManager(PlayerVO player) {
		this.player = player;
		messages.setGameMessages();
	}

	/**
	 * Displays a users current account balance, prompts the user for a bet amount,
	 * check that they have enough funds, then return the value of the bet
	 */
	public int placeBet(DealerVO dealer) {
		
		int betAmount = 0;
		
		Object[] gameStateInfo = {player.getUserName(), player.getHand().toString(), dealer.getHand().toString() };

		ui.display(messages.returnMessage("currentPlayerHand"), false, gameStateInfo);
		
		// get current balance and display
		if(ui.display(messages.returnMessage("wouldYouLikeToBet"), true, player.getAccount().getChips()).equalsIgnoreCase("yes")) {
			// Ask the user how much they would like to bet
			betAmount = Integer.parseInt(ui.display(messages.returnMessage("betAmount"), true));
			// Validate that they have the funds in their account
			if(betAmount > player.getAccount().getChips()) {
				do {
					// display message to user if they can't afford it
					ui.display(messages.returnMessage("notEnoughFunds"), false);
				} while(Integer.parseInt(ui.display(messages.returnMessage("betAmount"), true)) > player.getAccount().getChips());
			} else {
				// Reduce playerVO account balance by that amount and return to dealer for pot
				int accountAfterBet = player.getAccount().getChips() - betAmount;
				player.getAccount().setChips(accountAfterBet);
			}
		}
		return betAmount;
	}

	/**
	 * Prompt user for another card and return their response as a boolean
	 * 
	 * @return boolean representing the players response
	 */
	public boolean requestAnotherCard(DealerVO dealer) {
		
		Object[] gameStateInfo = {player.getUserName(), player.getHand().toString(), dealer.getHand().toString() };
		ui.display(messages.returnMessage("currentPlayerHand"), false, gameStateInfo);

		// prompt user for choice
		String playerChoice = ui.display(messages.returnMessage("getAnotherCard"), true);

		if (playerChoice.equalsIgnoreCase("no")) {
			player.setStatus("staying");
			return false;
		}
		return true;
	}

	/**
	 * Receives an Integer representing the amount to increase the players account
	 * 
	 * @param amount to increase the players accounts
	 */
	public void collectWinnings(int amount) {

		// Increase playerVO account by amount
		player.getAccount().setChips(player.getAccount().getChips() + amount);
	}

	/**
	 * Adds a new card to the players HandVO
	 * 
	 * @param newCard that is added to the players hand
	 */
	public void getNewCard(CardVO newCard) {

		// Update the players hand
		ArrayList<CardVO> playersHand = player.getHand().getHand();

		// Add the new card
		playersHand.add(newCard);

		// Set the players new hand
		player.getHand().setHand(playersHand);
		
		// Check for a bust
		if(player.getHand().getPossibleHandValues().size() == 0)
			player.setStatus("bust");
	}

	/**
	 * returns the players hand and empties the player HandVO
	 * 
	 * @return the players hand
	 */
	public ArrayList<CardVO> returnHand() {
		HandVO hand = player.getHand();
		ArrayList<CardVO> cards = hand.getHand();
		hand.clearHand();
		return cards;
	}
	
	public ArrayList<Integer> getPossibleValues() {
		return player.getHand().getPossibleHandValues();
	}

	public void roundEndMessage(int winnings, DealerVO dealer) {
		Object[] gameStateInfo = {player.getUserName(), player.getHand().toString(), dealer.getHand().toString() };
		ui.display(messages.returnMessage("currentPlayerHand"), false, gameStateInfo);
		switch(player.getStatus()) {
		case "won":
			ui.display(messages.returnMessage("gameWon"), false, winnings);
			break;
		case "tie":
			ui.display(messages.returnMessage("gameTied"), false);
			break;
		case "lost":
			ui.display(messages.returnMessage("gameLost"), false);
			break;
		}
	}
}