package hw4;

public class LockDListNode extends DListNode {
	boolean locked = false;

	LockDListNode(Object i, DListNode p, DListNode n) {
		super(i, p, n);
		locked = false;

	}
	

}
