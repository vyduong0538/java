/* Vy Duong
 * 10/27/2019
 * Inputs: GBused
 * Outputs: bill
*/
package javacellphonebill;
import java.util.Scanner;

public class JavaCellPhoneBill {
	// define the GB limits and billing rates for each cell phone plan
	private static final double OVER_LIMIT_CHARGE = 15.0;  // price per GB over limit
  	 
	// define row index names into the PLAN array
	private static final int A = 0;   // Plan-A
	private static final int B = 1;   // Plan-B
	private static final int C = 2;   // Plan-C
	private static final int D = 3;   // Plan-D
	// define column index names into the PLAN array
	private static final int LIMIT = 0;
	private static final int PRICE = 1;   
	private static final double[][] PLAN = {
    	{ 0.0,   50.00},	// Plan-A
    	{ 2.0,   60.00},	// Plan-B
    	{ 4.0,   70.00},	// Plan-C
    	{10.0,   90.00},	// Plan-D
	};
    
	// give names for each field in the customer list
	private static final int ACCT = 0;
	private static final int NAME = 1;
	private static final int SELECTION = 2;
	private static final int USED = 3;
	private static final int BILL = 4;
	private static String[][] Accounts = {
    	{"323998-9", "Dan McElroy",	"Plan-A",  "0.0",  "0.00"},
    	{"264442-8", "Manuel Estrada", "Plan-C",  "0.0",  "0.00"},
    	{"355591-3", "Charles Aitken", "Plan-B",  "0.0",  "0.00"},
    	{"100355-4", "Linh Nguyen",	"Plan-D",  "0.0",  "0.00"},
    	{"256052-9", "Alice Browne",   "Plan-D",  "0.0",  "0.00"},
    	{"726619-8", "Dave Ha",    	"Plan-A",  "0.0",  "0.00"},
    	{"145767-3", "Rachel Bush",	"Plan-B",  "0.0",  "0.00"},
    	{"767372-3", "Jose Gonzales",  "Plan-A",  "0.0",  "0.00"},
    	{"531923-3", "Oscar Meyer",	"Plan-D",  "0.0",  "0.00"},
    	{"445632-7", "Vy Duong",   	"Plan-B",  "0.0",  "0.00"},
    	{"856423-9", "Elder King", 	"Plan-C",  "0.0",  "0.00"},
    	{"656245-2", "Asaka Rintaro",  "Plan-A",  "0.0",  "0.00"},
   	 
	};
    
    
	public static void main(String[] args) {
    	inputGBused ();                     	// input GB used by each customer, place in array
    	billEachCustomer();                 	// compute bill for each customer based on plan
    	printBilling();                     	// display the bill for each customer
	}
    
	private static void inputGBused() {
    	// create the Scanner object
    	Scanner stdin = new Scanner(System.in);
   	 
    	System.out.println ("Enter GB for:");
    	int numberOfCustomers = Accounts.length;
    	for (int customer=0; customer<numberOfCustomers; customer++) {
        	System.out.printf ("%15.15s %s %s: ",
                	Accounts[customer][NAME],
                	Accounts[customer][ACCT],
                	Accounts[customer][SELECTION]);
        	Accounts[customer][USED] = stdin.nextLine( ); // input as a string
   	 
    	} // end of for
    	stdin.close();
	} // end of inputGBused( )
    

	private static void billEachCustomer() {
    	double GBused;
    	double bill;
   	 
    	int numberOfCustomers = Accounts.length;
    	for (int customer=0; customer<numberOfCustomers; customer++) {
        	try {
            	// get amount used by customer as String, convert to double
            	GBused = Double.valueOf(Accounts[customer][USED]);

            	// compute bill based on the customer's plan selection
            	if (Accounts[customer][SELECTION].equals("Plan-A")) {
                	bill = computeBill (GBused, PLAN[A][LIMIT], PLAN[A][PRICE]);              	 
            	}
            	else if (Accounts[customer][SELECTION].equals("Plan-B")) {
                	bill = computeBill (GBused, PLAN[B][LIMIT], PLAN[B][PRICE]);              	 
            	}
            	else if (Accounts[customer][SELECTION].equals("Plan-C")) {
                	bill = computeBill (GBused, PLAN[C][LIMIT], PLAN[C][PRICE]);              	 
            	}
            	else if (Accounts[customer][SELECTION].equals("Plan-D")) {
                	bill = computeBill (GBused, PLAN[D][LIMIT], PLAN[D][PRICE]);              	 
            	}

           	 
   	 
           	 
            	// a non-existing plan has been selected
            	else  {
                	bill = 0.00;
            	}
           	 
            	// convert the bill to a String and save into the array
            	Accounts[customer][BILL] = String.valueOf(bill);
        	} // end of try
        	catch (NumberFormatException e) {
           	System.out.println ("Values for GB used must be numeric");         	 
        	} // end of
    	}   //end of for loop
	} // end of billEachCustomer( )
    
    
	private static double computeBill(double used, double limit, double rate) {
    	double overLimit;
    	double bill;
   	 
    	if (used <= limit)  // see if customer used more GB than is on the plan
        	overLimit = 0.0;
    	else
        	overLimit = Math.ceil(used - limit);
    	// the bill is the plan's base rate + any charge for GB over the plan limit
    	bill = rate + overLimit * OVER_LIMIT_CHARGE;
    	return bill;
	}
    
    
	private static void printBilling() {	 
   	 int numberOfCustomers = Accounts.length;
   	 System.out.println();
   	 System.out.println("======== Customer Billing ========");
   	 for (int customer=0; customer<numberOfCustomers; customer++) {
   	 try {
   		 System.out.printf("%-15.15s %8.8s %6.6s\n ",
   				 Accounts[customer][NAME],Accounts[customer][ACCT],Accounts[customer][SELECTION]);
   		 // get amount used by customer as String, convert to double
   		 double GBused = Double.valueOf(Accounts[customer][USED]);
   		 // get bill by customer as String, convert to double
   		 double Bill = Double.valueOf(Accounts[customer][BILL]);
   		 System.out.printf("%7.1f GB used, bill = %.2f \n ",
   				 GBused,Bill);
   		 System.out.println();
   	 }
   	 catch (NumberFormatException e) {
   		 System.out.printf ("**** illegal input for ****\n");
   		 System.out.printf("%8.8s %s \n", Accounts[customer][ACCT],Accounts[customer][NAME]);
   		 System.out.println();
   	 }
    	// Display each customer bill using two lines with one digit
    	//	past the decimal for the gigabytes used and two digits
    	//	past the decimal for the total billed for all customers.

	} // end of printBilling( )
   	 
	}
    
	} // end of class