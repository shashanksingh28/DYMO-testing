import java.io.InputStreamReader;
import java.io.BufferedReader;

class network
{
	public static mobile_node [] nodes_in_network;
	public static int size;
	
	//Connections array. Here 0 means self, 1 means reachable, -1 means unreachable
	public static int [][] connections;
	BufferedReader br;
	
	public network(int numberOfNodes)
	{
		String name;
		size=numberOfNodes;
		nodes_in_network = new mobile_node[numberOfNodes];
		br= new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			for(int i=0;i<numberOfNodes;i++)
			{
				System.out.println("Enter the name of " + (i+1) + " node");
				name=br.readLine();
				nodes_in_network[i]= new mobile_node(i,name);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		connections= new int[size][size];
		
		
	}
	
	//Initialize the network Connections
	public void initializeConnections()
	{
		char choice;
		try
		{
			for(int i=0;i<size;i++)
			{
				for(int j=0;j<size;j++)
				{
					if(j==i)
					{
						connections[i][j]=0;
					}
					else
					{
						if(connections[i][j]==0)
						{
							System.out.println("Are "+nodes_in_network[i].getName()+
							" and "+ nodes_in_network[j].getName()+ " connected? y or n");
							choice=br.readLine().charAt(0);
							if(choice=='y')
							{
								connections[i][j]=connections[j][i]=1;
							}
							else
							{
								connections[i][j]=connections[j][i]=-1;
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
	
	}
	
	//Print the connection Matrix
	public static void printMatrix()
	{
		System.out.println("The connection matrix is ");
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				System.out.print(connections[i][j] + " ");
			}
			System.out.println();
		}
	}
}
