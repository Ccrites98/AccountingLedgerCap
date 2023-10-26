package com.pluralsight;
import java.time.YearMonth;
import java.util.List;


public class PreviousYear {
    public void generateReport(List<Transaction> transactions, YearMonth currentYearMonth) {
        int previousYear = currentYearMonth.getYear() - 1;
            //This is obviously for the PreviousYear function back on the LedgerScrn Menu.
        List<Transaction> filteredTransactions = filterByYear(transactions, previousYear);

        displayEntries(filteredTransactions);

        double totalDeposits = calculateTotalDeposits(filteredTransactions);
        double totalPayments = calculateTotalPayments(filteredTransactions);

        System.out.println("Sum of all Deposits: " + totalDeposits);
        System.out.println("Sum of all Payments: " + totalPayments);
    }

    private List<Transaction> filterByYear(List<Transaction> transactions, int year) {
        //Necessary for the GenerateReport above.
        return transactions;
    }

    private void displayEntries(List<Transaction> transactions) {
        //Necessary for the generatereport above.
    }

    private double calculateTotalDeposits(List<Transaction> transactions) {
        double totalDeposits = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.isDeposited()) {
                totalDeposits += transaction.getAmount();
            }
        }
        return totalDeposits;
    }
//These are supposed to run my calculations, but I ran out of time on them.
    private double calculateTotalPayments(List<Transaction> transactions) {
        double totalPayments = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                totalPayments += transaction.getAmount();
            }
        }
        return totalPayments;
    }

}