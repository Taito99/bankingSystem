public class Checking extends Account{
    public static String accountType = "Checking";

    Checking(double initialDeposit){
        super();
        this.setBalance(initialDeposit);
        this.checkInterest(0);
    }

    @Override
    public String toString(){
        return "Account Type: " + accountType + "Account\n" +
                "Account number: " + this.getAccountNumber() + "\n" +
                "Balance" + this.getBalance() + "\n" +
                "Interest Rate" + this.getInterest() + "%\n";
    }
}
