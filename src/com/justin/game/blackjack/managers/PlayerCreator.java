package com.justin.game.blackjack.managers;

import com.justin.game.blackjack.data.PlayerVO;

/****************************************************************************
 * <b>Title</b>: PlayerCreator.java
 * <b>Project</b>: blackjack
 * <b>Description: </b> CHANGE ME!!
 * <b>Copyright:</b> Copyright (c) 2020
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 22, 2020
 * @updates:
 ****************************************************************************/
public class PlayerCreator {

	public PlayerVO createNewPlayer() {
		
		//UI prompt user to create new player
		PlayerVO newPlayer = new PlayerVO();
		newPlayer.getFirstName(UI.getFirstName);
		newPlayer.getLastName(UI.getLastName);
		newPlayer.getUserName(UI.getUserName);
		return newPlayer;
	}
	
}
