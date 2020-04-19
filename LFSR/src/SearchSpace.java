
public class SearchSpace {
	private int numberOfNodes;
	private Node startNode;
	private Node endNode;
	private Node searchSpace[][];
	private double pheromoneValues[][];
	public SearchSpace(Node startNode, Node endNode, int numberOfNodes,int initialPheromoneValue ) {
		this.startNode=startNode;
		this.endNode=endNode;
		this.numberOfNodes=numberOfNodes;
		this.searchSpace=new Node[numberOfNodes][2];
		for(int i=0;i<numberOfNodes;i++) {
			
			searchSpace[i][0]=new Node(1);
			searchSpace[i][1]=new Node(0);
		}
		
		this.pheromoneValues=new double[numberOfNodes][2];
		for (int i = 0; i < numberOfNodes; i++) {
			for (int j = 0; j < 2; j++) {
				pheromoneValues[i][j]=initialPheromoneValue;
			}
			
		}
	}
	//Todo: new node or address for start node and end node
	public void createSearchSpace() {
		startNode.top=searchSpace[0][0];
		startNode.bottom=searchSpace[0][1];
		
		
		
		for(int i=0;i<numberOfNodes-1;i++) {
			searchSpace[i][0].top=searchSpace[i+1][0];
			searchSpace[i][0].bottom=searchSpace[i+1][1];
			searchSpace[i][1].bottom=searchSpace[i+1][0];
			searchSpace[i][1].bottom=searchSpace[i+1][1];
		}
		
		searchSpace[numberOfNodes-1][0].top=endNode;
		searchSpace[numberOfNodes-1][0].bottom=endNode;
		
		searchSpace[numberOfNodes-1][1].top=endNode;
		searchSpace[numberOfNodes-1][1].bottom=endNode;
		
	}
	
	
	public double transitionProbability(int fromNode , int toNode, double pheromoneFactor, double heuristicFactor) {
		double denominator=calculateDenominator( pheromoneFactor,heuristicFactor );
		double probability=(Math.pow(pheromoneValues[fromNode][toNode], pheromoneFactor)*
				Math.pow(heurisiticValue(searchSpace[fromNode][toNode]),heuristicFactor ))/denominator;
				
	}
	
	
	
	private double calculateDenominator(double pheromoneFactor, double heuristicFactor) {
		double sum=0;
		for(int i=0;i<numberOfNodes;i++) {
			for(int j=0;j<2;j++) {
				sum+=Math.pow(pheromoneValues[i][j], pheromoneFactor)*Math.pow(heurisiticValue(searchSpace[i][j]),heuristicFactor )
			}
		}
		return sum;
	}
	
	
	public double fitnessFunction( int K,String o, String g) {
		int hammingDistance=calculateHammingDistance(o,g);
		
		double fitnessValue=(2*numberOfNodes-hammingDistance)/(2*numberOfNodes);
		return fitnessValue;
	}
	
		
	private int calculateHammingDistance(int K,String o, String g) {
		 int i = 0, count = 0; 
		 int upperBound=(int) Math.pow(2, numberOfNodes-1);
		    while (i <= upperBound) 
		    { 
		        if (o.charAt(i) != o.charAt(i)) 
		            count++; 
		        i++; 
		    } 
		    return count; 
	}
	
	public void pheromoneUpdate(int Q,int K) {
		for(int i=0;i<pheromoneValues.length;i++) {
			for (int j = 0; j < pheromoneValues[i].length; j++) {
				this.pheromoneValues[i][j]=pheromoneValues[i][j]+fitnessFunction(K);
			}
		}
	}
	
	public void pheromoneEvaporate(double sigma) {
		for(int i=0;i<pheromoneValues.length;i++) {
			for (int j = 0; j < pheromoneValues[i].length; j++) {
				this.pheromoneValues[i][j]=pheromoneValues[i][j]*sigma;
			}
		}
	}
	
	public void printSearchSpace(Node startNode) {
//		Node temp=startNode;
//		if(temp.top==endNode&&temp.bottom==endNode) {
//			return;
//		}else {
//			System.out.printf("%d", temp.i);
//			printSearchSpace(temp.top);
//			printSearchSpace(temp.top);
//		}
		
		for (int i = 0; i < searchSpace.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.printf("node [%d] [%d] having value %d \n", i,j,searchSpace[i][j].i);
			}
		}
	}
}
