package com.pluralsight;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class YearToDate { //Nonfunctional. I ran out of time on this one.
    public void generateReport(List<Transaction> transactions, YearMonth currentYearMonth) {
        List<Transaction> filteredTransactions = filterByYear(transactions, currentYearMonth);

        displayEntries(filteredTransactions);

        double totalDeposits = calculateTotalDeposits(filteredTransactions);
        double totalPayments = calculateTotalPayments(filteredTransactions);

        System.out.println("Sum of all Deposits: " + totalDeposits);
        System.out.println("Sum of all Payments: " + totalPayments);
    }

    private void displayEntries(List<Transaction> transactions) {
        System.out.println("All filtered transactions here:");
        if (transactions.isEmpty()) {
            System.out.println("Nope, nothing found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toCSVString());
            }
        }
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

    private double calculateTotalPayments(List<Transaction> transactions) {
        double totalPayments = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                totalPayments += transaction.getAmount();
            }
        }
        return totalPayments;
    }
    private List<Transaction> filterByYear(List<Transaction> transactions, YearMonth currentYearMonth) {
        return transactions;
    }

}