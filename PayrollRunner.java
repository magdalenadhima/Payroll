package payroll;

import java.util.Scanner;

/*
 * title: Payroll
 * description: To create a payroll application for AYJ Co., which stores information of all
   employees. The program must include the functionalities of a Full-time and Part Time staff.
 * author: Magdalena Dhima
 * date: 2019-02-13
 */
public class PayrollRunner {
        
    // variable to loop printing menu
    static int x = 0;

    public static void main(String[] args) {
            
        // instantiate payroll class
        Payroll pr = new Payroll();

        // loop printing menu
        while (x == 0){
                
            // output menu
            
            System.out.println("LOAD                loads information from file\n" +
"SAVE                saves list of employees to file\n" +
"UPDATE              updates number of sick days for specified employee\n" +
"PRINT               prints pay stubs for all employees\n" +
"LIST                lists information for all employees\n" +
"AVGSAL              prints the average salary of full time employees\n" +
"AVGRATE             prints the average salary of full time employees\n" +
"MOSTABS             prints out information of the most absent full time employee\n" +
"MOSTSICK            prints out information of the most absent part time employee\n" +
"RESETYEAR           resets the yearly sick days for all full time employees\n" +
"RESETMONTH          resets the monthly sick days for all part time employees\n" +
"MENU                prints program menu again\n" +                       
"EXIT                quits program");
                
            int y = 0;  // variable for 2nd loop
                
            // loop getting input
            while (y == 0){
                    
                // get input
                Scanner scan = new Scanner(System.in);
                
                // return input into string 
                String input = scan.nextLine();
                
                System.out.println(">");
                    
                // check user's input
                switch (input.toUpperCase())   {

                    // breaks loop to print menu
                    case "MENU":
                        
                        y = 1;
                        break;
                        
                    // loads a file with employee data
                    case "LOAD":
                        
                        // get file name from user
                        
                        Scanner fileName = new Scanner(System.in);
                        
                        System.out.println("ENTER NAME OF FILE (INCLUDE ANY EXTENSIONS): ");
                        
                        String filename = scan.nextLine();
                        
                        // give file name as a string to the load method
                        pr.loadStaffList(filename);
                        break;
                        
                    // save a new java file with new employee data
                    case "SAVE":
                        
                        // get file name from user
                        
                        Scanner newFileName = new Scanner(System.in);
                        
                        System.out.println("ENTER NAME OF FILE (INCLUDE ANY EXTENSIONS): ");
                        
                        String newfilename = scan.nextLine();
                        
                        // give file name as a string to the save method
                        pr.saveStaffList(newfilename);
                        break;
                        
                    // updates number of sick days for specified employee
                    case "UPDATE":
                        
                        // get employee number from user
                        
                        System.out.println("EMPLOYEE NUMBER:");
                        
                        String employeeNum = scan.nextLine();
                        
                        // get the additional number of sick days from user
                        
                        System.out.println("ENTER SICK DAY(S) FOR A SPECIFIC EMPLOYEE, THE SMALLEST UNIT OF SICK DAY IS 0.5: ");
                        
                        // get number of sicks days as a double since the smallest unit of sick day is 0.5
                        pr.enterSickDay(employeeNum, Double.parseDouble(scan.nextLine()));
                        
                        break;
                        
                    // print pay stubs
                    case "PRINT":
                        
                        pr.printAllPayStubs();
                        break;
                        
                    // list all employee class data
                    case "LIST":
                        
                        pr.listAllEmployees();
                        break;
                        
                    // find average salary for full time
                    case "AVGSAL":
                        
                        // output average salary
                        System.out.println("$" + (pr.averageSalary())); 
                        break;
                        
                    // find average hourly salary for part time
                    case "AVGRATE":
                        // output average salary
                        System.out.println("$" + (pr.averageHourlyRate())); 
                        break;
                        
                    // find most abstent full time staff
                    case "MOSTABS":
                        
                        // output the employee's object
                        System.out.println(pr.mostAbsentFullTime());
                        break;
                        
                    // find most sick part time staff
                    case "MOSTSICK":
                        
                        // output the employee's object
                        System.out.println(pr.mostAbsentPartTime());
                        break;
                        
                    // reset yearly sick days for full time staff
                    case "RESETYEAR":
                        
                        pr.yearlySickDayReset();
                        break;
                        
                    // reset monthly sick days for part time staff
                    case "RESETMONTH":
                        pr.monthylySickDayReset();
                        break;
                        
                    // exit program
                    case "EXIT":
                        
                        System.exit(0);
                        
                    // if none
                    // print friendly message
                    default:
                        System.out.println("INVALID COMMAND!");
                        break;
                }
            }
        }
    }
}