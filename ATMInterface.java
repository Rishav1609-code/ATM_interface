import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double startingBalance) {
        this.balance = startingBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds. Withdrawal failed.");
        }
    }
}

class ATM {
    private final BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void showMenu() {
        System.out.println("ATM Options:");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    public void processTransaction(int selection, Scanner scanner) {
        switch (selection) {
            case 1:
                System.out.println("Your current balance is: $" + bankAccount.getBalance());
                break;
            case 2:
                System.out.print("Enter the amount to deposit: $");
                double depositAmount = scanner.nextDouble();
                bankAccount.deposit(depositAmount);
                break;
            case 3:
                System.out.print("Enter the amount to withdraw: $");
                double withdrawalAmount = scanner.nextDouble();
                bankAccount.withdraw(withdrawalAmount);
                break;
            case 4:
                System.out.println("Exiting the ATM. Thank you for using our service!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid selection. Please choose a valid option.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your initial balance: $");
        double initialBalance = scanner.nextDouble();
        BankAccount bankAccount = new BankAccount(initialBalance);

        ATM atm = new ATM(bankAccount);

        while (true) {
            atm.showMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            atm.processTransaction(choice, scanner);
        }
    }
}
