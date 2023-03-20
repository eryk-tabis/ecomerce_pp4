package org.example;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal assignedCredit = BigDecimal.ZERO;
    private int billingCount = 0;
    private boolean isOnCredit = false;
    public CreditCard(String cardNumber) {

    }

    public void assignLimit(BigDecimal creditAmount) {
        if (creditAmount.compareTo(BigDecimal.valueOf(100)) < 0) {
            throw new CreditBelowLimitException();
        }
        if (this.assignedCredit.compareTo(BigDecimal.ZERO) != 0){
            throw new CreditWasAssigned();
        }
        this.balance = this.balance.add(creditAmount);
        this.assignedCredit = creditAmount;
        isOnCredit = true;
    }
    public void withdrawMoney(BigDecimal withdrawAmount){
        if(this.balance.compareTo(withdrawAmount) >= 0 && this.assignedCredit.compareTo(withdrawAmount) >= 0 && billingCount != 10){
            billingCount ++;
            reduceBalance(withdrawAmount);

            System.out.println("withdraw completed");
        }else{
            System.out.println("not enough credit");
        }

    }
    public void repayCredit(BigDecimal repayAmount){
        if(this.assignedCredit.compareTo(repayAmount) <=0){
            increaseBalance(repayAmount.subtract(this.assignedCredit));
            this.assignedCredit = BigDecimal.valueOf(0);
            this.billingCount = 0;
            this.isOnCredit = false;

        }else{ // if amount is smaller
            this.assignedCredit = this.assignedCredit.subtract(repayAmount);
            if(repayAmount.compareTo(BigDecimal.valueOf(0)) != 0){
                this.billingCount = 0;
            }
        }
    }
    public void payIn(BigDecimal amount){increaseBalance(amount);}
    public BigDecimal getCurrentBalance() {
        return balance;
    }
    private void increaseBalance(BigDecimal am) {this.balance = this.balance.add(am);}
    private void reduceBalance(BigDecimal am) {this.balance = this.balance.subtract(am);}
}
