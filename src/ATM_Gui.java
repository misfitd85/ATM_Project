import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ATM_Gui extends JFrame{
//variables for the GUI
private JButton withdraw;
private JButton deposit;
private JButton transfer;
private JButton balance;
private JRadioButton checking;
private JRadioButton savings;
private ButtonGroup group;
private JTextField tf;
private JOptionPane message;
//instance variables for account objects to be used in constructor
private Account checking1;
private Account savings1;

//Constructor for the GUI
ATM_Gui(Account checking1, Account savings1){
	
	
	
	//Set title
	super("ATM Machine");
	setLayout(new FlowLayout());
	
	this.checking1 = checking1;
	this.savings1 = savings1;
	
	//JBUttons for ATM Function
	withdraw = new JButton("Withdraw");
	add(withdraw);
	
	deposit = new JButton("Deposit");
	add(deposit);
	
	transfer = new JButton("Transfer");
	add(transfer);
	
	balance = new JButton("Balance");
	add(balance);
	
	//Radio Buttons for accounts
	checking = new JRadioButton("Checking",true);
	add(checking);
	savings = new JRadioButton("Savings",false);
	add(savings);
	group = new ButtonGroup();
	group.add(checking);
	group.add(savings);
	
	//Text Field for amount input
	tf = new JTextField(30);
	add(tf);
	
	//JOptionPane for error/confirm message
	message = new JOptionPane();
	
	
	//Handlers for Buttons/Text Field
	TheHandler handler = new TheHandler();
	withdraw.addActionListener(handler);
	deposit.addActionListener(handler);
	transfer.addActionListener(handler);
	balance.addActionListener(handler);
	checking.addActionListener(handler);
	savings.addActionListener(handler);
	tf.addActionListener(handler);
	
}//end constructor
	



private class TheHandler implements ActionListener{
	public void actionPerformed(ActionEvent event) {
				

		
		
			//try catch to make sure entry is numeric
			try {
				double x = 0;
				//if checking is selected
				if(checking.isSelected()) {
					
					//if withdraw is pressed
				if(event.getSource()==withdraw) {
				//will catch an exception if entry in text field is not a number
				x = Double.parseDouble(tf.getText());
				
				//will catch an exception if text field entry is not divisible by 20
				boolean isDivisibleBy20 = Integer.parseInt(tf.getText()) % 20 == 0;
				if(isDivisibleBy20 == false) {
					message.showMessageDialog(null,"Please enter increments of 20");
				}else {
					checking1.withdrawChecking(Double.parseDouble(tf.getText()));
					message.showMessageDialog(null, tf.getText() + " withdrawn from checking.");
					
			}//end else for withdraw
				}//end if withdraw is pressed
				
				
				
				//if deposit
				if(event.getSource()==deposit) {
					checking1.setChecking(Double.parseDouble(tf.getText()));
					System.out.println(checking1.getChecking());
					message.showMessageDialog(null, "You deposited "+tf.getText());
				}//end if deposit
				
				//if balance
				else if(event.getSource()==balance) {
					message.showMessageDialog(null, "Your checking balance is: "+checking1.getChecking());
				}//end if balance
				//if transfer
				else if(event.getSource()==transfer) {
					checking1.transferToSavings(Double.parseDouble(tf.getText()));
					//added in because method does not work correctly
					savings1.setSavings(Double.parseDouble(tf.getText()));
					
					message.showMessageDialog(null, tf.getText() + " deposited to savings.");
				}//end if transfer
			
					}//end if checking is selected
				
				
				//if savings is selected
				else if(savings.isSelected()) 
				{
					//if deposit
					if(event.getSource()==deposit) {
						savings1.setSavings(Double.parseDouble(tf.getText()));
						System.out.println(savings1.getSavings());
						message.showMessageDialog(null, "You deposited "+tf.getText());
					}//end if deposit
					
					//if balance
					else if(event.getSource()==balance) {
						message.showMessageDialog(null, "Your savings balance is: "+savings1.getSavings());
					}//end if balance
					//if transfer
					else if(event.getSource()==transfer) {
						savings1.transferToChecking(Double.parseDouble(tf.getText()));
						//added in because method does not work correctly
						checking1.setChecking(Double.parseDouble(tf.getText()));
						message.showMessageDialog(null, tf.getText() + " deposited to checking.");
					}//end if transfer
					//if withdraw
					else if(event.getSource()==withdraw) {
						
						//will catch an exception if text field entry is not divisible by 20
						boolean isDivisibleBy20 = Integer.parseInt(tf.getText()) % 20 == 0;
						if(isDivisibleBy20 == false) {
							message.showMessageDialog(null,"Please enter increments of 20");
						}else {						
						savings1.withdrawSavings(Double.parseDouble(tf.getText()));
						message.showMessageDialog(null, tf.getText() + " withdrawn from savings.");}
					}//end if withdraw
					
					}//end if savings is selected
				
				
			}/*end try block*/ 
				
			catch(NumberFormatException er) {
				//creates JOptionPane window for error
				message.showMessageDialog(null,"Please enter a numeric value.");
				}//end catch numberformat exception
			catch(InsufficientFunds er) {
				message.showMessageDialog(null, "You have insufficient funds for this transaction.");
			}
			}//end handler method
		}//end handler class
		
		
		
	}//end GUI class





class ATM{
	public static void main(String[] args) throws InsufficientFunds {
		//creating object for GUI and setting size/visibility
		
		//Account objects to use with Action Listener
		Account checking1 = new Account();
		Account savings1 = new Account();
		checking1.setChecking(30000);
		savings1.setSavings(10000);
		
		ATM_Gui atm = new ATM_Gui(checking1, savings1);
		atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atm.setSize(400,350);
		atm.setVisible(true);
				
	}
}