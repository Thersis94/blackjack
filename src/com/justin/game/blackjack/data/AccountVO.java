package com.justin.game.blackjack.data;

/****************************************************************************
 * <b>Title</b>: Account.java <b>Project</b>: blackjack <b>Description: </b>
 * AccountVO is a object used for storing the current number of chips in a players account <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 17, 2020
 * @updates:
 ****************************************************************************/
public class AccountVO {

	private int chips = 100;

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
