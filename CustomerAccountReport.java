  /****************************************************************************************
  *   Title: CustomerAccountReport.java PA4
  *   Authors: Wesley Eaton (), Meagan Folmar (zpy558), Adam Garibay (rsg825)
  *   Date: 4/26/2018 2226
  *   Code version: 1.0
  * 
  *   Sources: Revel Fig. 15.10, 15.11, 16.12  
  * 
  * *************************************************************************************/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.io.EOFException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class CustomerAccountReport 
{
 /*declaring instance variables*/
 
    private static ObjectInputStream customers;
    private static ObjectInputStream securities;

    private static List < Customer > customerList = new ArrayList < Customer > ();
    private static List < Security > securityList = new ArrayList < Security > ();

    public static void main(String [] args) 
    {
     /*calling openFiles, processReport, and closeFiles methods*/
        try 
        {
            openFiles();
            processReport();
            closeFiles();
            
        }//end try statement
        
        catch(IOException ioErr) 
        {
            System.err.println("Ending process."); //print out message if ioErr exception is thrown
            
        }//end catch
        
        catch(Exception err) 
        {
            System.err.printf("Serious error. Ending process.  %s", err.getClass().getName()); //print out message if errException thrown
            err.printStackTrace();
            
        }//end catch

    }//end main()
    
    /*open two input files--customers and securities--to be read*/

    public static void openFiles() throws IOException 
    {
      /*try opening customers and securities files and throw exception if it is unable to open them*/
        try 
        {
            customers = new ObjectInputStream(Files.newInputStream(Paths.get("Customer.ser")));
            securities = new ObjectInputStream(Files.newInputStream(Paths.get("Security.ser")));
            
        }//end try statement 
                
        catch (IOException ioException) 
        {
            System.err.println("Error opening input file."); //print out message if ioException is thrown
            ioException.printStackTrace();
            throw ioException;
         
        }//end catch
        
    }//end public static void openFiles
    
    /*try to process reports and throws Exception if unable to*/

    public static void processReport() throws Exception 
    {
     /*call prepCustomerCollection, prepSecurityCollection, and reportEquityPositions methods*/
        try 
        {
            prepCustomerCollection();
            prepSecurityCollection();
            reportEquityPositions();
            
        }//end try statement 
        
        catch(Exception err) 
        {
            System.err.printf("Serious error. Ending proces.  %s", err.getClass().getName()); //print message if err exception thrown
            err.printStackTrace();
            
        }//end catch statement
        
    }//end public static void processReport
    
    /*close two input files customers and securities*/

    public static void closeFiles() throws IOException 
    {
      /*try closing customers and securities files and throws exception if unable to*/
        try 
        {
            if(customers != null && securities != null) 
            {
                customers.close(); //closing customers
                securities.close(); //closing securities
            
            }//end if statement
        
        }//end try statement
        
        catch (IOException ioException) 
        {
            System.err.println("Error closing input files."); //print out message if ioException is thrown
            ioException.printStackTrace( );
            throw ioException;
         
        }//end catch statement

    }//end public static void closeFiles
    
    /*enter loop to deserialize, store, and sort Customer objects. throw exceptions if unable to*/

    public static void prepCustomerCollection() throws ClassNotFoundException, IOException 
    {
        try 
        {
            while (true) 
            {
                Customer record = (Customer) customers.readObject();
                customerList.add(record);

            }//end while statement
        
        }//end try statement

        catch (EOFException endOfFileExeption) 
        {
            Collections.sort(customerList, new Customer_CustNumber());
        
        }//end catch statement

        catch (ClassNotFoundException classNotFoundException) 
        {
            System.err.println("Invalid object type. Terminating.");
        
        }//end catch statement 
        
        catch (IOException ioException) 
        {
            System.err.println("Error reading from file. Terminating.");
        
        }//end catch statement

    }//end public static void prepCustomerCollection
    
    /*enter loop to deserialize, store, and sort Security objects. throw exceptions if unable to*/

    public static void prepSecurityCollection() throws ClassNotFoundException, IOException 
    {
        try 
        {
            while (true) 
            {
                Security record = (Security) securities.readObject();
                securityList.add(record);
                
            }//end while statement
        
        }//end try statement

        catch (EOFException endOfFileExeption) 
        {
            Collections.sort(securityList, new Security_CustNumber());
        
        }//end catch statement
        
        catch (ClassNotFoundException classNotFoundException) 
        {
            System.err.println("Invalid object type. Terminating.");
        
        }//end catch statement
        
        catch (IOException ioException) 
        {
            System.err.println("Error reading from file. Terminating.");
        
        }//end catch statement
    
    }//end public static void prepSecurityCollection
    
    /*move through Security Collection and have holding spaces for Security and String objects*/

    public static void reportEquityPositions() 
    {
        Iterator < Security > securityIterator = securityList.iterator();
        Security equity = (Security)securityIterator.next();
        String holdEquity = equity.getCustNumber();
        findCustomer(holdEquity);

        try 
        {
            while(true) 
            {
                while(equity.getCustNumber().matches(holdEquity)) 
                {
                    System.out.printf("      %-10s %-5s carries a cost basis of %15s%n", equity.getClass().getName(),
                                       equity.getSymbol(), String.format("$%,.2f", equity.calcCost()));

                    equity = securityIterator.next();
                
                }//end while statement

                System.out.printf("=========================================================================%n%n");
                holdEquity = equity.getCustNumber();
                findCustomer(holdEquity);
                
            }//end while statement
        
        }//end try statement

        catch(NoSuchElementException endOfCollection) 
        {
            System.out.printf("%n%n\t\t ***** END OF EQUITY REPORT *****%n"); 

        }//end catch statement

    }//end public static void reportEquityPositions
    
    /*look through Customer collection for specified object, retrieve it, and name it*/

    public static void findCustomer(String findCust) 
    {
        Customer target;
        Customer found;

        try 
        {
            target = new Customer(findCust, "000000000");

            int result = Collections.binarySearch(customerList, target, new Customer_CustNumber());
            found = customerList.get(result);

            System.out.printf("%n%-20s %-20s Customer %s, TIN %s%n", found.getFirst(), found.getLast(),
                               found.getCustNumber(), found.getTin());

        }//end try statement 
        
        catch(CustomerException err) 
        {
            System.err.printf("Error searching Customer List%n");
        
        }//end catch statement
        
    }//end public static void findCustomer 

}//end public class CustomerAccountReport
