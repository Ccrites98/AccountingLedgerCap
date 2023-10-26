package com.pluralsight;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

                            //Get ready for a terrible layout.
public class Transaction {

    private String description;
    private String vendor;
    private double amount;
    private LocalDateTime transactionDateTime;
    private boolean isPayment;
    private boolean isDeposited;
                //Booleans are nice. I used it because something either Is or Isn't, and it made sense to me.
    public Transaction(String transactionDateTime, String description, String vendor, double amount) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
            this.transactionDateTime = LocalDateTime.parse(transactionDateTime, formatter);
        }catch (DateTimeParseException e) {
            e.printStackTrace();}
        //I needed the catch because it wouldn't parse the DateTime in the CSV file.
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.isDeposited = detectIsDeposited(description, vendor, amount);
        this.isPayment = detectIsPayment(description, vendor, amount);
    }
    private boolean detectIsDeposited(String description, String vendor, double amount) {
        String upperDesc = description.toUpperCase();
        return upperDesc.contains("DEPOSIT");
    }
    private boolean detectIsPayment(String description, String vendor, double amount) {
        String upperDesc = description.toUpperCase();
        return upperDesc.contains("PAYMENT") || upperDesc.contains("DEBIT");
    }

//My Get/Sets down below, unorganized as I threw them in while I was trying to get to the end of the project.
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }
    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }


    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isDeposited(){
        return isDeposited;
    }
    public void setDeposited(boolean isDeposited) {
        this.isDeposited = isDeposited;
    }
    public boolean isPayment() {
        return isPayment;
    }
    public void setPayment(boolean isPayment) {
        this.isPayment = isPayment;
    }
    public String toCSVString() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
        String formattedDateTime = dateTimeFormat.format(new Date());
        return formattedDateTime + "|" + description + "|" + vendor + "|" + amount + "\n";
    } //These two methods are for the CVS file to be formatted correctly.
    public static Transaction fromCSVString(String csv) {
        String[] parts = csv.split("\\|");
        if (parts.length >= 3) {
            String dateTime = parts[0];
            String description = parts[1];
            String vendor = parts[2];
            double amount = 0.0;

            if (parts.length >= 4) {
                if (dateTime.contains("T")) {
                    // Literally just doing this to get the T to stop showing up.
                    //This T was the bone in my throat.
                    dateTime = dateTime.replace("T", "|");
                }
                amount = Double.parseDouble(parts[3]);
            }
            return new Transaction(dateTime, description, vendor, amount);
        }
        return null;
    }
}


