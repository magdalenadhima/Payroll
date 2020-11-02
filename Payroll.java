package payroll;


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


// Payroll class, which store the list of employee
public class Payroll {

    /** create the variables needed for the methods of payroll **/
    
    // create an array list for storing employee objects
    ArrayList<Employee> staffList = new ArrayList();
    
    // create an array for storing file lines
    ArrayList<String> ar = new ArrayList<>();
    
    // other variables
    BufferedReader fileIn;
    PrintWriter pw = null;
    Boolean beginning = true;
    int num_of_employees, staffIndex;
    FullTimeStaff fts;
    PartTimeStaff pts;
    String strLine, employeeNumber, firstName, lastName, employeeType;
    double yearlySalary, sickDaysLeft, hourlyRate, numHoursAssigned, mostAbsent;


    // lists all employee (employee number, name)
    public void listAllEmployees() {

        // for each employee object in staff list
        for (int i = 0; i < staffList.size(); i++)
        {
            // output the 'main to string' method from employee class
            // with the data of the employee
            System.out.println(staffList.get(i).mainToString());

            // if employee an instance of Full Time Staff
            if (staffList.get(i) instanceof FullTimeStaff) {
                
                // output employee is full time
                System.out.println("TITLE: FULL TIME\n");
                
            // if an instance of part time Staff
            } else if (staffList.get(i) instanceof PartTimeStaff) {
                
                // output employee is part time
                System.out.println("TITLE: PART TIME\n");
            }
        }
    }
    
    // updates sick day information for the employee specified by the employee number
    public void enterSickDay(String employeeNum, double sickDays){

        // loop through employee list
        for (int x=0; x<=staffList.size()-1; x++) {
            
            // check if the employee number is equal to the given employee number
            if (staffList.get(x).getEmployeeNumber().equals(employeeNum)) {
                
                // call the 'deduct sick day' from employees instances
                // to deduct sick days from that employee
                
                if (staffList.get(x) instanceof FullTimeStaff) {
                    staffList.get(x).deductSickDay(sickDays);
                    
                } else if (staffList.get(x) instanceof PartTimeStaff) {
                    staffList.get(x).deductSickDay(sickDays);
                }
            }
        }
    }
    
    // prints on screen the pay stub (for the currently month) for all employees
    public void printAllPayStubs() {
        
        // loop through employees 
        for (int i = 0; i < staffList.size(); i++)
        {
            /** 
             * get employee type
             * call 'pay stub printing' methods of the employee object
             * to output the pay stubs
            **/
            if (staffList.get(i) instanceof FullTimeStaff) {
                
                staffList.get(i).printPayStub();
                
            } else if (staffList.get(i) instanceof PartTimeStaff) {
                staffList.get(i).printPayStub();
            }
        
        // seperate each pay stubs from each other nicely
        System.out.println("\n-----------------------");
        }
    }
    
    // saves employee information to the specified file
    public void saveStaffList(String newfilename) {
        
        try {
            
            // starts file stream with the file name given by the user
            pw = new PrintWriter(new FileOutputStream(newfilename, true));

        } catch (FileNotFoundException e) {
        }

        // creates empty string variable for storing output
        String temp = "";
        
        // add the number of employees to the beginning of the file
        temp += num_of_employees + "\n";

        // loops through objects in employee array list
        for (int x = 0; x <= staffList.size() - 1; x++) {
            
            // for each object, get its 'toString2' methods and add the outputs to the 'temp' variable
            temp = temp + staffList.get(x).toString2() + "\n";
        }

        pw.println(temp);   // appends content to the file
        pw.close();     // closes file stream
    }
    
