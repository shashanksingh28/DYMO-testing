import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

class mobile_node
{
	int key;
	String name;
	ArrayList<Route> table;
	
	public mobile_node(int key, String name)
	{
		this.key= key;
		this.name=name;
		table=new ArrayList<Route>();
	}
	
	public String getName()
	{
		return name;
	}
	
	//Function to return an existing route to a destination
	public Route getRoute(int dest)
	{
		Iterator<Route> itr= table.iterator();
		Route temp;
		while(itr.hasNext())
		{
			temp=itr.next();
			if(temp.getDest_addr()==dest)
			{
				return temp;
			}
		}
		
		return null;
	}
	
	//Function to process RREQ packet on a node
	public void processRREQ(Route_Message rreq, int receivedFrom)
	{
		printRoutePacket(rreq);
		
		//First check if route present
		Route r= getRoute(rreq.getOriginator_addr());
		
		if(r==null)
		{
			//Route not present, make a new one
			r = new Route(rreq.getOriginator_seqNumber(),rreq.getOriginator_addr(),
					receivedFrom,rreq.getHop_count());
			table.add(r);
			printTable();
		}
		else
		{
			System.out.println("Route already present");
			if(r.getSeq_Number()<rreq.getOriginator_seqNumber() || r.getHop_count()>rreq.getHop_count())
			{
				r.setNext_hop(receivedFrom);
				r.setHop_count(rreq.getHop_count());
			}
			System.out.println("Table of "+this.name+ " modified");
			printTable();
		}
		
		if(rreq.getDest_addr()==this.key)
		{
			//this is destination, send RREP
			Route_Message rrep= new Route_Message(1, 1, this.key, rreq.getOriginator_addr(), 1);
			network.nodes_in_network[receivedFrom].unicastRREP(rrep, this.key);
			return;
		}
		else
		{
			//this is not destination, broadcast
			rreq.setHop_count(rreq.getHop_count()+1);
			broadcastRREQ(rreq,receivedFrom);	
		}
	}
	
	/*Function to broadcast RREQ, reqFrom is its own address for other node to understand
	* After broadcasting, checks if route present, return 0 if yes and -1 if no
	*/
	public int broadcastRREQ(Route_Message rreq,int reqFrom)
	{
		int self=this.key;
		for(int i=0;i<network.size;i++)
		{
			if(network.connections[self][i]==1 && i!=reqFrom)
			{
				network.nodes_in_network[i].processRREQ(rreq,this.key);
				rreq.setHop_count(1);
			}
		}
		if(getRoute(rreq.getDest_addr())!=null)
		{
			return 0;
		}
		else
			return -1;	
		
	}
	
	//unicast RREP with self address passed as forwardFrom
	public void unicastRREP(Route_Message rrep,int forwardFrom)
	{
		printRoutePacket(rrep);
		Route r=getRoute(rrep.getOriginator_addr());
		if(r==null)
		{
			//Route does not exists, mostly should be the case
			r= new Route(rrep.getOriginator_seqNumber(),rrep.getOriginator_addr(),forwardFrom,rrep.getHop_count());
			table.add(r);
			printTable();
		}
		else
		{
			//Route exists, check
			if(r.getSeq_Number()<rrep.getOriginator_seqNumber() || r.getHop_count()>rrep.getHop_count())
			{
				r.setSeq_Number(rrep.getOriginator_seqNumber());
				r.setNext_hop(forwardFrom);
				r.setHop_count(rrep.getHop_count());
			}
			printTable();
			
		}
		
		
		if(rrep.getDest_addr()==this.key)
		{
			return;
		}
		else
		{
			//Unicast forward
			r=getRoute(rrep.getDest_addr());
			rrep.setHop_count(rrep.getHop_count()+1);
			network.nodes_in_network[r.getNext_hop()].unicastRREP(rrep, this.key);
		}
	}
	
	//Function to create RREQ for a destination
	public int createRREQ(int dest)
	{
		Route_Message rreq= new Route_Message(0, 1, this.key, dest, 1);
		return broadcastRREQ(rreq, this.key);
		
	}
	
	public void printRoutePacket(Route_Message rreq)
	{
		if(rreq.packet_type==0)
			System.out.println("RREQ received at "+this.name);
		else
			System.out.println("RREP received at "+this.name);
		System.out.print("Seq : "+rreq.getOriginator_seqNumber()+" Originator : "+
			network.nodes_in_network[rreq.getOriginator_addr()].getName());
		System.out.print(" Destination : "+network.nodes_in_network[rreq.getDest_addr()].getName()+ 
				" Hop_count : "+rreq.getHop_count());
		System.out.print(" \nNode "+this.name+ " will now process, hit enter\n");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printTable()
	{
		System.out.println("Showing table of : "+this.name);
		Iterator<Route> itr=table.iterator();
		Route temp;
		while(itr.hasNext())
		{
			temp=itr.next();
			System.out.print("Seq : "+temp.getSeq_Number());
			System.out.print(" Destination : "+network.nodes_in_network[temp.getDest_addr()].getName());
			System.out.print(" Next Hop : "+network.nodes_in_network[temp.getNext_hop()].getName());
			System.out.print(" Hop Count : "+temp.getHop_count());
			System.out.println();
		}
		System.out.println();
	}
	
	
}
