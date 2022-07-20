import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    // Instance Variables
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    public void runMenu(){
        printHeader();
        while (!exit) {
            printMenu();
            int userChoice = getInput();
            performAction(userChoice);
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------+");
        System.out.println("|            Welcome to                 |");
        System.out.println("|            Bank Management System                 |");
        System.out.println("+-----------------------------+");
    }
    private void performAction(int choice) {
        switch (choice) {
            case 0 -> {
                System.out.println("Thank you for using our application.");
                System.exit(0);
            }
            case 1 -> createAccount();
            case 2 -> makeDeposit();
            case 3 -> makeWithdraw();
            case 4 -> listBalances();
            default -> System.out.println("Unknown error has occurred.");
        }
    }

    private void createAccount() {
        String firstname, lastname, ssn, accountType = "";
        double initialDeposit = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Please enter an account type(checking/savings) ");
            accountType = keyboard.nextLine();
            if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")){
                valid = true;

            }
            else {
                System.out.println("Invalid account type. Please type checking or savings");
            }
        }

        System.out.print("please enter  your first name: ");
        firstname = keyboard.nextLine();
        System.out.print("please enter  your last name: ");
        lastname = keyboard.nextLine();
        System.out.print("please enter  your ssn: ");
        ssn = keyboard.nextLine();
        valid = false;

        while (!valid) {
            System.out.print("Please enter an initial deposit: ");
            try {
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Deposit invalid please type a number");
            }
            if (accountType.equalsIgnoreCase("checking")) {
                if (initialDeposit < 100) {
                    System.out.println("Checking accounts require minimum 100$ to open");
                }
                else {
                    valid = true;
                }
            }
            else if (accountType.equalsIgnoreCase("savings")){
                if (initialDeposit < 50) {
                    System.out.println("Savings accounts require minimum 50$ to open");
                }
                else {
                    valid = true;
                }
            }
        }

        Account account;
        if (accountType.equalsIgnoreCase("checking")) {
            account = new Checking(initialDeposit);
        }
        else {
            account = new Savings(initialDeposit);
        }

        Customer customer = new Customer(firstname, lastname, ssn, account);

        bank.addCustomer(customer);
    }

    private void makeDeposit() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.print("How much would you like to deposit?: ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                account = 0;
            }

            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        if(customers.size() <= 0){
            System.out.println("No customers at your bank");
            return -1;
        }
        System.out.println("Select an account");
        for (int i = 0; i < customers.size(); i++){
            System.out.println((i + 1) + ") " + customers.get(i).basicInfo());
        }
        int account;
        System.out.print("Please enter your selection: ");
        try {
            account = Integer.parseInt(keyboard.nextLine()) - 1;
        }catch (NumberFormatException e) {
            account = -1;
        }
        if (account < 0 || account > customers.size()) {
            System.out.println("Invalid account selected.");
            account -= -1;
        }

        return account;
    }

    private void makeWithdraw() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.print("How much would you like to withdraw?: ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                account = 0;
            }
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
    }

    private void listBalances() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.print(bank.getCustomer(account).getAccount());
        }
    }

    private int getInput() {
        int choice = -1;

        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > 4) {
                System.out.println("Choice outside of range. Please try again");
            }
        }while (choice < 0 || choice > 4);
        return choice;
    }

    private void printMenu() {
        System.out.println("Please make a selection.");
        System.out.println("1)Create a new Account.");
        System.out.println("2) Make a deposit.");
        System.out.println("3) Make a withdraw.");
        System.out.println("4) List account balance.");
        System.out.println("0)Exit.");
    }


}
