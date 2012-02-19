public class Route {

	long seq_Number;
	int dest_addr;
	int next_hop;
	int hop_count;
	
	public Route(long seq_Number, int dest_addr, int next_hop,
			int hop_count) {
		super();
		this.seq_Number = seq_Number;
		this.dest_addr = dest_addr;
		this.next_hop = next_hop;
		this.hop_count = hop_count;
	}
	
	public long getSeq_Number() {
		return seq_Number;
	}
	public void setSeq_Number(long seq_Number) {
		this.seq_Number = seq_Number;
	}
	public int getDest_addr() {
		return dest_addr;
	}
	public void setDest_addr(int dest_addr) {
		this.dest_addr = dest_addr;
	}
	public int getNext_hop() {
		return next_hop;
	}
	public void setNext_hop(int next_hop) {
		this.next_hop = next_hop;
	}
	public int getHop_count() {
		return hop_count;
	}
	public void setHop_count(int hop_count) {
		this.hop_count = hop_count;
	}
	
	
}
