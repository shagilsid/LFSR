
public class Node {
	int i;
	Node top;
	Node bottom;
	
	public Node(int i) {
		this.i=i;
	}
	
	
	@Override
	public String toString() {
		
		return String.format("%d", i);
	}
}
