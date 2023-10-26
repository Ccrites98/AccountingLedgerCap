package com.pluralsight;
import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.YearMonth;


//All necessary imports up ^ ^ ^
//Now begins the home page v v v
public class AccountingLedger {
    private static List<Transaction> allTransactions = new ArrayList<>();

    public AccountingLedger() { //Setting up the ArrayList
        allTransactions = new ArrayList<>();
    }
    public static void main(String[] args) { //Main
        AccountingLedger accountingLedger = new AccountingLedger();
        accountingLedger.runMainPage();
    }
    public void runMainPage() { //This is the main menu
        Scanner keyboard = new Scanner(System.in);
        Transactor transactor = new Transactor();
        boolean exit = false;
        LedgerScrn ledger = new LedgerScrn(); //<---- This for the LedgerScrn.java class
        while (!exit) {
            System.out.println("Main Page:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment with Debit");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("What would you like to do?: ");
            String choice = keyboard.nextLine().toUpperCase(); //Self-Explanatory i hope.

            switch (choice) {
                case "D":
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
                    String transactionDateTime = currentDateTime.format(formatter); //Necessary for the CVS to track time


                    System.out.println("Current Date and Time:" + transactionDateTime);

                    System.out.print("What are you paying for?: ");
                    String description = keyboard.nextLine();

                    System.out.print("Vendor?: ");
                    String vendor = keyboard.nextLine();

                    System.out.print("Enter your deposit amount: ");
                    double amount = Double.parseDouble(keyboard.nextLine());
                    //This formats how the CVS handles the input
                    Transaction depositTransaction = new Transaction(transactionDateTime, description, vendor, amount);
                    transactor.writeTransactionToCSV("src/main/resources/transactions.csv", depositTransaction);
                    System.out.println("Deposit successful.");
                    break;
                case "P":
                    currentDateTime = LocalDateTime.now();
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
                    transactionDateTime = currentDateTime.format(formatter);
                    System.out.println("Current Date and Time:" + transactionDateTime);
                    //TIME KEEPING

                    System.out.print("What are you paying for?: ");
                    String debitDescription = keyboard.nextLine();

                    System.out.print("Vendor?: ");
                    String debitVendor = keyboard.nextLine();

                    System.out.print("Enter your amount: ");
                    double debitAmount = Double.parseDouble(keyboard.nextLine());
                    //CVS formatting stuff
                    Transaction debitTransaction = new Transaction(transactionDateTime, debitDescription, debitVendor, debitAmount);
                    transactor.writeTransactionToCSV("src/main/resources/transactions.csv", debitTransaction);
                    System.out.println("Payment successful.");
                    break;
                case "L":
                    try {
                        ledger.setAllTransactions(allTransactions);
                        ledger.showLedgerScrn();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break; //This is all to access the LedgerScrn class.
                case "X":
                    exit = true;
                    System.out.println("Terminating application.");
                    break; //The Quit function, obvs.
                default:
                    System.out.println("Invalid.");
            }}}}

