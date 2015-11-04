/* HashTableChained.java */

package dict;

import list.DList;
import list.DListNode;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	protected int size; // the size of hash table 
	DList[] data; // list of entries
	

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
	  this.size = getPrime(sizeEstimate);
	  data = new DList[size];
	  for (int i = 0; i < size; i ++){
		  data[i] = new DList();
	  }  
    // Your solution here.
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
	  size = 101; //default value
	  data = new DList[size];
	  for (int i = 0; i < size; i++){
		  data[i] = new DList();
	  }
			  
    // Your solution here.
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
	  int pos;
	  pos = ((10*code+30)% 14657)%size;
	  if (pos < 0){
		  pos = pos + size;
	  }
	  return pos;
    
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
	  int num=0;
	  for (int i = 0; i < size; i++){
		  if(data[i]!=null){
			  num = num + data[i].size;  
		  }
	  }
	  return num;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	  boolean result = true;
	  if(data.length !=0){
		  for (int i = 0; i < data.length; i++){
			  if (data[i].size!=0){
				  result = false;
				  break;
			  }
			  
			  
		  }
	  }else{
		  result = false;
	  }
	  return result;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  if (key != null){

		  int hash = compFunction(key.hashCode());
		  Entry newEntry= new Entry();
		  newEntry.key = key;
		  newEntry.value = value;
		  data[hash].insertBack(newEntry);
		  return newEntry;
	  }else{
		  return null;
	  }
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
	  if (key!= null){
		  int hash = compFunction(key.hashCode()); 	
		  DListNode cur = data[hash].head.next;
		  while (cur.item != null){
			  if(key == cur.item.key){
				  return cur.item; 
			  }
			  cur = cur.next;
		  }
		  return null;
	  }else{
		  return null;
	  }
    // Replace the following line with your solution.

  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	  if(key != null){
		  int hash = compFunction(key.hashCode());
		  Entry result = null;
		  if (data[hash] != null){
			  DListNode cur = data[hash].head.next;
			  while(cur.item!=null){
				  if (cur.item.key == key){
					  result = cur.item;
					  data[hash].remove(cur);
					  break;
				  }
				  cur = cur.next;
			  }
		  }
		  return result;
	  }else{
		  return null;
	  }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	  if(this.isEmpty() == false){
		  for (int i = 0; i < size; i++){
			  data[i] = new DList();
		  }
	  }
  }
  
  public static boolean isPrime(int n) {
      int divisor = 2;
      while (divisor < n) {         
        if (n % divisor == 0) {     
          return false;             
        }                           
        divisor++;                  
      }
      return true;
    }
  
  public static int getPrime(int n){
	  int result=0;
	  if (isPrime(n)){
		  result = n;
	  }else{
		  for (int i = n; i <= 2*n; i++){
			  if(isPrime(i)){
				  result = i;
				  break;
			  }
		  }
	  }
	  return result;
  }
  
  public int collisions(){
	  int sum=0;
	  for (int i = 0; i < size; i++){
		  if (data[i].size > 1){
			  sum += (data[i].size-1);
		  }
	  }
	  return sum;
  }
  
  public static double power(double x, double y){
	  double result = 1;
	  if(y == 0){
		  result = 1;
	  }
	  for (int i = 0; i < y; i++){
		  result *= x;
	  }
	  return result;
  }
  
  public static void main(String[] args){
	  HashTableChained t = new HashTableChained(13);
	  t.insert("apple", 89);
	  System.out.println(t.find("apple").value);
	   HashTableChained t1 = new HashTableChained(97);

	    System.out.println(t1.find("weed"));
	    System.out.println(t1.size());
	    t1.makeEmpty();
	    System.out.println(t1.isEmpty());

	    System.out.println(getPrime(24));
	    System.out.println(t1.size);
	    System.out.println(t.isEmpty());
	    System.out.println(t.remove("banana"));
	    System.out.println(t.isEmpty());
	    System.out.println(t.insert("aoe", 99));

  }

}
