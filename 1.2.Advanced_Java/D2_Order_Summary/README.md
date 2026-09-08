# Order Management System - CLI Application
 
## Overview
 
This is a command-line application that demonstrates key Java concepts including:
 
- Stream pipelines for filtering, grouping, and aggregating data
- Method references and lambdas for functional programming
- Generics for type-safe collections
- Optional for null safety
- Records for immutable data carriers
- Custom comparators for sorting
The application simulates a small store's order management system where users can:
 
- Add orders (paid or unpaid) to the system
- View revenue reports showing only paid orders
- Switch between different customer accounts
## How to Run the Application
 
### 1. Navigate to the Project Directory
 
Open your terminal and navigate to the `D2_Order_Summary` directory:
 
```bash
cd ~/Desktop/teachable_bootcamp/1.2.Advanced_Java/D2_Order_Summary
```
 
### 2. Compile and Run
 
Compile all Java files:
 
```bash
javac *.java
```
 
Run the application:
 
```bash
java Main
```
 
**Note:** If you're using Java 17 or later, you can run directly with:
 
```bash
java Main.java
```
 
## Available Customer Accounts
 
The system has 10 pre-configured customer accounts. You can log in using any of these emails:
 
| ID | Customer Name      | Email             |
|----|---------------------|-------------------|
| 1  | Ada Lovelace         | ada@gmail.com     |
| 2  | Ben Franklin          | ben@gmail.com     |
| 3  | Clara Barton          | clara@gmail.com   |
| 4  | David Hume            | david@gmail.com   |
| 5  | Elena Garcia          | elena@gmail.com   |
| 6  | Felix Mendelssohn      | felix@gmail.com   |
| 7  | Grace Hopper           | grace@gmail.com   |
| 8  | Hannah Arendt          | hannah@gmail.com  |
| 9  | Isaac Newton           | isaac@gmail.com   |
| 10 | Jane Austen            | jane@gmail.com    |
 
## Application Walkthrough
 
### 1. Authentication
 
When you start the application, you'll be prompted to enter your email:
 
```text
Welcome to the Order Management System!
 
Please enter your email to continue:
>
```
 
Enter any email from the list above (case-insensitive). For example:
 
```text
> ada@gmail.com
```
 
If the email is valid, you'll see:
 
```text
Welcome, Ada Lovelace!
```
 
If the email is not found, the application will exit.
 
### 2. Main Menu
 
After authentication, you'll see the main menu with four options:
 
```text
 Main Menu 
1. Specify Order
2. View total paid amount
3. Switch email account
4. Exit
Enter your choice:
```
 
### 3. Specify Order (Option 1)
 
Add a new order to the system. You'll be prompted for:
 
**Status:** Choose between paid (1) or unpaid (2)
 
```text
Enter order status (1 for PAID, 2 for UNPAID):
> 1
```
 
**Amount:** Enter a positive number
 
```text
Enter order amount:
> 35.00
```
 
The system will auto-generate an order ID (`O-100`, `O-101`, etc.) and confirm:
 
```text
Order O-100 (paid, $35.00) added successfully!
```
 
### 4. View Revenue Report (Option 2)
 
Display a report showing:
 
- **Per-customer totals** - Only customers with paid orders appear
- **Grand total** - Sum of all paid orders
Example output:
 
```text
 Revenue Report
 
Ada Lovelace — $35.00
Ben Franklin — $25.00
----------------------------
Total — $60.00
```
 
Key behaviors to observe:
 
- Only paid orders are included
- Customers are sorted by highest total first
- If two customers have the same total, they're sorted by customer ID (ascending)
- Customers with no paid orders are absent from the report
- Shows "No paid orders yet" if none exist
### 5. Switch Email Account (Option 3)
 
Switch to a different customer account without restarting the application.
 
The system shows all available accounts:
 
```text
 Switch Account 
Current user: Ada Lovelace (ada@gmail.com)
 
Available accounts:
  1. Ada Lovelace (ada@gmail.com)
  2. Ben Franklin (ben@gmail.com)
  3. Clara Barton (clara@gmail.com)
  ... and so on
 
Enter email address to switch to:
```
 
Enter any valid email to switch accounts.
 
### 6. Exit (Option 4)
 
Exit the application:
 
```text
Goodbye!
```
 
## Testing Rubrics
 
To verify the application meets all requirements, test the following scenarios:
 
### Test 1: Filtering - Only Paid Orders Appear
 
**Steps:**
 
1. Add some paid orders (status 1)
2. Add some unpaid orders (status 2)
3. View the revenue report (Option 2)
**Expected Result:** Only paid orders appear in the report. Unpaid orders are completely ignored.
 
### Test 2: Sorting - Highest Amounts First
 
**Steps:**
 
1. Add paid orders with different amounts:
   - Customer A: $100
   - Customer B: $50
   - Customer C: $75
2. View the revenue report (Option 2)
**Expected Result:** Customers appear in order: $100, $75, $50 (highest first).
 
### Test 3: Tie-Breaking - Customer ID
 
**Steps:**
 
1. Add paid orders for different customers with the same amount:
   - Customer 3 (Clara): $50
   - Customer 7 (Grace): $50
2. View the revenue report (Option 2)
**Expected Result:** Customer 3 appears before Customer 7 (smaller ID first).
 
### Test 4: Absence Handling - No Zeros
 
**Steps:**
 
1. Log in as a customer (e.g., Jane Austen)
2. Add unpaid orders only (no paid orders)
3. View the revenue report (Option 2)
**Expected Result:** Jane Austen does not appear in the report as "0.00". She's simply absent.
 
### Test 5: Empty Report Handling
 
**Steps:**
 
1. Start the application fresh (or delete all orders by restarting)
2. View the revenue report (Option 2) without adding any orders
**Expected Result:** Shows "No paid orders yet. Total: 0.00"
 
### Test 6: Multiple Orders Per Customer
 
**Steps:**
 
1. Log in as one customer (e.g., Ada Lovelace)
2. Add multiple paid orders:
   - $35.00
   - $25.00
3. View the revenue report (Option 2)
**Expected Result:** Ada Lovelace shows a combined total of $60.00
 
### Test 7: Account Switching
 
**Steps:**
 
1. Log in as Customer A
2. Add some orders for Customer A
3. Switch to Customer B (Option 3)
4. Add orders for Customer B
5. View the revenue report (Option 2)
**Expected Result:** Both customers appear with their respective totals. The report shows all paid orders from all customers.
 
### Test 8: Grand Total Calculation
 
**Steps:**
 
1. Add paid orders for multiple customers
2. View the revenue report (Option 2)
**Expected Result:** The "Total" line shows the sum of all paid orders.
 