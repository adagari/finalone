/****************************************************************************************
  *   Title: CustomerAccountReport.java PA4
  *   Authors: Wesley Eaton, Megan Folmar, Adam Garibay
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

public class CustomerAccountReport {
    private static ObjectInputStream customers;
    private static ObjectInputStream securities;

    private static List<Customer> customerList = new ArrayList<Customer>( );
    private static List<Security> securityList = new ArrayList<Security>( );

    public static void main( String[] args) {

        try {
            openFiles();
            processReport();
            closeFiles();
        }
        catch( IOException ioErr ) {
            System.err.println( "Ending process." );
        }
        catch( Exception err ) {
            System.err.printf( "Serious error. Ending process.  %s",
                               err.getClass( ).getName( ) );
            err.printStackTrace( );
        }

    }

    public static void openFiles( )
    throws IOException {

        try {
            customers = new ObjectInputStream(Files.newInputStream(Paths.get( "Customer.ser")));
            securities = new ObjectInputStream(Files.newInputStream(Paths.get( "Security.ser")));
        } catch (IOException ioException) {
            System.err.println("Error opening input file.");
        }
    }

    public static void processReport( )
    throws Exception {
        try {
            prepCustomerCollection( );
            prepSecurityCollection( );
            reportEquityPositions( );
        } catch( Exception err ) {
            System.err.printf( "Serious error. Ending proces.  %s",
                               err.getClass( ).getName( ) );
            err.printStackTrace( );
        }
    }

    public static void closeFiles( )
    throws IOException {

        try {
            if(customers != null && securities != null) {
                customers.close();
                securities.close();
            }
        }
        catch (IOException ioException) {
            System.err.println("Error closing input files.");
        }

    }

    public static void prepCustomerCollection( )
    throws ClassNotFoundException,
        IOException {

        try {
            while ( true ) {
                Customer record = (Customer) customers.readObject();
                customerList.add(record);

            }
        }

        catch (EOFException endOfFileExeption) {
            Collections.sort(customerList, new Customer_CustNumber());
        }

        catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Invalid object type. Terminating.");
        } catch (IOException ioException) {
            System.err.println("Error reading from file. Terminating.");
        }

    }

    public static void prepSecurityCollection( )
    throws ClassNotFoundException,
        IOException {

        try {
            while ( true ) {
                Security record = (Security) securities.readObject();
                securityList.add(record);
            }
        }

        catch (EOFException endOfFileExeption) {
            Collections.sort(securityList, new Security_CustNumber());
        }
        catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Invalid object type. Terminating.");
        } catch (IOException ioException) {
            System.err.println("Error reading from file. Terminating.");
        }
    }

    public static void reportEquityPositions( ) {

        Iterator<Security> securityIterator = securityList.iterator();
        Security equity = (Security)securityIterator.next();
        String holdEquity = equity.getCustNumber();
        findCustomer( holdEquity );


        try {
            while( true ) {
                while( equity.getCustNumber( ).matches( holdEquity ) ) {

                    System.out.printf( "      %-10s %-5s carries a cost basis of %15s%n",
                                       equity.getClass( ).getName( ),
                                       equity.getSymbol( ),
                                       String.format( "$%,.2f", equity.calcCost( ) ) );

                    equity = securityIterator.next( );
                }

                System.out.printf( "=========================================================================%n%n" );
                holdEquity = equity.getCustNumber( );
                findCustomer( holdEquity );
            }
        }

        catch( NoSuchElementException endOfCollection ) {

            System.out.printf( "%n%n\t\t ***** END OF EQUITY REPORT *****%n" );

        }

    }

    public static void findCustomer( String findCust ) {
        Customer target;
        Customer found;

        try {

            target = new Customer( findCust,
                                   "000000000" );


            int result = Collections.binarySearch(customerList, target, new Customer_CustNumber());
            found = customerList.get(result);

            System.out.printf( "%n%-20s %-20s Customer %s, TIN %s%n",
                               found.getFirst( ),
                               found.getLast( ),
                               found.getCustNumber( ),
                               found.getTin( ) );

        } catch(CustomerException err) {
            System.err.printf( "Error searching Customer List%n" );
        }
    } 
}
