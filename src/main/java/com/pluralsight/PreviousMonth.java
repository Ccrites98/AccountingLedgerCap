package com.pluralsight;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class PreviousMonth {

                        //This screen is for the PreviousMonth function on the LedgerScrn Menu.
    public void generateReport(List<Transaction> transactions, YearMonth currentYearMonth) {
        int previousMonth = (currentYearMonth.getMonthValue() == 1) ? 12 : currentYearMonth.getMonthValue() - 1;
        int previousYear = (currentYearMonth.getMonthValue() == 1) ? currentYearMonth.getYear() - 1 : currentYearMonth.getYear();
                        //All of this is to calculate total deposits and payments for these transactions,
                        // and is supposed to print the results to the console.
        List<Transaction> filteredTransactions = filterByMonthAndYear(transactions, previousMonth, previousYear);

        displayEntries(filteredTransactions);

        double totalDeposits = calculateTotalDeposits(filteredTransactions);
        double totalPayments = calculateTotalPayments(filteredTransactions);

        System.out.println("Sum of the Deposits: " + totalDeposits);
        System.out.println("Sum of the Payments: " + totalPayments);
    }

    private List<Transaction> filterByMonthAndYear(List<Transaction> transactions, int month, int year) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionDateTime().getYear() == year
                    && transaction.getTransactionDateTime().getMonthValue() == month) {
                filteredTransactions.add(transaction);
            }}
        return filteredTransactions;}

    private void displayEntries(List<Transaction> transactions) {
        System.out.println("Your Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toCSVString());
        }
    }
    private double calculateTotalDeposits(List<Transaction> transactions) {
        double totalDeposits = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isDeposited()) {
                totalDeposits += transaction.getAmount();
            }
        }
        return totalDeposits;
    }//Nonfunctioning calculators. Was supposed to return to these but I ran out of time on Thursday.
    private double calculateTotalPayments(List<Transaction> transactions) {
        double totalPayments = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                totalPayments += transaction.getAmount();
            }}
        return totalPayments;
    }}