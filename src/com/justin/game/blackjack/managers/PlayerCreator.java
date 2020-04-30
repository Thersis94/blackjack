package com.justin.game.blackjack.managers;

//Importing the MessagesVo to display new user prompts
import com.justin.game.blackjack.data.MessagesVO;
import com.justin.game.blackjack.data.PlayerVO;
import com.justin.game.blackjack.process.UserInterface;

/****************************************************************************
 * <b>Title</b>: PlayerCreator.java <b>Project</b>: blackjack <b>Description:
 * </b> CHANGE ME!! <b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon
 * Mountain Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 22, 2020
 * @updates:
 ****************************************************************************/
public class PlayerCreator {

	public PlayerVO createNewPlayer() {
		
		MessagesVO messages = new MessagesVO();
		messages.setGameMessages();
		UserInterface ui = new UserInterface();

		// UI prompt user to create new player
		PlayerVO newPlayer = new PlayerVO();
		newPlayer.setFirstName(ui.display(messages.returnMessage("firstName"), true));
		newPlayer.setLastName(ui.display(messages.returnMessage("lastName"), true));
		newPlayer.setUserName(ui.display(messages.returnMessage("userName"), true));
		return newPlayer;
	}
}
