package dataStructures;

/**
 *	Class OrderedLinkedList.
 *
 *	This class functions as a linked list, but ensures items are stored in ascending order.
 *
 */
public class OrderedLinkedList
{
	
	/**************************************************************************
	 * Constants
	 *************************************************************************/
	
	/** return value for unsuccessful searches */
	private static final OrderedListNode NOT_FOUND = null;
	

	/**************************************************************************
	 * Attributes
	 *************************************************************************/

	/** current number of items in list */
	private int theSize;
	
	/** reference to list header node */
	private OrderedListNode head;
	
	/** reference to list tail node */
	private OrderedListNode tail;
	
	/** current number of modifications to list */
	private int modCount;
	
	
	/**************************************************************************
	 * Constructors
	 *************************************************************************/

	
	/**
	 *	Create an instance of OrderedLinkedList.
	 *
	 */
	public OrderedLinkedList()
	{
		// empty this OrderedLinkedList
		clear();
	}
	
	
	/**************************************************************************
	 * Methods
	 *************************************************************************/


	/*
	 *	Add the specified item to this OrderedLinkedList.
	 *
	 *	@param	obj		the item to be added
	 */
	public boolean add(Comparable obj)
	{
		OrderedListNode addedNode = null;
		//If there are no items in the list, the object will be placed between the head and the tail. 
		//head.next & tail.previous will contain a reference to the node until more items are added
		if(theSize == 0){
			addedNode = new OrderedListNode(obj, tail, head);
			head.next = addedNode;
			tail.previous = addedNode;

			modCount++;
			theSize++;
			
			return true;
		}
		for(OrderedListNode node = head.next; node != tail; node = node.next){
			//Object is smaller than all items in the list. Create a new OrderedListNode object
			//head.next and node.previous will point to the object
			if(obj.compareTo(node.theItem) < 0 && node.previous == head){
				 addedNode = new OrderedListNode(obj, node, head);
				
				node.previous = addedNode;
				head.next = addedNode;
				
				modCount++;
				theSize++;
				
				return true;
				
			//Object is larger than all items in the list. Create a new OrderedListNode object
			//node.next and tail.previous will point to the object
			}
			if(obj.compareTo(node.theItem) > 0 && node.next == tail){
				addedNode = new OrderedListNode(obj, tail, node);
				
				tail.previous = addedNode;
				node.next = addedNode;
				
				modCount++;
				theSize++;
				
				return true;
				
			//Object is larger than the current node, but smaller than the next node it is pointing to.
				
			}
			if(obj.compareTo(node.theItem) > 0 && obj.compareTo(node.next.theItem) < 0){
				OrderedListNode rightNode = node.next;
				addedNode = new OrderedListNode(obj, rightNode, node);
				
				rightNode.previous = addedNode;
				node.next = addedNode;
				
				modCount++;
				theSize++;
				
				return true;
			}
		}
		return false;
	}

	
	/*
	 *	Remove the first occurrence of the specified item from this OrderedLinkedList.
	 *
	 *	@param	obj		the item to be removed
	 */
	public boolean remove(Comparable obj)
	{		
		if(theSize == 0){
			throw new NoSuchElementException();
		}
		for(OrderedListNode node = head.next; node != tail; node = node.next){
			OrderedListNode previousNode = node.previous;
			OrderedListNode nextNode = node.next;
			
			if(node.theItem.compareTo(obj) == 0){
				previousNode.next = nextNode;
				nextNode.previous = previousNode;
				node = null;
				
				theSize--;
				modCount++;
				return true;
			}
		}
					return false;
	}

	
	/**
	 *	Empty this OrderedLinkedList.
	 */
	public void clear()
	{
		// reset header node
		head = new OrderedListNode("HEAD", null, null);
		
		// reset tail node
        tail = new OrderedListNode("TAIL", head, null);
        
        // header references tail in an empty LinkedList
        head.next = tail;
        
        // reset size to 0
		theSize = 0;
		
		// emptying list counts as a modification
		modCount++;
	}


	/**
	 *	Return true if this OrderedLinkedList contains 0 items.
	 */
	public boolean isEmpty()
	{
		return theSize == 0;
	}


	/**
	 *	Return the number of items in this OrderedLinkedList.
	 */
	public int size()
	{
		return theSize;
	}
	

	/*	
	 *	Return a String representation of this OrderedLinkedList.
	 *
	 *	(non-Javadoc)
     *	@see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
    	String s = "";
    	
    	OrderedListNode currentNode = head.next;
    	
    	while (currentNode != tail)
    	{
    		s += currentNode.theItem.toString();
    		
    		if (currentNode.next != tail)
    		{
    			s += ", ";
    		}
    		
    		currentNode = currentNode.next;
    	}
    	
    	return s;
    }

	
	/**************************************************************************
	 * Inner Classes
	 *************************************************************************/
	

	/**
	 *	Nested class OrderedListNode.
	 *
	 *	Encapsulates the fundamental building block of an OrderedLinkedList
	 *	contains a data item, and references to both the next and previous nodes
	 *	in the list
	 */
	
	
	// TODO: Implement the nested class OrderedListNode (5 points).  This nested class
	// should be similar to the nested class ListNode of the class LinkedList, but
	// should store a data item of type Comparable rather than Object.
    public static class OrderedListNode{
    	
    	Comparable theItem;
    	OrderedListNode next;
    	OrderedListNode previous;
    	
    	
    	public OrderedListNode(Comparable item, OrderedListNode nextNode, OrderedListNode previousNode){
    		theItem = item;
    		next = nextNode;
    		previous = previousNode;
    	}
    }
}