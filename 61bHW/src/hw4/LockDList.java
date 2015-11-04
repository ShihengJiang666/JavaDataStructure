package hw4;

public class LockDList extends DList{
	public void lockNode(DListNode node){
		LockDListNode newNode = new LockDListNode(node.item,node.prev,node.next);;
		newNode.locked = true;
		node.prev.next = newNode;
		node.next.prev = newNode;
	}
	public void remove(DListNode node) {
		//LockDListNode newNode = new LockDListNode(node.item,node.prev,node.next);
		if (node == null||(node instanceof LockDListNode)){
			return;
		}
		else{
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size --;
		}
	}

}
