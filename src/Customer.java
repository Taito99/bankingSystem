public class Customer {
    private final String firstname;
    private final String lastname;
    private final String ssn;
    private final Account account;

    public Customer(String firstname, String lastname, String ssn, Account account) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.account = account;
    }

    @Override
    public String toString() {
        return "\nCustomer information\n" +
                "First name: " + firstname + "\n" +
                "Last name: " + lastname + "\n" +
                "SSN: " + ssn +
                account;
    }

    public String basicInfo() {
        return " First name: " + firstname + "\n" +
                " Last name: " + lastname + "\n" +
                " SSN: " + ssn +
                " Account number" + account.getAccountNumber();
    }

    Account getAccount() {
        return account;
    }
}
