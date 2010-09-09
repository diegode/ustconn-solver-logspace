package misc;

import graph_utils.Graph;

import java.io.File;

import ustconn_solver_algorithm.Solver;


public class GraphUtilsTester {

	public static void main(String[] args) {
		File path = new File("/Users/tomgur/Code/USTCONN_Solver/src/misc/4clique.matrix");
		Graph G = new Graph(path, 4);
		System.out.println(G);
		Solver.solve_ustconn(G, 0, 1);
		
		/*Graph g = new Graph(3);
		int k2=1;
		for (int i=0; i<g.dim(); i++) {
			for (int j=0; j<g.dim(); j++)
				g.set(i,j,k2);
			k2++;
		}
		//System.out.println(g);
	
	
	int k=1;
	Graph g1 = new Graph(2);
	for (int i=0; i<g1.dim(); i++)
		for (int j=0; j<g1.dim(); j++){
			g1.set(i,j,k);
			k++;
		}*/

	//System.out.println(g1);
	//for (int i=0; i<g1.dim(); i++)
	//	for (int j=0; j<g1.dim(); j++)
	//		System.out.println(GraphOperators.powerProduct(g1, 10, i, j));
	
	//System.out.println(GraphOperators.regulateWithConstDegree(G, new Label(7,2)));
	//G.normalizeAdjMatrix(3);
	//System.out.println(G.calculateSpectralGap());
	//path = new File("/Users/tomgur/Code/USTCONN_Solver/src/misc/3clique.matrix");
	//Graph H = new Graph(path);	
	//System.out.println(H);
	//System.out.println(GraphOperators.zigzagProduct(G, H, 0, 1, 0, 0));
		
	}
	
}
