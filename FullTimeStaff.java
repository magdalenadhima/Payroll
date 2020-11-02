package payroll;


// FullTimeStaff (subclass) extends Employee.
public abstract class FullTimeStaff extends Employee {

        // create private fields
	private static final double yearlySickDay = 20;
	private final double yearlySalary;
	private double sickDaysLeft;
	
        
        // constructor of Full Time Staff object
	public FullTimeStaff(String eN, String fN, String lN, double yS, double sD) {
            
                // get employee number, first name and last name from employee class (super class)
		super(eN, fN, lN);
                
                //  initializes fields
		this.yearlySalary = yS;
		this.sickDaysLeft = sD;
	}
        
        // return yearly salary
        public double getYearlySalary()
        {
            return yearlySalary;
        } 
        
        // return sick days taken
        public double getSickDaysLeft()
        {
            return sickDaysLeft;
        } 
        
        // return sick days taken
        public double AccessSickDay()
        {
            return yearlySickDay - sickDaysLeft;
        } 
        
        
        @Override
        // returns a string with FullTimeStaff class data formatted so data could be appropriately appended to a file
        public String toString2(){
            
            return super.toString2() + "\n" + "FT" + "\n" + getYearlySalary() + "\n" + getSickDaysLeft();
            }

	public double pay() {
            
            // divide employees yearly salary by 12 to calculate pay 
            // then round number to 2 decimal places
            return Math.round(yearlySalary / 12*100.0)/100.0;
	}

	public void deductSickDay(double d) {
            
            // substract number of sicks days given from user 
            // from employee's sick days and call it number

            double number = sickDaysLeft - d;
            
            // if number is negative, make it the number of sick days that couldn't be taken (polarity will be changed)
            if (number < 0) {
                
                // make sick days 0
                sickDaysLeft = 0;
                
                // print out a warning message
                System.out.println("You weren't able to take " + number*-1 + " sick days.");
                System.out.println("You have exceeded max number of sick days you're allowed to take.");
                
            // if number is positive, make number the number of sicks days left
            } else {
                sickDaysLeft = number;
            }
	}

	public void resetSickDay() {
            
            // make sick days 20 again
            this.sickDaysLeft = yearlySickDay;
		
	}

	public void printPayStub() {
            
            // output all info of full time employee  
            System.out.println(mainToString());
            System.out.println("TITLE: FULL TIME\n\nTOTAL PAID AMOUNT FOR THIS MONTH: " + pay());
            System.out.println("SICK DAYS TAKEN THIS YEAR: " + (yearlySickDay - sickDaysLeft));
	}
} // end class FullTimeStaff
