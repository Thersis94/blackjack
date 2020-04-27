package com.justin.game.blackjack.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.DealerVO;
import com.justin.game.blackjack.data.MessagesVO;
import com.justin.game.blackjack.data.PlayerVO;
import com.justin.game.blackjack.deckBuilders.StandardDeck;
import com.justin.game.blackjack.managers.DealerPlayerManager;
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

	private List<PlayerVO> players;
	private DealerVO dealer = new DealerVO();
	private MessagesVO messages = new MessagesVO();
	UserInterface ui = new UserInterface();
	private Game21Manager gm = new Game21Manager(players, dealer);

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
		
		//Create dealer
		dealer.setUserName("dealer");
		
		//Get standard deck and give it to the dealer
		StandardDeck standardDeck = new StandardDeck();
		ArrayList<CardVO> deck = standardDeck.buildStandardDeck();
		dealer.setDeck(deck);
		players.add(dealer);
		
		// Add Players
		while(UI.addNewPlayerMessage) {
			PlayerCreator newPlayer = new PlayerCreator();
			PlayerVO player = newPlayer.createNewPlayer();
			players.add(player);
		}
	}

	/**
	 * Sequences the game play
	 */
	private void startGame() {

		while(UI.wouldYouLikeToPlayMessage) {
		// Deal cards
		gm.dealCards();

		// Run the game play logic for each player
		gm.playGame();

		// Calculate Winners
		gm.calculateWinners();
		
		//Players return their cards to the dealers deck
		gm.returnHandsToDeck();
		}
	}
}