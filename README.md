AccountingLedger:

This Accounting Ledger is my Capstone Java Development project designed to manage financial transactions and provide a basic ledger for personal or small-scale accounting purposes.

This ledger allows one to record and track transactions, categorize them, and calculate balances. Please note that the project is currently not fully functional and is still in development.

------------------------------------------------------------------------
Features:

-Records financial transactions.

-Categorizes transactions (e.g., income, expenses, transfers).

-Calculates account balances.

-Has a fully functioning main menu

-A fully functioning Ledger Screen

------------------------------------------------------------
Structure
Java Classes

AccountingLedger: Main Menu for the application

LedgerScrn: Ledger Menu for the application (handles reports, payments, and deposits).

PreviousMonth: The code that allows the user to view all entries sorted from the previous month.

MonthToDate: The code that allows the user to sort Month to Date entries in the ledger.

PreviousYear: The code that allows the user to view all entries sorted by the previous year.

VendorSearch: Allows the user to sort the transactions in the ledger by Vendor name.

Transaction: Handles the vast majority of the code necessary for user inputted transactions. Also, formats the input to be compatible with the CVS file.

Transactor: The code that reads what's been inputted on the CVS file.

YearToDate: WIP, but should allow the user to sort entries by Year.

---------------------------------------------------------------------------------

Usage/Examples:
Run the application by executing the main script: AccountingLedger.Java

Use the application to record transactions, categorize them, and view balances.

Use the Main Menu to navigate between the Ledger Screen, to record making deposits, or to record making payments.

Use the Ledger Screen to sort and filter previous logged transactions, and access the Report Menu.

Use the Report Menu to get reports on transactions in a variety of formats.

Exit again by navigating back to the Main Menu and using the provided exit key.

Acknowledgements:

For learning how to build the ReadMe:
https://readme.so/editor 

Refresher on maintaining a list of transactions:
https://stackoverflow.com/questions/51207510/what-is-the-best-way-to-maintain-a-list-of-transactions-in-java-transaction-obj

For learning how to generate reports:
https://stackoverflow.com/questions/22792139/how-do-i-generate-reports-in-java

A refresher on building Array Lists:
https://www.w3schools.com/java/java_arraylist.asp
----------------------------------------------------------------------------------
Author:
Christian Crites
Ccrites98
