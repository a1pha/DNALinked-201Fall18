
public class LinkStrand implements IDnaStrand{

	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;

	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
		    next = null;
		}
	}
	
	public LinkStrand() {
		this("");
	}
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source); 
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		Node temp = new Node(dna);
		myLast.next =  temp;
		myLast = myLast.next;
		mySize = mySize + dna.length();
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		Node copy = this.myFirst;
		StringBuilder reverseBuilder = new StringBuilder();
		reverseBuilder.append(copy.info);
		reverseBuilder.reverse();
		LinkStrand lsReverse = new LinkStrand(reverseBuilder.toString()); 
		copy = copy.next;
		
		while (copy != null) {
			reverseBuilder = new StringBuilder();
			reverseBuilder.append(copy.info);
			reverseBuilder.reverse();
			lsReverse.myFirst.next = new Node(reverseBuilder.toString());
			lsReverse.myFirst = lsReverse.myFirst.next;
			copy = copy.next;
		}
		
		Node previous = null;
		Node current = lsReverse.myFirst;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		lsReverse.myFirst = previous; 
		return lsReverse;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node node = myFirst;
		while (node != null) {
			sb.append(node.info);
			node = node.next;
		}
		return sb.toString();
	}
	@Override
	public char charAt(int index) throws IndexOutOfBoundsException{
		if (index > mySize) throw new IndexOutOfBoundsException();
		while (myIndex != index) {
			myIndex++;
			myLocalIndex++;
			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myFirst.next;
			}
		}
	    return myCurrent.info.charAt(myLocalIndex);
	}


	

}
