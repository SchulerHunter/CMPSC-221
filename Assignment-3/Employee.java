interface compensation {
    public double earnings();
    public void raise (double percent);
}

abstract class CompensationModel implements compensation {}

class SalariedCompensationModel extends CompensationModel {
    // Declare class variables
    private double weeklySalary;

    //Declare constructor
    public SalariedCompensationModel(double weeklySalary) {
        this.setWeeklySalary(weeklySalary);
    }

    // Declare setter and getters
    public double getWeeklySalary() {
        return this.weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    @Override
    public void raise(double percent) {
        this.setWeeklySalary(this.getWeeklySalary()+this.getWeeklySalary()*percent);
    }

    @Override
    public double earnings() {
        return weeklySalary;
    }

    @Override
    public String toString() {
        return String.format(
                "Salaried Compensation with:%nWeekly Salary of: %.2f%nEarnings: %.2f",
                this.getWeeklySalary(), this.earnings());
    }

}

class HourlyCompensationModel extends CompensationModel {
    // Declare private class variables
    private double wage;
    private double hours;

    // Declare constructor
    public HourlyCompensationModel(double wage, double hours) {
        this.setWage(wage);
        this.setHours(hours);
    }

    // Declare setter and getters
    public double getWage() {
        return this.wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getHours() {
        return this.hours;
    }
    
    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public void raise(double percent) {
        this.setWage(this.getWage()+this.getWage()*percent);
    }

    @Override
    public double earnings() {
        if (this.hours <= 40) {
            return this.hours*this.wage;
        } else {
            return 40*this.wage+(this.hours-40)*(this.wage*1.5);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Hourly Compensation with:%nWage of: %.2f%nHours Worked of: %.2f%nEarnings: %.2f",
                this.getWage(), this.getHours(), this.earnings());
    }

}

class CommissionCompensationModel extends CompensationModel {
    // Declare variables as private
    private double grossSales;
    private double commissionRate;

    // Declare Constructor
    public CommissionCompensationModel(double grossSales, double commissionRate) {
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

    @Override
    public void raise(double percent) {
        this.setCommissionRate(this.getCommissionRate()+this.getCommissionRate()*percent);
    }

    // Declare other class methods and overrides
    @Override
    public double earnings() {
        return (this.getGrossSales() * this.getCommissionRate());
    }

    @Override
    public String toString() {
        return String.format(
                "Commission Compensation with:%nGross Sales of: %.2f%nCommission Rate of: %.3f%nEarnings: %.2f",
                this.getGrossSales(), this.getCommissionRate(), this.earnings());
    }
}

class BasePlusCommissionCompensationModel extends CommissionCompensationModel {
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
    
    @Override
    public void raise(double percent) {
        this.setBaseSalary(this.getBaseSalary()+this.getBaseSalary()*percent);
        this.setCommissionRate(this.getCommissionRate()+this.getCommissionRate()*percent);
    }

    // Override toString
    @Override
    public double earnings() {
        return ((this.getGrossSales() * this.getCommissionRate()) + this.getBaseSalary());
    }

    @Override
    public String toString() {
        return String.format(
                "Base Plus Commission Compensation with:%nGross Sales of: %.2f%nCommission Rate of: %.3f%nBase Salary of: %.2f%nEarnings: %.2f",
                this.getGrossSales(), this.getCommissionRate(), this.getBaseSalary(), this.earnings());
    }
}

public class Employee {
    // Declare class variables
    private String firstName;
    private String lastName;
    private String SSN;
    private CompensationModel compensation;

    // Declare constructor
    public Employee(String firstName, String lastName, String SSN, CompensationModel compensation){
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

    public CompensationModel getCompensation() {
        return this.compensation;
    }

    public void setCompensation(CompensationModel compensation) {
        this.compensation = compensation;
    }

    // Declare other class methods and overrides
    public double earnings(){
        return this.compensation.earnings();
    }

    public void raise(double percent){
        this.compensation.raise(percent);
    }

    @Override
    public String toString() {
        return String.format("%s%nSocial Security Number: %s%n%s",
            this.getFirstName() + " " + this.getLastName(), this.getSSN(), this.compensation);
    }

    public static void main(String[] Args){
// Create the four employees with their compensation models.
       
        CommissionCompensationModel commissionModel = new CommissionCompensationModel(2000.00, 0.04);
        BasePlusCommissionCompensationModel basePlusCommissionModel = new BasePlusCommissionCompensationModel(2000.00, 0.05, 600.00);
        SalariedCompensationModel salariedCompensationModel = new SalariedCompensationModel(2500.00);
        HourlyCompensationModel hourlyCommissionModel = new HourlyCompensationModel(10.00, 35.0);
       
        Employee employee1 = new Employee("John", "Smith", "111-11-1111", commissionModel);
        Employee employee2 = new Employee("Sue", "Jones", "222-22-2222", basePlusCommissionModel);
        Employee employee3 = new Employee("Jim", "Williams", "333-33-3333", salariedCompensationModel);
        Employee employee4 = new Employee("Nancy", "Johnson", "444-44-4444", hourlyCommissionModel);
       
        // Print the information about the four employees.
        System.out.println("The employee information initially.");
        System.out.printf("%s%n%s%n%s%n%s%n", employee1, employee2, employee3, employee4);
        System.out.printf("%s%s%s%s%s%8.2f%n%n", "Earnings for ", employee1.getFirstName(), " ", employee1.getLastName(), ": ", employee1.earnings());
       
        // Change the compensation model for the four employees.
       
        CommissionCompensationModel commissionModelNew = new CommissionCompensationModel(5000.00, 0.04);
        BasePlusCommissionCompensationModel basePlusCommissionModelNew = new BasePlusCommissionCompensationModel(4000.00, 0.05, 800.00);
        SalariedCompensationModel salariedCompensationModelNew = new SalariedCompensationModel(3500.00);
        HourlyCompensationModel hourlyCommissionModeNewl = new HourlyCompensationModel(10.00, 50);
       
        // Set the new compensation models for the employees.
        employee1.setCompensation(basePlusCommissionModelNew);
        employee2.setCompensation(commissionModelNew);
        employee3.setCompensation(hourlyCommissionModeNewl);
        employee4.setCompensation(salariedCompensationModelNew);
       
        // Print out the new information for the four employees.
        System.out.println("The employee information after changing compensation models.");
        System.out.printf("%s%n%s%n%s%n%s%n", employee1, employee2, employee3, employee4);
       
        // Declare an array of employees and assign the four employees to it.
        Employee[] employees = new Employee[4];
        employees[0] = employee1;
        employees[1] = employee2;
        employees[2] = employee3;
        employees[3] = employee4;
    
        // Loop thru the array giving each employee a 2% raise polymorphically;
        for (Employee employee : employees)
        {
            employee.raise(.02);
        }
       
        // Print out their new earnings.
        System.out.println("The employee information after raises of 2 percent.");
        System.out.printf("%s%n%s%n%s%n%s%n", employee1, employee2, employee3, employee4);
    }
}