    // loads employee information from the specified file
    public void loadStaffList(String filename) {

        // variable to hold count for the lines of each employee in file
        int lineCount = 0;

        try {
            
            // create a BufferedReader object using the FileReader constructor 
            // with the filename given by user 
            fileIn = new BufferedReader(new FileReader(filename));

            // loop reading every line of the file until the line is empty
            while ((strLine = fileIn.readLine()) != null) {

                // store the number of employees given at the beginning of the file in a variable for payroll access
                if (beginning == true) {
                    
                    num_of_employees = Integer.parseInt(strLine);
                    beginning = false;
 
                } else {

                    // if line number is lower than 6
                    if (lineCount < 6) {
                        
                        // add the line read into an array and add 1 to line count
                        ar.add(strLine); 
                        lineCount ++;

                    }
                    
                    // if line count has reached 6 
                    if (lineCount == 6) {
                        
                        /** 
                         * get each string at every index of the array 
                         * and store value into its appropriate variable 
                         **/

                        employeeNumber = ar.get(0);
                        firstName = ar.get(1);
                        lastName = ar.get(2);
                        employeeType = ar.get(3);

                        // if employee type from line 4 is full time
                        if (employeeType.equals("FT")) {

                            yearlySalary = Double.parseDouble(ar.get(4));
                            sickDaysLeft = Double.parseDouble(ar.get(5));

                            // create a full time object with the created variables 
                            fts = new FullTimeStaff(employeeNumber, firstName, lastName, yearlySalary, sickDaysLeft) {};
                            
                            // add full time object to staff list
                            staffList.add(fts);


                        // if employee type from line 4 is part time
                        } else {

                            hourlyRate = Double.parseDouble(ar.get(4));
                            numHoursAssigned = Double.parseDouble(ar.get(5));

                            // create a part time object with the variables 
                            pts = new PartTimeStaff(employeeNumber, firstName, lastName, numHoursAssigned, hourlyRate) {};
                            
                            // add part time object to staff list
                            staffList.add(pts);
                        }
                        
                        // return line count to zero
                        lineCount = 0;
                        
                        // reset array 
                        ar.clear();

                    }
                }
            }

        } catch (IOException | NumberFormatException e) {//Catch exception if any error
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // calculates (and returns) the average salary of all full-time staffs
    public double averageSalary() {
        
        int s = staffList.size();
        int count = 0; // amount of FullTimeStaff
        double total = 0;  // total salary of all FullTimeStaff

	// go through employees and add up all Full Time salaries
        for (int i = 0; i < s; i++) {
			
            // staffList.get(i) return an Object
            // cast it then to an Employee
            Employee curr = (Employee) staffList.get(i);
            
            if (curr instanceof FullTimeStaff) {
                
                // add all pays into 'total' variable 
                total += curr.pay();
                count++;
            }
        }

        if (count != 0) {
            
            // return average salary
            return total / count;
        } else {
            // return 0
            return count;
        }
    }
    
    // calculates (and returns) the average hourly rate of all part-time staffs
    public double averageHourlyRate() {
        
        double total = 0;  // total salary of all PartTimeStaff
        double count = 0; // amount of FullTimeStaff
        
	// go through staffList and add up all PartTime salaries
        // then find average as shown in 'averageSalary' method
        for (int i = 0; i < staffList.size(); i++)
        {
            
            if (staffList.get(i) instanceof PartTimeStaff) {
                
                total += staffList.get(i).pay();
                count ++;
            }
        }
        
        if (count != 0) {
            return total / count;
        } else {
            return count;
        }
    }

    // determines (and returns) the full-time staff with the most absence in the current year
    public String mostAbsentFullTime() {
        
        // make most absent value -1 one because it is possible that most absent employee has been absent for 0 days
        mostAbsent = -1;
        
        // loop through full time employees
        for (int x=0; x<=staffList.size()-1; x++) {
            if (staffList.get(x) instanceof FullTimeStaff) {
                   
                // if employees sick days is bigger than 'most absent'
                if (staffList.get(x).AccessSickDay() > mostAbsent) {

                    // make most absent that new number
                    mostAbsent = staffList.get(x).AccessSickDay();
                    
                    // make staff index the employee's object index
                    staffIndex = x;
                    
                }
            }
        }

        // return the data for that employee
        return staffList.get(staffIndex).mainToString();
    }
    
    // determines (and returns) the part-time staff with the most absence in the current month
    public String mostAbsentPartTime() {
        
        // make most absent value -1
        mostAbsent = -1;
        
        // loop through part time employees
        for (int x=0; x<=staffList.size()-1; x++) {
            if (staffList.get(x) instanceof PartTimeStaff) {
                   
                // if employees sick days is bigger than 'most absent'
                if (staffList.get(x).AccessSickDay() > mostAbsent) {

                    // make most absent that new number
                    mostAbsent = staffList.get(x).AccessSickDay();
                    
                    // make staff index the employee's object index
                    staffIndex = x;
                    
                }
            }
        }

        // return the data for that employee
        return staffList.get(staffIndex).mainToString();
    }
    
    // resets the yearly sick day information for all full-time staffs
    public void yearlySickDayReset() {
        
        // loop through employees
        for (int x=0; x<=staffList.size()-1; x++) {
            
            // if full time
            if (staffList.get(x) instanceof FullTimeStaff) {
                
                // reset sick days
                staffList.get(x).resetSickDay();
            }
        }
    }

    // resets the monthly sick day information for all part-time staffs
    public void monthylySickDayReset() {
        
        // loop through employees
        for (int x=0; x<=staffList.size()-1; x++) {
            
            // if part time
            if (staffList.get(x) instanceof PartTimeStaff) {
                
                // reset sick days
                staffList.get(x).resetSickDay();
            }
        }
    }

}
