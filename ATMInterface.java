import java.util.Scanner;

public class ATMInterface {
    private static final int MAX_ATTEMPTS = 3;
    private static int attempts = 0;
    private static boolean loggedIn = false;
    private static double balance = 1000.0;
    private static boolean runATM = true;
    private static String cardNumber = "1234";
    private static String pin = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (runATM) {
                if (!loggedIn) {
                    System.out.print("Enter card number: ");
                    String enteredCardNumber = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    String enteredPin = scanner.nextLine();
                    if (authenticate(enteredCardNumber, enteredPin)) {
                        loggedIn = true;
                    } else {
                        attempts++;
                        if (attempts >= MAX_ATTEMPTS) {
                            System.out.println("Maximum attempts reached. Exiting.");
                            runATM = false;
                        }
                    }
                } else {
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
                            AccountBalanceInquiry();
                            break;
                        case 2:
                            CashWithdrawal(scanner);
                            break;
                        case 3:
                            Deposit(scanner);
                            break;
                        case 4:
                            TransferFunds(scanner);
                            break;
                        case 5:
                            ChangePIN(scanner);
                            break;
                        case 6:
                            System.out.println("Exiting. Goodbye!");
                            runATM = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        } finally {
            scanner.close(); // Close the scanner to prevent resource leaks
        }
    }

    private static boolean authenticate(String enteredCardNumber, String enteredPin) {
        return enteredCardNumber.equals(cardNumber) && enteredPin.equals(pin);
    }

    private static void AccountBalanceInquiry() {
        System.out.println("Your account balance is: " + balance);
    }

    private static void CashWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        }
    }

    private static void Deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    private static void TransferFunds(Scanner scanner) {
        System.out.print("Enter recipient account number: ");
        String recipientAccount = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Transfer successful. Remaining balance: " + balance);
            // Add logic to transfer funds to the recipient account
        }
    }

    private static void ChangePIN(Scanner scanner) {
        System.out.print("Enter new PIN: ");
        String newPIN = scanner.nextLine();
        System.out.print("Confirm new PIN: ");
        String confirmPIN = scanner.nextLine();
        if (newPIN.equals(confirmPIN)) {
            pin = newPIN; // Update the PIN
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("PIN mismatch. Please try again.");
        }
    }
}
