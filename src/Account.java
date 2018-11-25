
public class Account {
	//variables to hold account balances and a count for how many withdrawals are made
	private double checking;
	private double savings;
	//for counting over 4 transactions
	private static int count =0;
	//penalty charge for over 4 transaction
	private double charge = 1.50;
	
	public Account() {
		
	}
	
	
	/*this constructor isn't needed at the moment
	public Account(double ch, double sa) {
		checking = ch;
				
	}*/
	
	
	//methods for four buttons on GUI
	
	//method for withdraw...going to need two for savings and checking
	//throws insufficient funds exception
	public void withdrawChecking(double amount) throws InsufficientFunds{
		
				
		//for checking
		if(amount>this.checking) {
			throw new InsufficientFunds();
		}else {
			++count;
			this.checking = this.checking-amount;
			if(count>4) {
				if(charge>this.checking) {
					throw new InsufficientFunds();
				}else
				this.checking = this.checking - charge;}
			}
			}
	
	
	public void withdrawSavings(double amount) throws InsufficientFunds{
	//for savings
		if(amount>this.savings) {
			throw new InsufficientFunds();
		} else {
			++count;
			this.savings=this.savings-amount;
			if(count>4) {
				//in case charge is greater than account amount
				if(charge>this.savings) {
					throw new InsufficientFunds();
				}else
				this.savings = this.savings - charge;}
					}

				}
	
	
	//deposits
	public void setChecking(double amount) {
		this.checking += amount;
	}
	public void setSavings(double amount) {
		this.savings += amount;
	}
	
	public void depositSavings(double amount) {
		this.savings = amount+this.savings;
		
	}	
	

	//transfer
	//methods weren't working correctly because accounts are two different objects??
	public void transferToSavings(double amount) throws InsufficientFunds {
		//subtract from checking
		if(amount>this.checking) {
			throw new InsufficientFunds();
		}else {
		this.checking = checking-amount;}
		//add to savings
		this.savings = savings + amount;
	}
	
	public void transferToChecking(double amount) throws InsufficientFunds {
		//subtract from savings
		if(amount>this.savings) {
			throw new InsufficientFunds();
		}else {
		this.savings = savings - amount;}
		//add to checking
		this.checking = checking + amount;
	}
	
	//balance
	
	public double getChecking() {
		return this.checking;
	}
	
	public double getSavings() {
		return this.savings;
		
	}
}
