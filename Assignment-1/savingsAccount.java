public class savingsAccount {
    // Store annualInterestRate as static to be used along all classes
    // Store both variables as private so they can only be accessed with setter and getter
    private static double annualInterestRate;
    private double savingsBalance;

    // Constructor accepting an initial savings balance for the account
    public savingsAccount (double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    // Public static method to set annual interest rate
    public static void setAnnualInterestRate(double interestRate) {
        annualInterestRate = interestRate;
    }

    // Public setter and getter methods for savings balance
    public void setSavingsBalance (double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public double getSavingsBalance() {
        return this.savingsBalance;
    }

    // Public method to calculate and append interest
    public void calculateMonthlyInterest() {
        this.savingsBalance += ((annualInterestRate * savingsBalance) / 12);
    }

    // Test code for savingsAccount class
    public static void main(String[] Args) {
        savingsAccount.setAnnualInterestRate(.04);
        savingsAccount saver1 = new savingsAccount(2000);
        savingsAccount saver2 = new savingsAccount(3000);
        System.out.printf("Month\tSaver1\t\tSaver2\n");
        int i = 0;

        for (;i < 12; i++) {
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
            System.out.printf("%d\t\t%.2f\t\t%.2f\n", i+1, saver1.getSavingsBalance(), saver2.getSavingsBalance());
        }

        savingsAccount.setAnnualInterestRate(.05);
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.printf("%d\t\t%.2f\t\t%.2f\n", i + 1, saver1.getSavingsBalance(), saver2.getSavingsBalance());
    }

}