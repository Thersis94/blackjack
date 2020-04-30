package com.justin.game.blackjack.data;

/****************************************************************************
 * <b>Title</b>: Person.java <b>Project</b>: blackjack <b>Description: </b> This
 * object is a value object storing information about the
 * player<b>Copyright:</b> Copyright (c) 2020 <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author justinjeffrey
 * @version 3.0
 * @since Apr 20, 2020
 * @updates:
 ****************************************************************************/
public class PersonVO {

	String firstName;
	String lastName;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
