
public class LinkStrand implements IDnaStrand{

	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
		    next = null;
		}
	}
	private Node myFirst,myLast;
	private long mySize;
	private String myInfo;
	private int myAppends;
	Node node;
	StringBuilder sb;
	
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
		Node node = new Node(source);
		myFirst = node; 
		myLast = node;
		myInfo = node.info;
		mySize = source.length();
		myAppends = 0;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		Node clone = new Node(dna);
		clone.next = null;
		node.next = clone;
		myLast = node.next;
		mySize = mySize + dna.length();
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		StringBuilder copy = new StringBuilder(myInfo);
		copy.reverse();
		LinkStrand ss = new LinkStrand(copy.toString());
		return ss;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		while (node != null) {
			sb.append(node.info);
			node = node.next;
		}
		return sb.toString();
	}
	@Override
	public char charAt(int index) {
		return myInfo.charAt(index);
	}
	

}
