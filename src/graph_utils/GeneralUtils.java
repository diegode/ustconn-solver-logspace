package graph_utils;

import misc.Label;

public class GeneralUtils {
	
	/**
	 * Return the graph that is implied by the rotation map of the product
	 * @param G The initial graph (Pre-Product)
	 * @param N The size of the graph (Post-Product)
	 * @param De The regularity degree of the graph (Post-Product)
	 * @return
	 */
	public static Graph extractGraphFromRotMap(Graph G, int N, int De, int type) {
		Graph G_out = new Graph(N);
		for (int i=0; i<N; i++)
			for (int j=0; j<N; j++) {
				if (type == 0)
					G_out.set(i, (int)GraphOperators.regulateWithConstDegree(G, new Label(i,j)).v, 1);
				if (type == 1) {
					System.out.println("("+i+","+j + ") = "+GraphOperators.powerProduct(G, 2, new Label(i,j)).v);
					G_out.inc(i, (int)GraphOperators.powerProduct(G, 2, new Label(i,j)).v);
				}
			}
		
		return G_out;
	}
}
