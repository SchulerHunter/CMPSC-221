public class employee {
    // Declare class variables
    private String firstName;
    private String lastName;
    private String SSN;
    private CommissionCompensationModel compensation;

    // Declare constructor
    public employee(String firstName, String lastName, String SSN, CommissionCompensationModel compensation){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setSSN(SSN);
        this.setCompensation(compensation);
    }

    // Declare setter and getters
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSSN() {
        return this.SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public CommissionCompensationModel getCompensation() {
        return this.compensation;
    }

    public void setCompensation(CommissionCompensationModel compensation) {
        this.compensation = compensation;
    }

    // Declare other class methods and overrides
    public double earnings(){
        return this.compensation.earnings();
    }

    @Override
    public String toString() {
        return String.format("%s%nSocial Security Number: %s%n%s",
            this.getFirstName() + " " + this.getLastName(), this.getSSN(), this.compensation);
    }

    // Declare ComissionCompensationModel and extended class BasePlusComissionCompensationModel
    static class CommissionCompensationModel {
        // Declare variables as private
        private double grossSales;
        private double commissionRate;

        // Declase Constructor
        public CommissionCompensationModel (double grossSales, double commissionRate) {
            this.setGrossSales(grossSales);
            this.setCommissionRate(commissionRate);
        }

        // Provide setter and getters for variables
        public double getGrossSales() {
            return this.grossSales;
        }

        public void setGrossSales(double grossSales) {
            this.grossSales = grossSales;
        }

        public double getCommissionRate() {
            return this.commissionRate;
        }

        public void setCommissionRate(double commissionRate) {
            this.commissionRate = commissionRate;
        }

        // Declare other class methods and overrides
        public double earnings() {
            return (this.getGrossSales() * this.getCommissionRate());
        }

        @Override
        public String toString() {
            return String.format("Commission Compensation with:%nGross Sales of: %.2f%nCommission Rate of: %.2f%nEarnings: %.2f",
                this.getGrossSales(), this.getCommissionRate(), this.earnings());
        }
    }

    static class BasePlusCommissionCompensationModel extends CommissionCompensationModel {
        // Declare variable as private
        private double baseSalary;

        // Declare Constructor
        public BasePlusCommissionCompensationModel(double grossSales, double commissionRate, double baseSalary) {
            super(grossSales, commissionRate);
            this.baseSalary = baseSalary;
        }

        // Provide setter and getters for variables
        public double getBaseSalary() {
            return this.baseSalary;
        }

        public void setBaseSalary(double baseSalary) {
            this.baseSalary = baseSalary;
        }
        
        // Override toString
        @Override
        public double earnings() {
            return ((this.getGrossSales() * this.getCommissionRate()) + this.getBaseSalary());
        }

        @Override
        public String toString() {
            return String.format("Base Plus Commission Compensation with:%nGross Sales of: %.2f%nCommission Rate of: %.2f%nBase Salary of: %.2f%nEarnings: %.2f",
                this.getGrossSales(), this.getCommissionRate(), this.getBaseSalary(), this.earnings());
        }

    }

    public static void main(String[] Args){
        // Create the two employees with their compensation models.

        CommissionCompensationModel commissionModel = new CommissionCompensationModel(2000.00, 0.04);
        BasePlusCommissionCompensationModel basePlusCommissionModel = new BasePlusCommissionCompensationModel(2000.00,0.05, 600.00);

        employee employee1 = new employee("John", "Smith", "111-11-1111", commissionModel);
        employee employee2 = new employee("Sue", "Jones", "222-22-2222", basePlusCommissionModel);

        System.out.printf("%s%n%s%n", employee1, employee2);
        System.out.printf("%s%s%s%s%s%8.2f%n%n", "Earnings for ", employee1.getFirstName(), " ", employee1.getLastName(), ": ", employee1.earnings());

        // Change the compensation model for the two employees.

        CommissionCompensationModel commissionModelNew = new CommissionCompensationModel(5000.00, 0.04);
        BasePlusCommissionCompensationModel basePlusCommissionModelNew = new BasePlusCommissionCompensationModel(4000.00, 0.05, 800.00);

        // Set the new compensation models for the employees.
        employee1.setCompensation(basePlusCommissionModelNew);
        employee2.setCompensation(commissionModelNew);

        // Print out the new information for the two employees.
        System.out.printf("%s%n%s%n", employee1, employee2);
    }
}