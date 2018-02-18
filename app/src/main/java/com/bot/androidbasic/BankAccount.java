package com.bot.androidbasic;

public class BankAccount {
    private final float balance;
    private final float accountNumber;
    private final String holderName;
    private final String holderAddress;
    private static String BANK_NAME = "SBI";

    public static void main(String args[]) {

//        BankAccount harisAccount = new BankAccount(100, 12345, "Harish", "Telangana");
//        BankAccount bhagiAccount = new BankAccount(100, 12346, "Bhagi", "Telangana");
//        BankAccount maruthiAccount = new BankAccount(100, 12347, "Maruthi", "Telangana");
//
//        System.out.println(harisAccount.toString());
//        System.out.println(bhagiAccount.toString());
//        System.out.println(maruthiAccount.toString());
//
//        harisAccount = harisAccount.debit(10);
//        bhagiAccount = bhagiAccount.credit(10);
//
//
//        System.out.println(harisAccount.toString());
//        System.out.println(bhagiAccount.toString());
//        System.out.println(maruthiAccount.toString());


        //worker thread

        for (int j = 0; j < 100; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int n = 0;
                    while (n < 10) {
                        n++;
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread 1=" + n);
                    }
                }
            }).start();
        }

        //UI thread
        int n = 0;
        while (n < 10) {
            n++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2=" + n);
        }

    }


    public static String formatCurrency(float balance) {
        return balance + " ₹";
    }

    public BankAccount(float balance, float accountNumber, String holderName, String holderAddress) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.holderAddress = holderAddress;
    }

    public BankAccount debit(float value) {
        return new BankAccount((this.balance - value), this.accountNumber, this.holderName, this.holderAddress);
    }

    public BankAccount credit(float value) {
        return new BankAccount((this.balance + value), this.accountNumber, this.holderName, this.holderAddress);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + formatCurrency(balance) +
                ", accountNumber=" + accountNumber +
                ", holderName='" + holderName + '\'' +
                ", holderAddress='" + holderAddress + '\'' +
                ", banName='" + BANK_NAME + '\'' +
                '}';
    }
}
