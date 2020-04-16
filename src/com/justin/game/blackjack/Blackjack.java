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
		
		//If there are no players create a new player
		if(numOfPlayers == 0) {
			addNewPlayer();
		}
		
		//prompt the user to ask if they would like to create a new user
		while(ui.display(messages.returnMessage("newPlayerOption"), true).equalsIgnoreCase("yes")) {
			addNewPlayer();
		}
		
		//Create a new deck
		deck.createNewDeck();
		
		//Shuffle the deck
		deck.shuffleDeck();
		
		
		//Deal cards
		for(int i=playerMap.size()-1;i>=0;i--) {
			//If the player doesn't have two cards then deal two cards
			while(playerMap.get(i).numOfCardsInHand()<2) {
				playerMap.get(i).drawCard(deck.dealCard());
			}
		}
		
		//Run the game play logic for each player
		//Iterate through player map
		playGame();
		
		//handle dealer game play
		
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
				
			while(true) {
				//If the player isn't showing all of their cards then show them all
				ui.display(playerMap.get(i).getUserName() + messages.returnMessage("youHaveBeenDealt") + playerMap.get(i).listOfCardLabelsInHand(), false);
				
				//Show the dealers first card
				ui.display(messages.returnMessage("dealersFirstCard") + playerMap.get(0).dealersFirstCard() , false);
				
				//Prompt player to select an action
				String playerInput = ui.display(messages.returnMessage("playerGameChoice"), true);
				
				if(playerInput.equalsIgnoreCase("raise")) {
					
					//Display the account information for the current player
					ui.display(messages.returnMessage("bankBalance") + playerMap.get(i).getAccountInfo() + " chips.", false);
					
					//Send message with account balance and save the amount the player would like top bet
					int amountToIncreaseBet = Integer.parseInt(ui.display(messages.returnMessage("betAmount"), true));
					
					//If the amount they would like to bet is more than they have then display an error
					if(amountToIncreaseBet > playerMap.get(i).getAccountInfo()) {
						ui.display(messages.returnMessage("notEnoughFunds"), false);
					} else {
						
						//Increase the bet amount
						playerMap.get(i).increaseBet(amountToIncreaseBet);
						
						//Display current account balance
						ui.display(messages.returnMessage("bankBalance") + playerMap.get(i).getAccountInfo() + " chips.", false);
						
						//Display current pot balance
						ui.display(messages.returnMessage("currentValueOfPot") + playerMap.get(i).valueOfPot(), false);
					}
					
				} else if(playerInput.equalsIgnoreCase("hit")) {
					
					//Player is dealt another card
					playerMap.get(i).drawCard(deck.dealCard());
					
				} else if(playerInput.equalsIgnoreCase("stay")) {
					break;
				} else {
					ui.display(messages.returnMessage("errorInputNotFound"), false);
				}
				//Check for Bust
			}
		}
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
		
		Player newPlayer = new Player();
		String newPlayerName = ui.display(messages.returnMessage("newPlayer"), true);
		newPlayer.setUserName(newPlayerName);
		//add starting bet
		numOfPlayers++;
		playerMap.put(numOfPlayers, newPlayer);
		
	}
}
