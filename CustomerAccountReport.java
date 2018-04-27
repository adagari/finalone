/*
 *  Read in TWO serialized files of objects: 
 *     Customer
 *     Security
 *  Store objects in collections - each file/type in separate collection
 *  Sort each collection into ASCENDING ORDER by custNumber
 *  
 *  Process match/merge report:
 *      All Security objects
 *          By Customer
 * 
 * (c) adapted from Terri Davis (2017)
 */
/*
/********************************************************************************************************************
 *  IMPORT STATEMENTS FOR SERIALIZED INPUT: I've provided two; you need to add four more   
 *  See Figure 15.10 in your text as an example and import EOFException as well
 ********************************************************************************************************************/

import java.io.ObjectInputStream;
import java.io.IOException;
              

/********************************************************************************************************************
 *  IMPORTS FOR COLLECTION MANAGEMENT: I've provided two, you'll need three more.
 *  See Figure 16.2 in your text as an example and import Collections as well.
 ********************************************************************************************************************/ 
   import java.util.List;                                // A list, for individual objects w/in collection
   import java.util.ArrayList;                           // An Arraylist; implements List & Collection interfaces
                                                         // An interface provides bulk operations methods for Collections
                                                         // A iterator providing functionality similar to a subscript
                                                         // A 'helper' Collections class containing (mostly) static methods .

public class CustomerAccountReport
{
  
  /********************************************************************************************************************
 *  DECLARATIONS: 
 *  Declare two ObjectInputStream objects
 *  Create two ArrayLists, one of type Customer and one of type Security -- see line 14 of 16.2 as an example.
 ********************************************************************************************************************/
   private static ObjectInputStream customers;        // serialized input stream
                                                      // declared at this level to make it "globally accessible"
   private static ObjectInputStream securities;       // serialized input stream
                                                      // declared at this level to make it "globally accessible"
   
   private static List<Customer> customerList = new ArrayList<Customer>( );// ArrayList of Customers 
   private static List<Security> securityList = new ArrayList<Security>( );// ArrayList of Securities
   
   /**************************************************************************************************************
    *  main method 
    *************************************************************************************************************/
   public static void main( String[] args)
   {
     /*
      *  The try block executes the following processes:
      *     Open input files
      *     create and output report entries
      *     close input files
      */   
     
           
     try
     {
       openFiles();
       processReport();
       closeFiles();
     }  // end try
     catch( IOException ioErr )                       // If there is any error in opening or reading files
     {
       System.err.println( "Ending process." );
     } // end catch
     catch( Exception err )                           // Any/all other exceptions passed up to main
     {
       System.err.printf( "Serious error. Ending process.  %s",
                           err.getClass( ).getName( ) );
       err.printStackTrace( );
     } 
     
   }// end main

   /***************************************************************************************************************
    *  Open the input files
    **************************************************************************************************************/   
   /*
    *  Locate the physical file; define it as an input stream; define the input stream as an object stream.
    *  Prepare to deseralize objects from the file.
    */
   public static void openFiles( )
                      throws IOException
   {
    // This code is commented out so that the program runs as is -- take out the comments after you add the code to test
    /* try 
      {
        // open the input files, which will have .ser extensions
                
      } // end try
      catch ()
      {
        // include appropriate exception handling here
        
      } // end catch
    */  

   } // end method openFile

   
   /***************************************************************************************************************
    *  Manage the actual reporting process
    **************************************************************************************************************/   
   /*
    * Deserialize Customer file; store in collection. Sort collection when file completely deserialized.
    * Deserialize Security file; store in collection. Sort collection when file completely deserialized.
    * 
    * Process match/merge based on Customer.
    *  For each Customer:
    *    Output Customer information.
    *       Output Security information
    *       Output CostBasis for Security 
    *  
    */
   public static void processReport( )
                      throws Exception
   {
     try
     {
       prepCustomerCollection( );
       prepSecurityCollection( );
       reportEquityPositions( );
     }
     catch( Exception err )
     {
       System.err.printf( "Serious error. Ending proces.  %s",
                         err.getClass( ).getName( ) );
       err.printStackTrace( );
     }
   } // end method processRecords
 
