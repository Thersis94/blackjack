package com.justin.game.blackjack.managers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.justin.game.blackjack.data.CardVO;
import com.justin.game.blackjack.data.DealerVO;
import com.justin.game.blackjack.data.PlayerVO;
import com.justin.game.blackjack.data.PotVO;

/****************************************************************************
 * <b>Title</b>: DealerManager.java <b>Project</b>: blackjack <b>Description:
 * </b> CHANGE ME!! <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class Game21Manager {

	private List<PlayerVO> players;
	private DealerVO dealer;
	private Map<String, PotVO> gamePot;

	public Game21Manager(List<PlayerVO> players, DealerVO dealer) {
		this.players = players;
		this.dealer = dealer;
		this.gamePot = new LinkedHashMap<>();
		players.add(dealer);
	}

	public void playGame() {
		
		for (PlayerVO player : players) {
			gamePot.put(player.getUserName(), new PotVO());
			DealerPlayerManager dpm = new DealerPlayerManager(dealer);
			PlayerManager pm = null;
			if (player.isDealer())
				pm = new DealerPlayerManager(player);
			else
				pm = new PlayerManager(player);

			playerTurn(player, pm, dpm);
		}
	}

	public void playerTurn(PlayerVO player, PlayerManager pm, DealerPlayerManager dpm) {
		
			while (true) {
				int bet = pm.placeBet();
				// Add bet amount to pot from player and dealer if > 0
				gamePot.get(player.getUserName()).setChips(gamePot.get(player.getUserName()).getChips() + dpm.matchBet(bet));
				
				//Player is dealt another card
				pm.getNewCard(dpm.dealCard());
				
				//Set the best possible hand value
				player.setBestPossibleHand(bestPossibleHand(player.getHandValues()));
				
				//Check for a bust
				if(player.getBestPossibleHand() > 21) {
					player.setStatus("bust");
					break;
				}
				
				//If player doesn't request another card break while loop
				if (!pm.requestAnotherCard())
					break;
			}
		
	}

	public void calculateWinners() {
		// Calculate winners
			for(PlayerVO player : players) {
				if(player.getStatus().equals("bust") || (!player.getStatus().equals("bust") && player.getBestPossibleHand() < dealer.getBestPossibleHand())) {
					
					//Set player status to lost
					
				} else if(!player.getStatus().equals("bust") && dealer.getStatus().contentEquals("bust") || (!player.getStatus().equals("bust") && player.getBestPossibleHand() > dealer.getBestPossibleHand())) {
					
					//Set player status to win
					
				} else if(!player.getStatus().equals("bust") && dealer.getStatus().contentEquals("bust") || (!player.getStatus().equals("bust") && player.getBestPossibleHand() == dealer.getBestPossibleHand())) {
					
					//Set player status to tie
					
				}
				
				
				switch(player.getStatus()) {
				case "tie":
					//Transfer Pot value 50/50 between player and dealer
					
					//Remove Pot
					
					break;
				case "win":
					//Transfer the total pot value to the player
					
					//Remove Pot
					
					break;
				case "lose":
					//Transfer total pot value to the dealer
					
					//Remove Pot
					
					break;
				}
			}
	}
	
	public int bestPossibleHand(List<Integer> handValues) {
		
		//Iterate through handValue
		
		//Find the best possible hand value
		
		//Return the best hand value as integer
		
		return -1;
	}
	
	public void dealCards() {
		DealerPlayerManager dpm = new DealerPlayerManager(dealer);
		dpm.shuffleDeck();
		for(PlayerVO player : players) {
			if(player.getHand().numOfCardsInHand() < 2) {
				// Have the dealer deal a card to the player
			}
		}
	}

	public void returnHandsToDeck() {
		ArrayList<CardVO> cardsToReturn = new ArrayList<CardVO>();
		
		for(PlayerVO player : players) {
			PlayerManager pm = new DealerPlayerManager(player);
			ArrayList<CardVO> playersCards = pm.returnHand();
			for(CardVO card : playersCards) {
				cardsToReturn.add(card);
			}
		}
		
		DealerPlayerManager dpm = new DealerPlayerManager(dealer);
		dpm.returnCardsToDeck(cardsToReturn);
		
	}
	
	
}











