/*
 *  Custom Comparator class 
 *    Associated with Customer class
 *    Sort into ASCENDING order based on custNumber value
 */

import java.util.Comparator;                                   // import interface

public class Customer_CustNumber implements Comparator<Customer> 
{
  public int compare( Customer cust1, 
                      Customer cust2 )
  {
    return cust1.getCustNumber( ).compareTo( cust2.getCustNumber( ) );
  } // end compare
}  // end Customer_CustNumber
