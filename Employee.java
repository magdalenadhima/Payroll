package payroll;

// Employee abstract superclass
public abstract class Employee {
    
        // create protected fields
	protected String employeeNumber;
	protected String firstName;
	protected String lastName;
        
        // constructor (initializes all fields of the Employee object)
	public Employee(String eN, String fN, String lN){
		this.employeeNumber = eN;
		this.firstName = fN;
		this.lastName = lN;
	}
    
        // return first name
        public String getFirstName()
        {
            return firstName;
        } 
    
        // return last name
        public String getLastName()
        {
            return lastName;
        } 
    
        // return social security number
        public String getEmployeeNumber()
        {
            return employeeNumber;
        } 

        // returns a string with employee class data printed out nicely for user to see
        public String mainToString() {
            
            return "Employee Number: " + getEmployeeNumber()  + "\n" + "First Name: " + getFirstName() + "\n" + "Last Name: " + getLastName();
            }
        
        // returns a string with employee class data formatted so data could be appropriately appended into a file
        public String toString2(){
            
            return getEmployeeNumber()  + "\n" + getFirstName() + "\n" + getLastName();
            }
    
        /** abstract method overridden by subclasses  **/
        
	// pay  an abstract method that returns an employee's pay
	public abstract double pay(); // NO PARAMS; RETURNS DOUBLE.  BY DEFINING pay()ABSTRACTLY HERE IN STAFFMEMBER, THE payday METHOD OF STAFF CAN POLYMORPHICALLY PAY EACH EMPLOYEE.

	// deductSickDay  an abstract method that updates the 
	// sick day information for an employee 
	public abstract void deductSickDay(double d);
        
	// resetSickDay  an abstract method that resets the sick day 
	// information for an employee
	public abstract void resetSickDay();
        
	// printPayStub  an abstract method that prints 
	// (display on screen) the monthly pay stub of an employee
	public abstract void printPayStub();
        
        abstract double AccessSickDay();

}   // end abstract class Employee
