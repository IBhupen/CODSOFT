import java.util.Scanner;

// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Method to run the ATM interface
    public void run() {
        while (true) {
            System.out.println("1. Account Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transferFunds();
                    break;
                case 5:
                    changePIN();
                    break;
                case 6:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to check the account balance
    private void checkBalance() {
        System.out.println("Your account balance is: " + account.getBalance());
    }

    // Method to withdraw amount from the account
    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
        } else {
            account.withdraw(amount);
            System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
        }
    }

    // Method to deposit amount into the account
    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    // Method to transfer funds from the account
    private void transferFunds() {
        System.out.print("Enter recipient account number: ");
        String recipientAccount = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
        } else {
            account.withdraw(amount);
            System.out.println("Transfer successful. Remaining balance: " + account.getBalance());
            // Add logic to transfer funds to the recipient account
        }
    }

    // Method to change the PIN
    private void changePIN() {
        System.out.print("Enter new PIN: ");
        String newPIN = scanner.nextLine();
        System.out.print("Confirm new PIN: ");
        String confirmPIN = scanner.nextLine();

        if (newPIN.equals(confirmPIN)) {
            account.setPIN(newPIN);
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("PIN mismatch. Please try again.");
        }
    }
}

// Class to represent the user's bank account
class BankAccount {
    private double balance;
    private String PIN;

    public BankAccount(double balance, String PIN) {
        this.balance = balance;
        this.PIN = PIN;
    }

    // Method to get the account balance
    public double getBalance() {
        return balance;
    }

    // Method to withdraw amount from the account
    public void withdraw(double amount) {
        balance -= amount;
    }

    // Method to deposit amount into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to set the PIN
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    // Method to get the PIN
    public String getPIN() {
        return PIN;
    }
}

// Main class to run the ATM interface
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0, "1234");
        ATM atm = new ATM(account);

        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Insert your card:");
                String enteredCardNumber = scanner.nextLine();

                System.out.println("Enter your PIN:");
                String enteredPin = scanner.nextLine();

                if (authenticate(enteredCardNumber, enteredPin, account)) {
                    System.out.println("Card inserted successfully. Welcome!");
                    atm.run();
                } else {
                    System.out.println("Invalid card number or PIN. Please try again.");
                }
            }
        } finally {
            scanner.close(); // Close the scanner to prevent resource leaks
        }
    }

    // Method to authenticate the card number and PIN
    public static boolean authenticate(String cardNumber, String pin, BankAccount account) {
        // In a real ATM, this would verify the card number and PIN with the bank's database
        // For this simulation, we'll just check if the card number and PIN match the account's details
        return cardNumber.equals("1234") && pin.equals(account.getPIN());
    }
}