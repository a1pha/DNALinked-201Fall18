
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
		myInfo = new String(source);
		myAppends = 0;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}
	
	@Override
	public String toString() {
		for (int i = 0; i < this.size(); i++) {
			
			
		}
	}
	
	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
