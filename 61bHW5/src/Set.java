/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
	protected List set;
	protected ListNode cur;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
	  set = new DList();
	  cur = null;
    // Your solution here.
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return this.set.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.

   **/
  public void insert(Comparable c){
	  if (this.cardinality() == 0){
		  set.insertFront(c);
		  cur = set.front();
	  }else{
		  cur = this.set.front();
		  while(cur.isValidNode()){
			  try{
				  if (c.compareTo(cur.item())==0){
					  break;
				  }else if (c.compareTo(cur.item()) < 0){
					  cur.insertBefore(c);
					  break;
				  }else if(!cur.next().isValidNode()){
					  cur.insertAfter(c);
					  break;
				  }else{
					  cur = cur.next();
				  }
			  }catch(InvalidNodeException e){
				  System.out.println(e);
			  }
		  }
	  }

    // Your solution here.
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
	  s.cur = s.set.front();
	  while(s.cur.isValidNode()){
		  try {
			  this.insert((Comparable) s.cur.item());
			  s.cur = s.cur.next();
		  } catch (InvalidNodeException e) {
			  System.out.println(e);
		  }
	  }
    // Your solution here.
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
	 
	  cur = this.set.front();
	  int count = 0;
	  try {
		while(cur.next().isValidNode()){
			  try {
				  count =0;
				  s.cur= s.set.front();
				  while(s.cur.isValidNode()){
					  try{
						  if (((Comparable)(s.cur.item())).compareTo((Comparable)(cur.item()))==0){
							  count++;
						  }
						  s.cur=s.cur.next();
					  }catch(InvalidNodeException e){
						  System.out.println(e);
					  }
				  }
				  cur = cur.next();
			//	  System.out.println(cur.item());
				//  System.out.println(count);
				//  System.out.println(cur.prev().item());
				  if(count == 0){
					  cur.prev().remove();
				  }
				  
			  } catch (InvalidNodeException e) {
				  System.out.println(e);
			  }
		  }
		count = 0;
		s.cur= s.set.front();
		while(s.cur.isValidNode()){
			try{
				  if (((Comparable)(s.cur.item())).compareTo((Comparable)(cur.item()))==0){
					  count++;
				  }
				  s.cur=s.cur.next();
			  }catch(InvalidNodeException e){
				  System.out.println(e);
			  }
		}
		if(count == 0){
			cur.remove();
		}
	} catch (InvalidNodeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // Your solution here.
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
	  String result = "{  ";
	  cur = set.front();
	  while (cur.isValidNode()) {
		  try {
			  result = result + cur.item() + "  ";
			  cur = cur.next();
		  } catch (InvalidNodeException e) {
			System.out.println(e);
		  }
	  }
	  return result + "}";
  }


  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
    Set s4 = new Set();
    s4.insert(new Integer(5));
    s4.insert(new Integer(3));
    s4.insert(new Integer(8));
    s4.insert(new Integer(1));
    s4.insert(new Integer(1));
    s4.insert(new Integer(2));
    s4.insert(new Integer(7));
    s4.insert(new Integer(4));
    System.out.println("s2 ="+s2);
    System.out.println("s4 ="+s4);
    s4.intersect(s2);
    System.out.println("s4 intersects s2 ="+s4);
    
  }
}
