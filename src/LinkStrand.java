
public class LinkStrand implements IDnaStrand{

	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	StringBuilder myInfo;
	
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
		return myInfo.length();
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source); 
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;
		
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
		return null;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	@Override
	public String toString() {
		myInfo = new StringBuilder();
		Node node = myFirst;
		while (node != null) {
			myInfo.append(node.info);
			node = node.next;
		}
		return myInfo.toString();
	}
	@Override
	public char charAt(int index) {
		return myInfo.charAt(index);
	}
	

}
