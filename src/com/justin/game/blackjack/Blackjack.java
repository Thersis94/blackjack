package com.justin.game.blackjack;

import java.util.HashMap;

/****************************************************************************
 * <b>Title</b>: Blackjack.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> A basic setup for command line blackjack/21. Developed by Justin Jeffrey as a practice exercise to develope skills in building basic applications from the ground up.
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 14, 2020
 * @updates:
 ****************************************************************************/
public class Blackjack {
	
	HashMap<Integer, Player> playerMap = new HashMap();
	
	private int numOfPlayers = 0;
	private Messages messages = new Messages();
	Deck deck = new Deck();
	UserInterface ui = new UserInterface();

	/**
	 * Main method called to allow command line processing
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Initializing new blackjack object
		Blackjack bj = new Blackjack();
		bj.process();
		
	}
	
	/**
	 * Sequences the configuration and initialization of objects and messages needed to run the game
	 */
	public void process() {
		
		//Configure application with starting variables and messages
		appConfig();
		
		//Initialize the card deck object
		initializeDeck();
		
		//Begin the game sequence
		startGame();
		
	}		
	
	/**
	 * Runs methods to initialize the game messages and to create a instance of the dealer
	 */
	private void appConfig() {
		
		//Run messages setGameMessages method to setup game messages
		messages.setGameMessages();

		//Create player instance for Dealer
		Player dealer = new Player();
		dealer.setUserName("dealer");
		playerMap.put(0, dealer);
	}
	
	/**
	 *Runs the createNewDeck method on the deck instance to build all of the card objects that it will hold
	 */
	private void initializeDeck() {
		
		//Runs the deck method to create all of the playing card objects in the deck
		deck.createNewDeck();
	}
	
	/**
	 * 
	 */
	private void startGame() {
		
		
		//Find a better way to handle this!
		if(numOfPlayers == 0) {
			Player newPlayer = new Player();
			String newPlayerName = ui.display(messages.returnMessage("newPlayer"), true);
			newPlayer.setUserName(newPlayerName);
			numOfPlayers++;
			playerMap.put(numOfPlayers, newPlayer);
		}
		
		while(true) {
			String playerResponse = ui.display(messages.returnMessage("newPlayerOption"), true);
			if(playerResponse.equalsIgnoreCase("yes")) {
				Player newPlayer = new Player();
				String newPlayerName = ui.display(messages.returnMessage("newPlayer"), true);
				newPlayer.setUserName(newPlayerName);
				numOfPlayers++;
				playerMap.put(numOfPlayers, newPlayer);
			} if(playerResponse.equalsIgnoreCase("no")) {
				break;
			}
		}
		
		//Create a new deck
		deck.createNewDeck();
		
		//Shuffle the deck
		deck.shuffleDeck();

		//Run the game play logic for each player
		playGame();

//		for(each player in player[]) {
//			//Check to see who won each game
//			calculateWinner();
//			
//			//Reset all of the players hands to empty
//			playerMap.get(i).resetHand();
//		}
	}
	
	
	private void playGame() {
		
		
		for(int i=playerMap.size()-1;i>0;i--) {
			while(playerMap.get(i).numOfCardsInHand()<2) {
				playerMap.get(i).drawCard(deck.dealCard());
				System.out.println(playerMap.get(i).getUserName());
				System.out.println(playerMap.get(i).listOfCardLabelsInHand());//setup a way to view the cards in a players hand
			}
		}
//		
//		//Deal the cards to all of the players
//		dealCards();
//		
//		//If the player isn't showing all of their cards then show them all
//		player[playerNum].displayCards();
//		
//		//Prompt player to select an action
//		
//		//Possible options for the player to choose
//		while(true) {
//			if(raiseBet) {
//				
//				//transfer chips from player account to pot
//				increaseBet();
//				
//			} else if(hit) {
//				
//				//Run the hit method
//				hit();
//				
//			} else if(stay) {
//				
//				//Run the stay method
//				break;
//				
//			} else {
//				
//				//Run an error
//				
//			}
//		}
	}
	
	
	private void calculateWinner(int playerNum) {
		
		int winner = -1;
		
		//Compare player points to the dealer points
		if(player[playerNum].handValue() > player[dealerNum].handValue()) {
			winner = playerNum;
		} else if(player[playerNum].handValue() < player[dealerNum].handValue()) {
			winner = dealerNum;
		}
		
		
		//Transfer the value of the pot to the winner
		
		
		
		transferPotToWinner();
		
		//Return cards to deck
		returnCardsToDeck();
		
		//Allow player the option to exit the game
		if(exit) {
			exit();
		}else if(continue) {
			startGame();
		} else {
			error();
		}

	}
	
	private void addNewPlayer() {
		
		//Create new player instance
		
		//Set new users username
		
		//add the new user instance to the player array
		
	}
}
