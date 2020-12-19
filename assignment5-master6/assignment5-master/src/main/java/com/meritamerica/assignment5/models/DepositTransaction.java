package com.meritamerica.assignment5.models;

import com.meritamerica.assignment5.exceptions.ExceedsAvailableBalanceException;
import com.meritamerica.assignment5.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment5.exceptions.NegativeAmountException;

public class DepositTransaction extends Transaction {
	DepositTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.isProcessed = false;
	}
	
	public void process() throws ExceedsAvailableBalanceException, NegativeAmountException, ExceedsFraudSuspicionLimitException {

		if (amount <= 0.0) {
			throw new NegativeAmountException();
		} else if (amount > 1000) {
			FraudQueue.addTransaction(this);
			throw new ExceedsFraudSuspicionLimitException();
		} else {
			targetAccount.deposit(amount);
		}
	}
}
