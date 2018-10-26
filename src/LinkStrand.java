
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
			lsReverse.myLast.next = new Node(reverseBuilder.toString());
			lsReverse.mySize += reverseBuilder.length();
			lsReverse.myLast = lsReverse.myLast.next;
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
		if (index >= mySize || index < 0) throw new IndexOutOfBoundsException();
		if (index < myIndex || myIndex == 0) {
			myIndex = index;
			int count = 0;
			int dex = 0;
			Node list = myFirst;
			while (count != index) {
				count++;
				dex++;
				if (dex >= list.info.length()) {
					dex = 0;
					list = list.next;
				}
			}
			myLocalIndex = dex;
			myCurrent = list;
	        return list.info.charAt(dex);

		}
		else {
			while (myIndex != index) {
				myIndex++;
				myLocalIndex++;
				if (myLocalIndex >= myCurrent.info.length()) {
					myLocalIndex = 0;
					myCurrent = myCurrent.next;
				}
			}
		    return myCurrent.info.charAt(myLocalIndex);
		}
	}
}
