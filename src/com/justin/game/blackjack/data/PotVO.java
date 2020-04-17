package com.justin.game.blackjack.data;
/****************************************************************************
 * <b>Title</b>: Pot.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> The pot object is a class that represents the current value of a players bets.
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 15, 2020
 * @updates:
 ****************************************************************************/
public class PotVO {
	
	int totalChips;
	
	/**
	 * Method for paying out the total number of chips to the winner of a round
	 * @return the total number of chaps as a integer
	 */
	public int playerWon() {
		
		//Save the value of totalChips in pot
		int payout = totalChips;
		
		//Reset the totalChips to 0
		totalChips = 0;
		
		//Return the original value
		return payout;
	}
	
	/**
	 * Method for returning the players bet to them if the round was a push
	 * @return the number of chips the player bet as an integer
	 */
	public int playerTied() {
		
		//Split total in half
		int payout = totalChips/2;
		
		//Reset the totalChips to 0
		totalChips = 0;
		
		//Return the payout to the player
		return payout;
	}
	
	/**
	 * Method for reducing the pot to zero when the player loses
	 */
	public void playerLost() {

		totalChips = 0;
	}
	
	/**
	 * Method for increasing the the total pot value by the players bet
	 * @param potIncrease is the total bet a player is placing as an integer
	 */
	public void increaseTotal(int potIncrease) {
		
		//Set totalChips to the original value plus the received value multiplied by two for the dealers bet
		totalChips = totalChips + (potIncrease*2);
	}
	
	/**
	 * Method for returning the total number of chips in the pot
	 * @return the total number of chips in the pot as an integer
	 */
	public int totalPot() {
		return totalChips;
	}
}
