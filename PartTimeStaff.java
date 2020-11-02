package payroll;


// PartTimeStaff (subclass) extends Employee.
public abstract class PartTimeStaff extends Employee {

        // create private fields
	private double numHoursAssigned;
	private double hourlyRate;
	private double sickDaysTaken;
	
        // constructor of Part Time Staff object
	public PartTimeStaff(String eN, String fN, String lN, double nH, double hR) {
		super(eN, fN, lN);
		this.numHoursAssigned = nH;
		this.hourlyRate = hR;
		this.setSickDaysTaken(0);
	}
    
        // return wage
        public double getHourlyRate()
        {
            return hourlyRate;
        } 
    
       // return hours worked
       public double getHours()
       {
          return numHoursAssigned;
       } 
       
        // return sick days taken
        public double AccessSickDay()
        {
            return sickDaysTaken;
        } 

        // construct sick days making it of value 0
	public void setSickDaysTaken(double sickDaysTaken) {
		this.sickDaysTaken = sickDaysTaken;
	}
        
        @Override
        // returns a string with PartTimeStaff class data formatted so data could be appropriately appended to a file
        public String toString2(){
            
            return super.toString2() + "\n" + "PT" + "\n" +  + getHourlyRate() + "\n" + getHours();
            }
       
	public double pay() {

                // convert the 'sick days taken' into hours (8 hours per day) and deduct it from employee's assigned hours to calculate their pay
                // then round number to 2 decimal places
		return Math.round(getHourlyRate() * (getHours() - (8 * sickDaysTaken))*100.0)/100.0;
	}

	public void deductSickDay(double d) {
            
            // add number of sicks days given from user from employee's sick days
            sickDaysTaken += d;

	}

	public void resetSickDay() {
            
            // make sick days taken 0
            sickDaysTaken = 0;
		
	}

	public void printPayStub() {
            
            // output all info of part time employee  
            System.out.println(super.mainToString());
            System.out.println("TITLE: PART TIME\n\nNUMBER OF HOURS WORKED: " + getHours());
            System.out.println("TOTAL PAID AMOUNT FOR THIS MONTH: " + pay());     
            System.out.println("SICK DAYS TAKEN THIS YEAR: " + AccessSickDay());
	}
} // end class PartTimeStaff
