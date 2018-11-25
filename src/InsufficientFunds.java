import javax.swing.*;

/*
 * Developed By: Devin Norwood
 * Date: 18 November 2018
 * InsufficientFunds.java
 * Purpose: This class throws a user defined Insufficient Funds checked exception
 */

public class InsufficientFunds extends Exception{

	public InsufficientFunds() {
		
		//throws an Exception for an account balance going lower than 0
		super("Insufficient Funds");
		
		/*Uneeded
		 * JOptionPane insFunds = new JOptionPane();
		insFunds.showMessageDialog(null, "You have insufficient funds!");*/
		
	}
}
