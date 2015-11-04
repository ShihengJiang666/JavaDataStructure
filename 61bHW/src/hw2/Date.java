package hw2;

/* Date.java */

import java.io.*;

class Date {
	private int month;
	private int day;
	private int year;

  /* Put your private data fields here. */

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
	  if (isValidDate(month, day, year) == false){
		 System.exit(0);
	  }else{
		  this.month = month;
		  this.day = day;
		  this.year = year;
	  }
	  
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
	  try{
		  String [] myDate = s.split("/");
		  int month = Integer.parseInt(myDate[0]);
		  int day = Integer.parseInt(myDate[1]);
		  int year = Integer.parseInt(myDate[2]);
		  this.month = month;
		  this.day = day;
		  this.year = year;
	  }catch(NumberFormatException e){
		  System.out.println("Error: only integers can be put in!");
		  System.exit(0);
	  }
	  /*String [] myDate = s.split("/");
	  int month = Integer.parseInt(myDate[0]);
	  int day = Integer.parseInt(myDate[1]);
	  int year = Integer.parseInt(myDate[2]);
	  if (isValidDate(month, day, year) == false){
		  System.out.print("Your date is invalid!");
		  System.exit(0);
	  }else{
		  this.month = month;
		  this.day = day;
		  this.year = year;
	  }*/
	  
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
	  if (year%4 == 0){
		  if (year%100 == 0){
			  if (year%400 == 0){
				  return true;
			  }else{
				  return false;
			  }
		  }else{
			  return true;
		  }
	  }else{
		  return false;
	  }// replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
	  int days = 0;
	  switch(month){
	  case 1: case 3: case 5: case 7: case 8: case 10: case 12:
		  days = 31;
		  break;
	  case 4: case 6: case 9: case 11:
		  days = 30;
		  break;
	  case 2:
		  if (isLeapYear(year)){
			  days = 29;
		  }else{
			  days = 28;
		  }
		  break;
	  default:
		  break;
	  }
	  return days;
                             // replace this line with your solution
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
	  boolean result = false;
	  if (month > 0 && month < 13 && day > 0 && day <= daysInMonth(month, year) && year > 0 && year < 10000){
		  result = true;
	  }else{
		  result = false;
	  }
	  return result;
	  
                      // replace this line with your solution
	  
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
	  String a = Integer.toString(this.month);
	  String b = Integer.toString(this.day);
	  String c = Integer.toString(this.year);
	  return a + "/" + b + "/" + c;                     // replace this line with your solution
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
	  boolean result = false;
	  if (this.year < d.year){
		  result = true;
	  }else if (this.year > d.year){
		  result = false;
	  }else{
		  if (this.month < d.month){
			  result = true;
		  }else if (this.month > d.month){
			  result = false;
		  }else{
			  if (this.day < d.day){
				  result = true;
			  }else{
				  result = false;
			  }
		  }
	  }
		  
	  return result;
  }
	 
  
	  
                // replace this line with your solution
  

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
	  boolean result = false;
	  if (this.year < d.year){
		  result = false;
	  }else if (this.year > d.year){
		  result = true;
	  }else{
		  if (this.month < d.month){
			  result = false;
		  }else if (this.month > d.month){
			  result = true;
		  }else{
			  if (this.day > d.day){
				  result = true;
			  }else{
				  result = false;
			  }
		  }
	  }
		  
	  return result;
   // replace this line with your solution
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
	  int result = 0;
	  for(int i = 1; i < this.month; i++){
		  result += daysInMonth(i, this.year);
	  }
	  return result + this.day;
                         // replace this line with your solution
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
	  int result = 0;
	  int num1 = 0;
	  int num2 = 0;
	  int num3 = 0;
	  if (this.isAfter(d)){
		  num3 = this.dayInYear();
		  for(int i = this.year - 1; i >= d.year; i--){
			  for (int j =1; j <= 12; j++){
				  num2 += daysInMonth(j,i);
			  }
		  }
		  num1 = d.dayInYear();
		  result = num2 + num3 - num1;
	  }else if (this.isBefore(d)){
		  num3 = d.dayInYear();
		  for (int i = d.year - 1; i >= this.year; i--){
			  for(int j=1; j <= 12; j++){
				  num2 += daysInMonth(j,i);
			  }
		  }
		  num1 = this.dayInYear();
		  result = -(num2 + num3 - num1);
	  }else{
		  result = 0;
	  }
	
	  return result;                           // replace this line with your solution
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");
    
    //my tests following:
    /*System.out.println("2000 is leap year should be true:" +isLeapYear(2000));
    System.out.println("d2 is leap year should be false:" +isLeapYear(d2.year));
    System.out.println("d3 is after d5:"+d3.isAfter(d5));
    System.out.println(d5.dayInYear());
    Date d8 = new Date("hey dude");
    System.out.println(d8);*/
    
    

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
