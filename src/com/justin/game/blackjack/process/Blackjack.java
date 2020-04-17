package com.justin.game.blackjack.process;

import java.util.HashMap;

import com.justin.game.blackjack.data.DeckVO;
import com.justin.game.blackjack.data.MessagesVO;
import com.justin.game.blackjack.data.PlayerVO;

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

	HashMap<Integer, PlayerVO> playerMap = new HashMap<Integer, PlayerVO>();

	private int numOfPlayers = 0;
	private MessagesVO messages = new MessagesVO();
	private int minimumBet = 10;
	DeckVO deck = new DeckVO();
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

		// Initialize the card deck object
		initializeDeck();

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

		// Create player instance for Dealer
		PlayerVO dealer = new PlayerVO();
		dealer.setUserName("Dealer");
		playerMap.put(0, dealer);
	}

	/**
	 * Runs the createNewDeck method on the deck instance to build all of the card
	 * objects that it will hold
	 */
	private void initializeDeck() {

		// Runs the deck method to create all of the playing card objects in the deck
		deck.createNewDeck();
	}

	/**
	 * 
	 */
	private void startGame() {

		// If there are no players create a new player
		if (numOfPlayers == 0) {
			addNewPlayer();
		}

		// prompt the user to ask if they would like to create a new user
		while (ui.display(messages.returnMessage("newPlayerOption"), true).equalsIgnoreCase("yes")) {
			addNewPlayer();
		}

		// Create a new deck
		deck.createNewDeck();

		// Shuffle the deck
		deck.shuffleDeck();

		// Deal cards
		for (int i = playerMap.size() - 1; i >= 0; i--) {
			// If the player doesn't have two cards then deal two cards
			while (playerMap.get(i).numOfCardsInHand() < 2) {
				playerMap.get(i).drawCard(deck.dealCard());
			}

			// Place minimum bet
			playerMap.get(i).increaseBet(minimumBet);
		}

		// Run the game play logic for each player
		playGame();

		// Calculate Winners
		calculateWinners();

	}

	/**
	 * runs the game play sequence
	 */
	private void playGame() {

		// Iterate through players
		for (int i = playerMap.size() - 1; i >= 0; i--) {

			// Check to see if the current player is the dealer
			if (i == 0) {

				// Force the dealer to hit until he has a minimum hand value of 17
				while (playerMap.get(0).handValue() < 17) {
					playerMap.get(0).drawCard(deck.dealCard());
				}
			} else {

				// Run loop while player is making play decisions
				while (true) {

					ui.display(messages.returnMessage("bracket"), false);
					
					// If the player isn't showing all of their cards then show them all
					ui.display(playerMap.get(i).getUserName() + messages.returnMessage("youHaveBeenDealt")
							+ playerMap.get(i).listOfCardLabelsInHand(), false);

					// Show the dealers first card
					ui.display(messages.returnMessage("dealersFirstCard") + playerMap.get(0).showFirstCardInHand(),
							false);

					// Prompt player to select an action
					String playerInput = ui.display(messages.returnMessage("playerGameChoice"), true);

					// Run if player selects 'raise'
					if (playerInput.equalsIgnoreCase("raise")) {

						// Display the account information for the current player
						ui.display(
								messages.returnMessage("bankBalance") + playerMap.get(i).getAccountInfo() + " chips.",
								false);

						// Send message with account balance and save the amount the player would like
						// top bet
						int amountToIncreaseBet = Integer
								.parseInt(ui.display(messages.returnMessage("betAmount"), true));

						// If the amount they would like to bet is more than they have then display an
						// error
						if (amountToIncreaseBet > playerMap.get(i).getAccountInfo()) {

							ui.display(messages.returnMessage("notEnoughFunds"), false);
						} else {

							// Increase the bet amount
							playerMap.get(i).increaseBet(amountToIncreaseBet);

							// Display current account balance
							ui.display(messages.returnMessage("bankBalance") + playerMap.get(i).getAccountInfo()
									+ " chips.", false);

							// Display current pot balance
							ui.display(messages.returnMessage("currentValueOfPot") + playerMap.get(i).valueOfPot(),
									false);
						}

						// Run if player selects hit
					} else if (playerInput.equalsIgnoreCase("hit")) {

						// Player is dealt another card
						playerMap.get(i).drawCard(deck.dealCard());

						// If player selects stay break the loop
					} else if (playerInput.equalsIgnoreCase("stay")) {

						break;
					} else {

						ui.display(messages.returnMessage("errorInputNotFound"), false);
					}

					// Check for Bust
					if (playerMap.get(i).handValue() > 21) {
						ui.display(messages.returnMessage("bracket"), false);
						ui.display(playerMap.get(i).getUserName() + messages.returnMessage("youHaveBeenDealt")
								+ playerMap.get(i).listOfCardLabelsInHand(), false);
						ui.display(messages.returnMessage("bust"), false);
						ui.display(messages.returnMessage("bracket"), false);
						break;
					}
				}
			}
		}
	}

	/**
	 * Logic that iterates through the player list and compares player hand values
	 * to the dealer and determines the winner
	 */
	private void calculateWinners() {

		// Iterate through the players
		for (int i = playerMap.size() - 1; i > 0; i--) {
			
			ui.display(messages.returnMessage("bracket"), false);

			int playerHandValue = playerMap.get(i).handValue();// Current players hand value
			int dealerHandValue = playerMap.get(0).handValue();// The dealers hand value

			// If the players hand didn't bust and it is equal to the dealers hand then it
			// is a push
			if (playerHandValue < 22 && playerHandValue == dealerHandValue) {

				playerMap.get(i).handlePot("tie");
				// Shows hands of the player and the dealer
				displayEndingHands(i);
				ui.display(messages.returnMessage("tie"), false);

				// If the players hand did't bust and the players hand is greater than the
				// dealers hand or the player didn't bust and the dealer did the player wins
			} else if ((playerHandValue < 22 && playerHandValue > dealerHandValue)
					|| (playerHandValue < 22 && dealerHandValue > 21)) {

				ui.display(messages.returnMessage("youWon") + playerMap.get(i).valueOfPot() + " chips!", false);
				playerMap.get(i).handlePot("win");
				// Shows hands of the player and the dealer
				displayEndingHands(i);
				ui.display(messages.returnMessage("win"), false);

				// Else the player loses
			} else {

				playerMap.get(0).handlePot("lost");
				// Shows hands of the player and the dealer
				displayEndingHands(i);
				ui.display(messages.returnMessage("lost"), false);
			}
		}

		// Return cards to deck
		returnCardsToDeck();

		// Allow player the option to exit the game
		String playerInput = ui.display(messages.returnMessage("exitOption"), true);

		// Run loop till player inputs either a continue or exit command
		while (true) {
			if (playerInput.equalsIgnoreCase("exit")) {
				ui.closeScanner();
				System.exit(0);
			} else if (playerInput.equalsIgnoreCase("continue")) {
				startGame();
			} else {
				ui.display(messages.returnMessage("errorInputNotFound"), false);
			}
		}
	}

	/**
	 * Adds a new player to the player map when run. Will prompt the user for a
	 * playerName for the new player map object
	 */
	private void addNewPlayer() {

		PlayerVO newPlayer = new PlayerVO();// Create new instance of the player object
		String newPlayerName = ui.display(messages.returnMessage("newPlayer"), true);// Prompt user for new player name
		newPlayer.setUserName(newPlayerName);// Set the selected playerName
		numOfPlayers++;// Increase player count
		playerMap.put(numOfPlayers, newPlayer);// Add the new Player object to the player object map
	}

	/**
	 * Iterate through all players and return their hands to the deck
	 */
	public void returnCardsToDeck() {

		// Iterate through all players
		for (int i = playerMap.size() - 1; i >= 0; i--) {

			// Check the number of cards a player has
			int playersCards = playerMap.get(i).numOfCardsInHand();

			// Iterate through the players cards
			for (int c = playersCards; c > 0; c--) {

				// Return cards to the deck
				deck.addCardToDeck(playerMap.get(i).returnHandToDeck());
			}
		}
	}

	/**
	 * Method created to handle message delivery in the calculateWinner method to
	 * reduce repetition
	 * 
	 * @param recieves the current player number
	 */
	public void displayEndingHands(int playerNum) {

		// display the dealers hand
		ui.display(playerMap.get(0).getUserName() + messages.returnMessage("dealerHasBeenDealt")
				+ playerMap.get(0).listOfCardLabelsInHand(), false);
		
		ui.display(messages.returnMessage("bracket"), false);

		// display the players hand
		ui.display(playerMap.get(playerNum).getUserName() + messages.returnMessage("youHaveBeenDealt")
				+ playerMap.get(playerNum).listOfCardLabelsInHand(), false);
		
		ui.display(messages.returnMessage("bracket"), false);

		// display the players account balance
		ui.display(messages.returnMessage("bankBalance") + playerMap.get(playerNum).getAccountInfo() + " chips.",
				false);
	}
}
