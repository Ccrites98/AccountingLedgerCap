package com.pluralsight;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendorSearch { //At least this one worked. This is for searching by vendor.
    public void generateReport(List<Transaction> transactions) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search the vendor here?: ");
        String vendorName = scanner.nextLine();

        List<Transaction> filteredTransactions = filterByVendor(transactions, vendorName);

        displayEntries(filteredTransactions);
        //The FilteredTransactions are self-explanatory right?
    }
    private List<Transaction> filterByVendor(List<Transaction> transactions, String vendorName) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                filteredTransactions.add(transaction);
            }}
        return filteredTransactions;}
    private void displayEntries(List<Transaction> transactions) {
        System.out.println("Results for Vendor:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toCSVString());
        }}}