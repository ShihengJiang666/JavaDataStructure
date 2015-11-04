/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
    // Fill in your solution here.
	  if (this.find(key) == false){
		  if (this.size == 0){
			  root = new Tree234Node(null, key);
			  this.size++;
		  }else if(this.size < 3 && root.keys<3){
			  root.keys++;
			  this.size++;
			  if (root.keys == 2){
				  if(root.key1 > key){
					  root.key2 = root.key1;
					  root.key1 = key;
				  }else{
					  root.key2 = key;
				  }
			  }else if (root.keys == 3){
				  if (root.key1 > key){
					  root.key3 = root.key2;
					  root.key2 = root.key1;
					  root.key1 = key;
				  }else{
					  if (root.key2 > key){
						  root.key3 = root.key2;
						  root.key2 = key;
					  }else{
						  root.key3 = key;
					  }
				  }
			  }
		  }else{
			  this.size++;
			  Tree234Node cur = root;
			  while (cur != null){	//don't forget to add the kick-up process
				  if (cur.keys == 1){
					  if (cur.key1 > key){
						  if (cur.child1!= null){	//not leaf
							  cur.child1.parent = cur;
							  cur = cur.child1;
						  }else{		//leaf case
							  cur.keys = 2;
							  cur.key2 = cur.key1;
							  cur.key1 = key;
							  break;
						  }
					  }else{
						  if (cur.child2 != null){
							  cur.child2.parent = cur;
							  cur = cur.child2;
						  }else{
							  cur.keys = 2;
							  cur.key2 = key;
							  break;
						  }
					  }
				  }else if (cur.keys == 2){
					  if (cur.key1 > key){
						  if (cur.child1 != null){
							  cur = cur.child1;
						  }else{
							  cur.keys = 3;
							  cur.key3 = cur.key2;
							  cur.key2 = cur.key1;
							  cur.key1 = key;
							  break;
						  }
					  }else{
						  if(cur.key2 > key){
							  if (cur.child2 != null){
								  cur = cur.child2;
							  }else{
								  cur.keys = 3;
								  cur.key3 = cur.key2;
								  cur.key2 = key;
								  break;
							  }
						  }else{

							  if(cur.child3 != null){
								  cur.child3.parent = cur;
								  cur = cur.child3;
							  }else{
								  cur.keys = 3;
								  cur.key3 = key;
								  break;
							  }
						  }
					  }
				  }else if (cur.keys == 3){	// kick-up;size of parent's keys; size of cur keys;
					  if (cur.parent == null){
						  Tree234Node temp1 = root.child1;
						  Tree234Node temp2 = root.child2;
						  Tree234Node temp3 = root.child3;
						  Tree234Node temp4 = root.child4;
						  root = new Tree234Node(null, cur.key2);
						  root.child1 = new Tree234Node(root, cur.key1);
						  root.child2 = new Tree234Node(root, cur.key3);
						  
						  root.child1.child1 = temp1;
						  root.child1.child2 = temp2;
						  root.child2.child1 = temp3;
						  root.child2.child2 = temp4;

						  if (root.key1 > key){
							  if (root.child1.key1 > key){
								  if (root.child1.child1 != null){
									  root.child1.child1.parent = root.child1;
									  cur = root.child1.child1;
								  }else{
									  root.child1.keys++;
									  root.child1.key2 = root.child1.key1;
									  root.child1.key1 = key;
									  break;
								  }
							  }else{
								  if (root.child1.child2 != null){
									  root.child1.child2.parent = root.child1;
									  cur = root.child1.child2;
								  }else{
									  root.child1.keys++;
									  root.child1.key2 = key;
									  break;
								  }
							  }
						  }else{
							  if (root.child2.key1 > key){
								  if(root.child2.child1 != null){
									  root.child2.child1.parent = root.child2;
									  cur = root.child2.child1;
								  }else{
									  root.child2.keys++;
									  root.child2.key2 = root.child2.key1;
									  root.child2.key1 = key;
									  break;
								  }
							  }else{
								  if (root.child2.child2 != null){
									  root.child2.child1.parent = root.child2;
									  cur = root.child2.child2;
								  }else{
									  root.child2.keys++;
									  root.child2.key2 = key;
									  break;
								  }
							  }
						  }
					  }else{
						  if (cur.parent.keys == 1){// parent's # of keys = 2; cur's # of keys = 2
							  cur.parent.keys++;
							  if (cur.key2 > cur.parent.key1){
								  cur.parent.key2 = cur.key2;
								  Tree234Node temp1 = cur.parent.child2.child1;
								  Tree234Node temp2 = cur.parent.child2.child2;
								  Tree234Node temp3 = cur.parent.child2.child3;
								  Tree234Node temp4 = cur.parent.child2.child4;
								  
								  cur.parent.child2 = new Tree234Node(cur.parent,cur.key1);
								  cur.parent.child3 = new Tree234Node(cur.parent,cur.key3);
								  
								  cur.parent.child2.child1 = temp1;
								  cur.parent.child2.child2 = temp2;
								  cur.parent.child3.child1 = temp3;
								  cur.parent.child3.child2 = temp4;
								  
								  if (cur.parent.key2 > key){
									  if (cur.key1 > key){
										  if (cur.parent.child2.child1!=null){
											  cur = cur.parent.child2.child1;
										  }else{
											  cur.parent.child2.keys = 2;
											  cur.parent.child2.key2 = cur.key1;
											  cur.parent.child2.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child2.child2!=null){
											  cur = cur.parent.child2.child2;
										  }else{
											  cur.parent.child2.keys = 2;
											  cur.parent.child2.key2 = key;
											  break;
										  }
									  }
								  }else{
									  cur.parent.child3.keys = 2;
									  if(cur.key3 > key){
										  if (cur.parent.child3.child1 != null){
											  cur = cur.parent.child3.child1;
										  }else{
											  cur.parent.child3.key2 = cur.key3;
											  cur.parent.child3.key1 = key;
											  break;
										  }
									  }else{
										  if(cur.parent.child3.child2 != null){
											  cur = cur.parent.child3.child2;
										  }else{
											  cur.parent.child3.key2= key;
											  break;
										  }
									  }
								  }
							  }else{
								  cur.parent.key2 = cur.parent.key1;
								  cur.parent.key1 = cur.key2;
								  Tree234Node temp1 = cur.parent.child1.child1;
								  Tree234Node temp2 = cur.parent.child1.child2;
								  Tree234Node temp3 = cur.parent.child1.child3;
								  Tree234Node temp4 = cur.parent.child1.child4;
								  cur.parent.child3 = cur.parent.child2;
								  
								  cur.parent.child1 = new Tree234Node(cur.parent,cur.key1);
								  cur.parent.child2 = new Tree234Node(cur.parent,cur.key3);
								  
								  cur.parent.child1.child1 = temp1;
								  cur.parent.child1.child2 = temp2;
								  cur.parent.child2.child1 = temp3;
								  cur.parent.child2.child2 = temp4;
								  
								  if (cur.parent.key1 > key){
									  if (cur.key1 > key){
										  if (cur.parent.child1.child1!=null){
											  cur = cur.parent.child1.child1;
										  }else{
											  cur.parent.child1.keys = 2;
											  cur.parent.child1.key2 = cur.key1;
											  cur.parent.child1.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child1.child2!=null){
											  cur = cur.parent.child1.child2;
										  }else{
											  cur.parent.child1.keys = 2;
											  cur.parent.child1.key2 = key;
											  break;
										  }
									  }
								  }else{
									  cur.parent.child2.keys = 2;
									  if(cur.key3 > key){
										  if (cur.parent.child2.child1 != null){
											  cur = cur.parent.child2.child1;
										  }else{
											  cur.parent.child2.key2 = cur.key3;
											  cur.parent.child2.key1 = key;
											  break;
										  }
									  }else{
										  if(cur.parent.child2.child2 != null){
											  cur = cur.parent.child2.child2;
										  }else{
											  cur.parent.child2.key2= key;
											  break;
										  }
									  }
								  }  
							  }
						  }else if (cur.parent.keys == 2){	// parent's # of keys = 3; cur's # of keys = 2
							  cur.parent.keys++;
							  if (cur == cur.parent.child1){
								  cur.parent.key3 = cur.parent.key2;
								  cur.parent.key2 = cur.parent.key1;
								  cur.parent.key1 = cur.key2;
								  cur.parent.child4 = cur.parent.child3;
								  cur.parent.child3 = cur.parent.child2;

								  Tree234Node temp1 = cur.parent.child1.child1;
								  Tree234Node temp2 = cur.parent.child1.child2;
								  Tree234Node temp3 = cur.parent.child1.child3;
								  Tree234Node temp4 = cur.parent.child1.child4;
								  
								  cur.parent.child1 = new Tree234Node(cur.parent,cur.key1);
								  cur.parent.child2 = new Tree234Node(cur.parent,cur.key3);

								  cur.parent.child2.child1 = temp3;
								  cur.parent.child2.child2 = temp4;
								  cur.parent.child1.child1 = temp1;
								  cur.parent.child1.child2 = temp2;
								  
								  if (cur.parent.key1 > key){
									  if (cur.key1 > key){
										  if (cur.parent.child1.child1 != null){
											  cur.parent.child1.child1.parent = cur.parent.child1;
											  cur = cur.parent.child1.child1;
										  }else{
											  cur.parent.child1.keys = 2;
											  cur.parent.child1.key2 = cur.key1;
											  cur.parent.child1.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child1.child2 != null){
											  cur.parent.child1.child2.parent = cur.parent.child1;
											  cur = cur.parent.child1.child2;
										  }else{
											  cur.parent.child1.keys = 2;
											  cur.parent.child1.key2 = key;
											  break;
										  }
									  }
								  }else{
									  if (cur.key3 > key){
										  if (cur.parent.child2.child1 != null){
											  cur.parent.child2.child1.parent = cur.parent.child2;
											  cur = cur.parent.child2.child1;
										  }else{
											  cur.parent.child2.keys = 2;
											  cur.parent.child2.key2 = cur.key3;
											  cur.parent.child2.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child2.child2 != null){
											  cur.parent.child2.child2.parent = cur.parent.child2;
											  cur = cur.parent.child2.child2;
										  }else{
											  cur.parent.child2.keys = 2;
											  cur.parent.child2.key2 = key;
											  break;
										  }
									  }
								  }
							  }else if (cur == cur.parent.child2){
								  cur.parent.key3 = cur.parent.key2;
								  cur.parent.key2 = cur.key2;
								  cur.parent.child4 = cur.parent.child3;
								  
								  Tree234Node temp1 = cur.parent.child2.child1;
								  Tree234Node temp2 = cur.parent.child2.child2;
								  Tree234Node temp3 = cur.parent.child2.child3;
								  Tree234Node temp4 = cur.parent.child2.child4;
								  
								  cur.parent.child2 = new Tree234Node(cur.parent,cur.key1);
								  cur.parent.child3 = new Tree234Node(cur.parent,cur.key3);

								  cur.parent.child3.child1 = temp3;
								  cur.parent.child3.child2 = temp4;
								  cur.parent.child2.child1 = temp1;
								  cur.parent.child2.child2 = temp2;
								  if (cur.parent.key2 > key){
									  if (cur.key1 > key){
										  if (cur.parent.child2.child1 != null){
											  cur.parent.child2.child1.parent = cur.parent.child2;
											  cur = cur.parent.child2.child1;
										  }else{
											  cur.parent.child2.keys = 2;
											  cur.parent.child2.key2 = cur.key1;
											  cur.parent.child2.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child2.child2 != null){
											  cur.parent.child2.child2.parent = cur.parent.child2;
											  cur = cur.parent.child2.child2;
										  }else{
											  cur.parent.child2.keys = 2;
											  cur.parent.child2.key2 = key;
											  break;
										  }
									  }
								  }else{
									  if (cur.key3 > key){
										  if (cur.parent.child3.child1 != null){
											  cur.parent.child3.child1.parent = cur.parent.child3;
											  cur = cur.parent.child3.child1;
										  }else{
											  cur.parent.child3.keys = 2;
											  cur.parent.child3.key2 = cur.key3;
											  cur.parent.child3.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child3.child2 != null){
											  cur.parent.child3.child2.parent = cur.parent.child3;
											  cur = cur.parent.child3.child2;
										  }else{
											  cur.parent.child3.keys = 2;
											  cur.parent.child3.key2 = key;
											  break;
										  }
									  }
								  }
							  }else if (cur == cur.parent.child3){
								  cur.parent.key3 = cur.key2;
								  Tree234Node temp1 = cur.parent.child3.child1;
								  Tree234Node temp2 = cur.parent.child3.child2;
								  Tree234Node temp3 = cur.parent.child3.child3;
								  Tree234Node temp4 = cur.parent.child3.child4;
								  
								  cur.parent.child3 = new Tree234Node(cur.parent,cur.key1);
								  cur.parent.child4 = new Tree234Node(cur.parent,cur.key3);
								  
								  cur.parent.child3.child1 = temp1;
								  cur.parent.child3.child2 = temp2;
								  cur.parent.child4.child1 = temp3;
								  cur.parent.child4.child2 = temp4;
								  if(cur.parent.key3 > key){
									  if (cur.key1 > key){
										  if (cur.parent.child3.child1 != null){
											  cur.parent.child3.child1.parent = cur.parent.child3;
											  cur.parent.child3.child1.parent = cur.parent.child3;
											  cur = cur.parent.child3.child1;
										  }else{
											  cur.parent.child3.keys = 2;
											  cur.parent.child3.key2 = cur.key1;
											  cur.parent.child3.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child3.child2 != null){
											  cur.parent.child3.child2.parent = cur.parent.child3;
											  cur.parent.child3.child2.parent = cur.parent.child3;
											  cur = cur.parent.child3.child2;
										  }else{
											  cur.parent.child3.keys = 2;
											  cur.parent.child3.key2 = key;
											  break;
										  }
									  }
								  }else{
									  if (cur.key3 > key){
										  if (cur.parent.child4.child1 != null){
											  cur.parent.child4.child1.parent = cur.parent.child4;
											  cur.parent.child4.child1.parent = cur.parent.child4;
											  cur = cur.parent.child4.child1;
										  }else{
											  cur.parent.child4.keys = 2;
											  cur.parent.child4.key2 = cur.key1;
											  cur.parent.child4.key1 = key;
											  break;
										  }
									  }else{
										  if (cur.parent.child4.child2 != null){
											  cur.parent.child4.child2.parent = cur.parent.child4;
											  cur.parent.child4.child1.parent = cur.parent.child4;
											  cur = cur.parent.child4.child2;
										  }else{
											  cur.parent.child4.keys = 2;
											  cur.parent.child4.key2 = key;
											  break;
										  }
									  }
								  }
							  }
						  }
					  }
				  }
			  }
		  }
	  }
  }


  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");


    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }

}
