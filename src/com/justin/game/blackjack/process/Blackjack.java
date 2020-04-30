package com.justin.game.blackjack.process;

//Importing ArrayList from java.util for the players Array
import java.util.ArrayList;

import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.DealerVO;
import com.justin.game.blackjack.data.MessagesVO;
import com.justin.game.blackjack.data.PlayerVO;
import com.justin.game.blackjack.deckBuilders.BlackjackDeck;
import com.justin.game.blackjack.managers.Game21Manager;
import com.justin.game.blackjack.managers.PlayerCreator;

/****************************************************************************
 * <b>Title</b>: Blackjack.java <b>Project</b>: blackjack <b>Description: </b> A
 * basic setup for command line blackjack/21. Developed by Justin Jeffrey as a
 * practice exercise to develop skills in building basic applications from the
 * ground up. <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 14, 2020
 * @updates:
 ****************************************************************************/
public class Blackjack {

	private ArrayList<PlayerVO> players = new ArrayList<>();
	private DealerVO dealer = new DealerVO();
	private MessagesVO messages = new MessagesVO();
	UserInterface ui = new UserInterface();

	/**
	 * Main method called to allow command line processing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Initializing new blackjack object
		Blackjack bj = new Blackjack();
		bj.process();

	}

	/**
	 * Sequences the configuration and initialization of objects and messages needed
	 * to run the game
	 */
	public void process() {

		// Configure application with starting variables and messages
		appConfig();

		// Begin the game sequence
		startGame();

	}

	/**
	 * Runs methods to initialize the game messages and to create a instance of the
	 * dealer
	 */
	private void appConfig() {

		// Run messages setGameMessages method to setup game messages
		messages.setGameMessages();

		// Add Players
		while (ui.display(messages.returnMessage("newPlayerOption"), true).equalsIgnoreCase("yes")) {
			PlayerCreator newPlayer = new PlayerCreator();
			PlayerVO player = newPlayer.createNewPlayer();
			players.add(player);
		}

		// Create dealer
		dealer.setDealer(true);
		dealer.setUserName("dealer");
		// Get standard deck and give it to the dealer
		BlackjackDeck blackjackDeck = new BlackjackDeck();
		ArrayList<CardVO> deck = blackjackDeck.getDeck();
		dealer.setDeck(deck);
		players.add(dealer);
	}

	/**
	 * Sequences the game play
	 */
	private void startGame() {

		while (ui.display(messages.returnMessage("startANewRound"), true).equalsIgnoreCase("yes")) {

			Game21Manager gm = new Game21Manager(players, dealer);

			// Deal cards
			gm.dealCards();

			// Run the game play logic for each player
			gm.playGame();

			// Calculate Winners
			gm.calculateWinners();

			// Pay out Winners
			gm.payOutWinners();

			// Players return their cards to the dealers deck
			gm.returnHandsToDeck();
		}
	}
}