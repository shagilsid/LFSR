
public class LFSRMain {

	public static void main(String[] args) {
		Node startNode=new Node(-1);
		Node endNode=new Node(2);
		SearchSpace sp=new SearchSpace(startNode, endNode, 4,5);
		sp.createSearchSpace();
		sp.fitnessFunction(K, o, g);
		sp.updateBestAntSolution()// dont know how to do it
		sp.pheromoneUpdate(Q, K);
		sp.pheromoneEvaporate( sigma);

	}

}
