package com.justin.game.blackjack.managers;

import java.util.ArrayList;
import java.util.List;

import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.HandVO;
import com.justin.game.blackjack.data.PlayerVO;
import com.justin.game.blackjack.data.PotVO;

/****************************************************************************
 * <b>Title</b>: PlayerManager.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> CHANGE ME!!
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class PlayerManager {
	
	private PlayerVO player;

	public PlayerManager(PlayerVO player) {
		this.player = player;
		player.setStatus("in progress");
	}
	
	public int placeBet() {
		
		//get current balance and display
		
		//Ask the user how much they would like to bet
		
		//Validate that they have the funds in their account
		
		//Reduce playerVO account balance by that amount and return to dealer for pot
		
		return 0;
	}
	
	
	public boolean requestAnotherCard() {
		
		//prompt user for choice
		
		//if no return false// update status
		
		//else return true
		
		return false;
	}
	
	public void calculatePossibleHandTotals() {
		
		//Create a list for possible values
		List<Integer> handValues;
		
		//get hand from VO
		HandVO playerHand = player.getHand();
		List<CardVO> cards = playerHand.getHand();
		//loop through and get possible variations for the cards in the hand
		for(CardVO card : cards) {
			//Add possible variations to the possible values list
			handValues.add();
		}
		//store them in the VO
		player.setHandValues(handValues);
		
	}
	
	public void winGame(int amount) {
		
		//Increase playerVO account by amount
		player.getAccount().setAccountBalance(player.getAccount().getAccountBalance() + amount);
		
	}
	
	public void getNewCard(CardVO newCard) {
		
		//Update the players hand
		
		//check if the player has busted
		
		//if true update status to busted
		
		
	}

	public ArrayList<CardVO> returnHand() {
		HandVO hand = player.getHand();
		ArrayList<CardVO> cards = hand.getHand();
		hand.clearHand();
		return cards;
	}
}




















