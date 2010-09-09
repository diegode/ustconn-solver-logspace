package ustconn_solver_algorithm;

import misc.Label;
import graph_utils.Expander;
import graph_utils.GabberGalilExpander;
import graph_utils.Graph;

public class Solver {
	
	public static boolean solve_ustconn(Graph G, int s, int t) {
		Expander H = new GabberGalilExpander();
		MainTransformation tau = new MainTransformation(G,H);
		Label[] path = new Label[tau.Gl_diameter_bound()];
		return bruteForceSolver(G, H, s, t, tau, path, tau.Gl_diameter_bound());
	}
		
	private static boolean bruteForceSolver(Graph G, Graph H, long s, long t, MainTransformation tau, Label[] path, int diameter) {
		if (diameter == 0)
			return (s==t);
		else
			for (int i=0; i<tau.Gl_regularity_degree(); i++) {
				long next_s = tau.rotGl(new Label(s,i)).v; // Move one step
				path[tau.Gl_diameter_bound() - diameter] = new Label(s,i); //save current path 
				if (bruteForceSolver(G, H, next_s, t, tau, path, diameter-1))
					return true;
			}
		return false;
	}
	
}
