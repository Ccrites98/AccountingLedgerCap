package com.pluralsight;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MonthToDateReport {

    public void generateReport(List<Transaction> transactions, YearMonth currentYearMonth) {
        List<Transaction> filteredTransactions = filterByMonthAndYear(transactions, currentYearMonth);

        displayEntries(filteredTransactions);

        double totalDeposits = calculateTotalDeposits(filteredTransactions);
        double totalPayments = calculateTotalPayments(filteredTransactions);

        System.out.println("Sum of all deposits for " + currentYearMonth + ": " + totalDeposits);
        System.out.println("Sum of all payments for " + currentYearMonth + ": " + totalPayments);
    }
    private List<Transaction> filterByMonthAndYear(List<Transaction> transactions, YearMonth targetYearMonth) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            YearMonth transactionYearMonth = YearMonth.from(transaction.getTransactionDateTime());
            if (transactionYearMonth.equals(targetYearMonth)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    private void displayEntries(List<Transaction> transactions) {
        System.out.println("Month-to-Date Report:");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this month.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toCSVString());
            }}}
    private double calculateTotalDeposits(List<Transaction> transactions) {
        double totalDeposits = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.isDeposited()) {
                totalDeposits += transaction.getAmount();
            }}
        return totalDeposits;}
    private double calculateTotalPayments(List<Transaction> transactions) {
        double totalPayments = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                totalPayments += transaction.getAmount();
            }}
        return totalPayments;}}