package com.pluralsight;
import java.io.*;
import java.util.*;

public class Transactor {
    private String fileName;

    public Transactor() {
        this.fileName = fileName;
    }
    //To path the way to the Transactions.csv
    public List<Transaction> readTransactions() {
        List<Transaction> transactions = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) { //Gotta be able to read the file.
                Transaction transaction = Transaction.fromCSVString(line);
                if (transaction != null) {
                    transactions.add(transaction);
                } else {
                    System.err.println("Error: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Sorry, file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("error: " + e.getMessage());
        }

        return transactions;
    }
//You might be wondering why I have the other CSV function here instead of with the other 2.
    //I have no idea. I was very feverish when I did this.
    public void writeTransactionToCSV(String fileName, Transaction transaction) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String csvLine = String.format(
                    "%s|%s|%s|%.2f%n",
                    transaction.getTransactionDateTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()
            );
            writer.write(csvLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}