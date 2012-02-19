
// Route Request Packet Structure
public class Route_Message 
{
	long originator_seqNumber;
	int originator_addr;
	int dest_addr;
	int hop_count;
	int packet_type;
	
	public Route_Message(int packet_type,long seq_number, int src_addr, int dest_addr, int hop_count) {
		super();
		this.packet_type=packet_type;
		this.originator_seqNumber = seq_number;
		this.originator_addr = src_addr;
		this.dest_addr = dest_addr;
		this.hop_count = hop_count;
	}

	public long getOriginator_seqNumber() {
		return originator_seqNumber;
	}

	public void setOriginator_seqNumber(long originator_seqNumber) {
		this.originator_seqNumber = originator_seqNumber;
	}

	public int getOriginator_addr() {
		return originator_addr;
	}

	public void setOriginator_addr(int originator_addr) {
		this.originator_addr = originator_addr;
	}

	public int getDest_addr() {
		return dest_addr;
	}

	public void setDest_addr(int dest_addr) {
		this.dest_addr = dest_addr;
	}

	public int getHop_count() {
		return hop_count;
	}

	public void setHop_count(int hop_count) {
		this.hop_count = hop_count;
	}

	@Override
	public String toString() {
		return packet_type+","+originator_seqNumber+","+originator_addr+","+dest_addr+","+hop_count;
	}
	 
	
}
