package com.justin.game.blackjack;
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
public class Pot {
	
	int totalChips;
	
	
	public int payWinner() {
		
		//Save the value of totalChips in pot
		int payout = totalChips;
		
		//Reset the totalChips to 0
		totalChips = 0;
		
		//Return the original value
		return payout;
	}
	
	public void increaseTotal(int potIncrease) {
		
		//Set totalChips to the original value plus the received value
		totalChips = totalChips + (potIncrease*2);
	}
	
	public int totalPot() {
		return totalChips;
	}
	
	
}
