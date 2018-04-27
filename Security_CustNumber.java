/*
 *  Custom Comparator class 
 *    Associated with Security class
 *    Sort into ASCENDING order based on custNumber value
 */

import java.util.Comparator;                                   // import interface

public class Security_CustNumber implements Comparator<Security> 
{
  public int compare( Security secure1, 
                      Security secure2 )
  {
    return secure1.getCustNumber( ).compareTo( secure2.getCustNumber( ) );
  } // end compare
}  // end Security_CustNumber
