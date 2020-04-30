package com.justin.game.blackjack.data;

/****************************************************************************
 * <b>Title</b>: Player.java <b>Project</b>: CoinGameJava <b>Description: </b>
 * Player class is a class that will represent the instance of a player. It
 * contains player information and methods for manipulating user variables
 * <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 14, 2020
 * @updates:
 ****************************************************************************/
public class PlayerVO extends PersonVO {

	private String userName;
	private String status = "waiting";
	private HandVO hand = new HandVO();
	private AccountVO account = new AccountVO();
	private boolean isDealer;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the hand
	 */
	public HandVO getHand() {
		return hand;
	}

	/**
	 * @param hand the hand to set
	 */
	public void setHand(HandVO hand) {
		this.hand = hand;
	}

	/**
	 * @return the account
	 */
	public AccountVO getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(AccountVO account) {
		this.account = account;
	}

	/**
	 * @return the isDealer
	 */
	public boolean isDealer() {
		return isDealer;
	}

	/**
	 * @param isDealer the isDealer to set
	 */
	public void setDealer(boolean isDealer) {
		this.isDealer = isDealer;
	}
}
