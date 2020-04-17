package com.justin.game.blackjack;
/****************************************************************************
 * <b>Title</b>: Player.java
 * <b>Project</b>: CoinGameJava
 * <b>Description: </b> Player class is a class that will represent the instance of a player. It contains player information and methods for manipulating user variables
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 14, 2020
 * @updates:
 ****************************************************************************/
public class PlayerVO {
	
	private int account = 100;
	private String userName;
	private Hand hand = new Hand();
	private Pot pot = new Pot();
	
	/** 
	 * Method to set a new players userName
	 * @param newUserName
	 */
	public void setUserName(String newUserName) {
		userName = newUserName;
	}
	
	/**
	 * Method to return a players name
	 * @return the players name as a string
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Method to return a players account chip number
	 * @return the number of chips a player has
	 */
	public int getAccountInfo() {
		return account;
	}
	
	/**
	 * Method for increasing the value of the pot object
	 * @param recieves a integer with the value to increase the pot by
	 */
	public void increaseBet(int amountToBet) {
		
		//remove chips from account
		account = account - amountToBet;
		//add chips to pot
		pot.increaseTotal(amountToBet);
	}
	
	/**
	 * Method for handling the win/loss/tie of a game
	 * @param recieves either win loss or tie as a String
	 */
	public void handlePot(String winOrTie) {
		
		//Check for win or tie
		if(winOrTie.equalsIgnoreCase("win")) {
			int payout = pot.playerWon();
			//Add winnings to account
			account += payout;
		} else if (winOrTie.equalsIgnoreCase("tie")) {
			int payout = pot.playerTied();
			//Add winnings to account
			account += payout;
		} else {
			pot.playerLost();
		}
	}
	
	/**
	 * Method for receiving a Card object and adding it to the hand object
	 * @param recieves the Card object to add to the hand
	 */
	public void drawCard(Card newCard) {
		
		//draw another card from the deck
		hand.addCardToHand(newCard);
	}
	
	
	/**
	 * Returns the highest possible value without going over 21 of the hand object
	 * @return value of hand object
	 */
	public int handValue() {
		
		//Return the value of current hand
		return hand.handValue();
	}
	
	/**
	 * Method for returning a integer representing the number of cards in a players hand
	 * @return
	 */
	public int numOfCardsInHand() {
		
		//Return the number of cards in the stored in the hand object
		return hand.numOfCardsInHand();
	}
	
	/**
	 * Method for displaying a String listing all of the card labels in a hand
	 * @return A string listing all of the card labels in a hand
	 */
	public String listOfCardLabelsInHand() {
		return hand.displayCards();
	}
	
	/**
	 * Method for showing the first card in a hand object
	 * @return a String with information on the first card in the hand object
	 */
	public String showFirstCardInHand() {
		return hand.displayCard();
	}
	
	/**
	 * Method returning the value of the pot object as an integer
	 * @return the value of the pot object as an integer
	 */
	public int valueOfPot() {
		return pot.totalChips;
	}
	
	/**
	 * Method returning a card object from the hand object
	 * @return card object from the hand object
	 */
	public Card returnHandToDeck() {
		return hand.returnCard();
	}
	
}
