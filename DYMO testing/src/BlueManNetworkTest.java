import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BlueManNetworkTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n;
		try
		{
			System.out.println("Enter the number of nodes..");
			n=Integer.parseInt(br.readLine());
			//setting up the network and connections
			network test_network= new network(n);
			test_network.initializeConnections();
			System.out.println("The id's of the nodes are");
			for(int i=0;i<n;i++)
			{
				System.out.println(network.nodes_in_network[i].getName()+" : "+i);
			}
			System.out.println("Enter initiator node key");
			int src=Integer.parseInt(br.readLine());
			System.out.println("Enter destination key");
			int dest=Integer.parseInt(br.readLine());
		
			if(network.nodes_in_network[src].createRREQ(dest)==0)
				System.out.println("Connection Succeeded");
			else
				System.out.println("Connection failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
