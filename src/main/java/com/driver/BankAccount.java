package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance){
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if (digits <= 0 || digits > 9 || sum < 0 || sum > digits * 9) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        StringBuilder accountNumber = new StringBuilder();
        boolean isValidCombination = generateAccountNumberMethod(digits, sum, accountNumber);

        if (isValidCombination) {
            return accountNumber.toString();
        } else {

            throw new Exception("Account Number can not be generated");
        }

//        return null;
    }


    private static boolean generateAccountNumberMethod(int digits, int sum, StringBuilder accountNumber) {
        if (digits == 0) {
            return sum == 0;
        }

        for (int digit = 9; digit >= 0; digit--) {
            if (sum - digit >= 0) {
                accountNumber.append(digit);
                boolean isValidCombination = generateAccountNumberMethod(digits - 1, sum - digit, accountNumber);
                if (isValidCombination) {
                    return true;
                }
                accountNumber.deleteCharAt(accountNumber.length() - 1);
            }
        }
        return false;
    }

    public void deposit(double amount) {
        //add amount to balance
        balance+=amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(balance<minBalance){
            throw new Exception("Insufficient Balance");
        }else{
            balance-=amount;
        }

    }

}