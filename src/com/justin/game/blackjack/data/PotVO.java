package com.justin.game.blackjack.data;

/****************************************************************************
 * <b>Title</b>: Pot.java <b>Project</b>: blackjack <b>Description: </b> The pot
 * object is a class that represents the current value of a players bets.
 * <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 15, 2020
 * @updates:
 ****************************************************************************/
public class PotVO {

	private int chips;

	/**
	 * @return the chips
	 */
	public int getChips() {
		return chips;
	}

	/**
	 * @param chips the chips to set
	 */
	public void setChips(int chips) {
		this.chips = chips;
	}

}
