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
public class Player {
	
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
	
	
	public void increaseBet(int amountToBet) {
		
		//remove chips from account
		account = account - amountToBet;
		//add chips to pot
		pot.increaseTotal(amountToBet);
	}
	
	
	public void collectWinnings(String winOrTie) {
		
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
	
	public void resetHand() {
		
		//empty the players hand... either transfer back to the deck or remove completely if we are regenerating a new deck from scratch each round
		
	}
	
	
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
	
	
	public int numOfCardsInHand() {
		
		//Return the number of cards in the stored in the hand object
		return hand.numOfCardsInHand();
	}
	
	
	public String listOfCardLabelsInHand() {
		return hand.displayCards();
	}
	
	public String dealersFirstCard() {
		return hand.displayCard();
	}
	
	public int valueOfPot() {
		return pot.totalChips;
	}
	
	public Card returnHandToDeck() {
		return hand.returnCard();
	}
	
}
