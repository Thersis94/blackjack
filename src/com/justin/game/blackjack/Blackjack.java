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
	private int minimumBet = 10;
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
			
			//Place minimum bet
			playerMap.get(i).increaseBet(minimumBet);
		}
		
		//Run the game play logic for each player
		playGame();
		
		//Calculate Winners
		 calculateWinners();
		 
	}
	
	
	private void playGame() {
		
		for(int i=playerMap.size()-1;i>=0;i--) {
				
			if(i == 0) {
				while(playerMap.get(0).handValue() < 17) {
					playerMap.get(0).drawCard(deck.dealCard());
				}
			} else {
			
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
					if(playerMap.get(i).handValue() > 21) {
						ui.display(playerMap.get(i).getUserName() + messages.returnMessage("youHaveBeenDealt") + playerMap.get(i).listOfCardLabelsInHand(), false);
						ui.display(messages.returnMessage("bust"), false);
						break;
					}
				}
			}
		}
	}
	
	private void calculateWinners() {
		
		for(int i=playerMap.size()-1;i>0;i--) {
			
			int playerHandValue = playerMap.get(i).handValue();
			int dealerHandValue = playerMap.get(0).handValue();
			
			//Compare player points to the dealer points
			if(playerHandValue < 22 && playerHandValue == dealerHandValue) {
				
				displayEndingHands(i);
				playerMap.get(i).collectWinnings("tie");
				ui.display(messages.returnMessage("tie"), false);
			} else if((playerHandValue < 22 && playerHandValue > dealerHandValue) || (playerHandValue < 22 && dealerHandValue > 21)) {
				
				ui.display(messages.returnMessage("youWon") + playerMap.get(i).valueOfPot() + " chips!", false);
				displayEndingHands(i);
				playerMap.get(i).collectWinnings("win");
				ui.display(messages.returnMessage("win"), false);
			} else {
				
				displayEndingHands(i);
				playerMap.get(0).collectWinnings("lost");
				ui.display(messages.returnMessage("lost"), false);
			}
		}

		//Return cards to deck
		returnCardsToDeck();

		//Allow player the option to exit the game
		String playerInput = ui.display(messages.returnMessage("exitOption"), true);
		while(true) {
			if(playerInput.equalsIgnoreCase("exit")) {
				System.exit(0);
			} else if(playerInput.equalsIgnoreCase("continue")) {
				startGame();
			} else {
				ui.display(messages.returnMessage("errorInputNotFound"), false);
			}
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
	
	public void returnCardsToDeck() {
		
		//Iterate through all players
		for(int i=playerMap.size()-1;i>=0;i--) {
			
			//Check the number of cards a player has
			int playersCards = playerMap.get(i).numOfCardsInHand();

			//Iterate through the players cards
			for(int c=playersCards; c>0;c--) {
				
				//Return cards to the deck
				deck.addCardToDeck(playerMap.get(i).returnHandToDeck());
			}
		}
		
	}
	
	public void displayEndingHands(int playerNum) {
		
		//display the dealers hand
		ui.display(playerMap.get(0).getUserName() + messages.returnMessage("dealerHasBeenDealt") + playerMap.get(0).listOfCardLabelsInHand(), false);
		
		//display the players hand
		ui.display(playerMap.get(playerNum).getUserName() + messages.returnMessage("youHaveBeenDealt") + playerMap.get(playerNum).listOfCardLabelsInHand(), false);
		
		//display the players account balance
		ui.display(messages.returnMessage("bankBalance") + playerMap.get(playerNum).getAccountInfo() + " chips.", false);
		
	}
}


















