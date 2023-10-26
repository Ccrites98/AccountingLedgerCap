package com.pluralsight;
import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgerScrn { //Pardon the private declarations below.

    private MonthToDateReport monthToDateReport;
    private PreviousMonth previousMonth;
    private YearToDate yearToDate;
    private Transactor transactor;
    private PreviousYear previousYear;
    private VendorSearch vendorSearch;
    private YearMonth currentYearMonth;
    private List<Transaction> allTransactions;

    public LedgerScrn() { //needed to initalize these for the ledger screen
        this.transactor = new Transactor();
        this.monthToDateReport = new MonthToDateReport();
        this.previousMonth = new PreviousMonth();
        this.yearToDate = new YearToDate();
        this.previousYear = new PreviousYear();
        this.vendorSearch = new VendorSearch();
        this.currentYearMonth = YearMonth.now();
    }

    public void showLedgerScrn() throws FileNotFoundException {
        if (allTransactions == null) {
            System.out.println("Not available.");
            return; //In case something goes horribly horribly wrong. Or there's no transactions around.
        }
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("Ledger Page:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter your choice: ");
            //Ledger Screen Menu above.
            String choice = keyboard.nextLine().toUpperCase();
            if (allTransactions.isEmpty()) {
                allTransactions = transactor.readTransactions();
            }
            //Fetch Transactions and populate above if empty.
            switch (choice) {
                case "A":
                    displayEntries(allTransactions);
                    break; //Displays all in the ledger
                case "D":
                    List<Transaction> depositTransactions = filterDeposits(allTransactions);
                    displayEntries(depositTransactions); //Supposed to only display Deposits.
                    break;
                case "P":
                    List<Transaction> paymentTransactions = filterPayments(allTransactions);
                    displayEntries(paymentTransactions); //Supposed to only display Payments.
                    break;
                case "R":
                    // Reports menu
                    while (true) {
                        System.out.println("Reports:");
                        System.out.println("1) Month to Date");
                        System.out.println("2) Previous Month");
                        System.out.println("3) Year to Date");
                        System.out.println("4) Previous Year");
                        System.out.println("5) Search by Vendor");
                        System.out.println("0) Back");
                        System.out.print("Enter your choice: ");
                        String reportChoice = keyboard.nextLine();
                        //Down here are all the Report options.
                        switch (reportChoice) {
                            case "1":
                                MonthToDateReport monthToDateReport = new MonthToDateReport();
                                monthToDateReport.generateReport(transactor.readTransactions(), currentYearMonth);
                                break;
                            case "2":
                                PreviousMonth previousMonth = new PreviousMonth();
                                previousMonth.generateReport(transactor.readTransactions(), currentYearMonth);
                                break;
                            case "3":
                                YearToDate yearToDate = new YearToDate();
                                yearToDate.generateReport(transactor.readTransactions(), currentYearMonth);
                                break;
                            case "4":
                                PreviousYear previousYearReport = new PreviousYear();
                                previousYearReport.generateReport(transactor.readTransactions(), currentYearMonth);
                                break;
                            case "5":
                                VendorSearch vendorSearch = new VendorSearch();
                                vendorSearch.generateReport(transactor.readTransactions());
                                break;
                            case "0":
                                return; //makes you go back
                            default:
                                System.out.println("Invalid.");
                        }
                        if (reportChoice.equals("0")) {
                            break; // Exits the Reports menu
                        }
                    }
                    break;
                case "H":
                    return; // Should take you to the home screen
                default:
                    System.out.println("Invalid. Please try again.");
            }
        }
    }

    //The hidden methods that hold this code up.
    //Don't stare too long into the coding abyss
    private void displayEntries(List<Transaction> transactions) { //For the display above, on the Ledger Menu
        System.out.println("Transaction Ledger:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (int i = transactions.size() - 1; i >= 0; i--) {
                Transaction transaction = transactions.get(i);
                System.out.println(transaction.toCSVString());
            }}}
    public List<Transaction> filterDeposits(List<Transaction> transactions) { //Supposed to filter the Deposits for the Ledger.
        List<Transaction> depositTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.isDeposited()) {
                depositTransactions.add(transaction);
            }
        }
        return depositTransactions;
    }
    public List<Transaction> filterPayments(List<Transaction> transactions) { //Same, but for the payments.
        List<Transaction> paymentTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                paymentTransactions.add(transaction);
            }
        }
        return paymentTransactions;
    }

    public void setAllTransactions(List<Transaction> allTransactions) {
        this.allTransactions = allTransactions; //Tbh this only exists because when I took it out, things would break.
    } //It's a load-bearing artifact.

}