   /***************************************************************************************************************
    *  Close the input files
    **************************************************************************************************************/
   public static void closeFiles( )
                      throws IOException
   {
      // This code is commented out so that the program runs as is -- take out the comments after you add the code to test
      /* try 
      {
                
      } // end try
      catch ( IOException ioException )
      {                                               
         // Include exception handling in case file will not close
      } // end catch IOException
      */ 
   } // end method closeFiles

   
   /***************************************************************************************************************
    *  Prepare the Customer collection
    **************************************************************************************************************/   
   /*
    *  Deserialize all objects from serialized Customer file
    *     As each object is deserialized (read), add it to the collection holding Customer objects
    *  Once all Customer objects have been stored in the collection,
    *     Sort the collection in ascending order, based on custNumber value of each object.
    */
   public static void prepCustomerCollection( )
                      throws ClassNotFoundException,
                             IOException
   {
      // This "try" code is commented out so that the program runs as is -- take out the comments after you add the code to test
      // Note that the catch parameter is missing; fill in the appropriate Exception  
      // See Figure 15.11 in your text for one way to read in the input 
      
      /* try
      {
         while ( true )                               // essentially an infinite loop; will be ended by EOFException
         {
                                                      // deserialize object
                                                      // add the record

         } // end while
      } // end try

      catch ( )
      {
                                                      // sort collection
      } // end catch 
        
      /* catch (  )
      {                                               // object in input file are not of correct type
         // print a message and throw the error 
      }  // end catch 
      */
        
      /* 
      catch (  )
      {                                               // some sort of error on attempting to read/deserialize
         // print a message and throw the error;
      }  // end catch 
      */
    } // end prepCustomerCollection

   /***************************************************************************************************************
    *  Prepare the Security collection
    **************************************************************************************************************/   
   /*
    *  Deserialize all objects from serialized Security file
    *     As each object is deserialized (read), add it to the collection holding Security objects
    *  Once all Security objects have been stored in the collection,
    *     Sort the collection in ascending order, based on custNumber value of each object.
    */
   public static void prepSecurityCollection( )
                      throws ClassNotFoundException,
                             IOException
   {
     // This method is very similar to prepCustomerCollection.
     // Once you figure that one out, this will be a piece of cake.
      
   } // end prepSecurityCollection

   /***************************************************************************************************************
    *  Report Equity Positions
    **************************************************************************************************************/   
   /*
    *    Once both input files have been read, both collections built, and both sorted,
    *    Use a matching algorithm to report equity positions for all Customers 
    */
   public static void reportEquityPositions( )
   { 
     
     /* THREE DECLARATIONS
      *
      * Declare an object to use to move through the collection of Security objects
      * Using that object, pick up the 'next' Security object available, assign it the name ‘equity’
      * Retrieve the custNumber value from that object and store it in a variable named 'holdEquity'
      */ 
     
     // Uncomment this when you are ready to test. 
     // findCustomer( holdEquity );                      // Pass the custNumber value retrieved from the Security
     
     // The entire try statement below has been provided to you.
     // Uncomment the section when you are ready to test. 
     
     /** try
     {
       while( true )
       {
         while( equity.getCustNumber( ).matches( holdEquity ) )
         {
           // Output a line describing this Security
           System.out.printf( "\t%-10s %-5s carries a cost basis of %15s%n",
                             equity.getClass( ).getName( ),
                             equity.getSymbol( ),
                             String.format( "$%,.2f", equity.calcCost( ) ) );
           // Move to the next Security
           equity = securityIterator.next( );
         }  // end INNER while
         // This is just an indication that we're done with this Customer's Security objects
         System.out.printf( "=========================================================================%n%n" );
         holdEquity = equity.getCustNumber( );
         findCustomer( holdEquity );
       }  // end OUTER while
     } 
     */
     /* catch( NoSuchElementException endOfCollection )
     {
       // This Exception is EXPECTED to be thrown when we 'run out' of Security or Customer objects */
       
       // System.out.printf( "%n%n\t\t\t ***** END OF EQUITY REPORT *****%n" );
       
     // } end catch NoSuchElementException  
     
       
   } // end reportEquityPositions
   
   /***************************************************************************************************************
    *  Find Customer
    **************************************************************************************************************/   
   /*
    *  Using the target custNumber passed into the method from the calling code...
    *     Search the sotred and sorted Customer objects for the one matching the provided target
    *     Assign the located Customer object to the reference found
    *     Using the Customer reference found, produce the report header
    */
   public static void findCustomer( String findCust )
   {
     Customer target;                               // 'template' for search process
     Customer found;                                // 'holding space' for the located Customer object
     
     try
     {
       // Set up for Customer search
       target = new Customer( findCust,
                              "000000000" );
       
       // implement a binary search here with the Collections method 
       

       // Print the first report break header (commented out until ready to test)
       
       /* System.out.printf( "%n%-20s %-20s Customer %s, TIN %s%n",   
                         found.getFirst( ),
                         found.getLast( ),
                         found.getCustNumber( ),
                         found.getTin( ) );
        */
     }
     catch( CustomerException err )
     {
       System.err.printf( "Error searching Customer List%n" );
     }  // end catch
   }  // end findCustomer
 
 } // end class CustomerAccountReport
