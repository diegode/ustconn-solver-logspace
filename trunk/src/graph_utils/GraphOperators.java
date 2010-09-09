package graph_utils;

import misc.Label;

public class GraphOperators {
	
	/**
	 * Calculates the rotation map of the zigzag product of G_reg and H
	 * G_Reg is a (N*N, De^16, lambda) graph
	 * H is a (De^16, De, 1/2) graph
	 * The product is a (N*N*(De^16), De^2, f(lambda, 1/2) graph, where:
	 * f(lambda, 1/2) = (3/8)lambda + (1/2)sqrt((9/16)(lambda^2)+1)
	 * @param G Graph given by adjacencies matrix of size N 
	 * @param H Expander graph given by adjacencies matrix of size De^16, and regularity De
	 * @param v_a_i_j Vertex a_v and Edge i_j (v,i in G, a,j in H)
	 * @return Rot_G-zigzag-H((v,a),(i,j)) - a (De^2)-regular graph with (N*N)*(De^16) vertices
	 */
	public static Label zigzagProduct(Graph G, Expander H, Label v_a_i_j) {
		long v = v_a_i_j.v / H.dim(); // The vertex in G
		long a = v_a_i_j.v % H.dim(); // The vertex in H, inside v
		int i = (int)(v_a_i_j.i / H.regularityDegree()); // The first "zig"
		int j = (int)(v_a_i_j.i % H.regularityDegree()); // The second "zig"
			
		// First "zig": (a',i') = Rot_H(a,i)
		Label at_it = H.rot(a, i);
		long at = at_it.v;
		long it = at_it.i;
		
		// The "zag": (w,b') = Rot_G(v,a')
		Label w_bt = regulateWithConstDegree(G, new Label(v, at));
		long w = w_bt.v;
		long bt = w_bt.i;
		
		// Second "zig": (b,j') = Rot_H(b',j)
		Label b_jt = H.rot(bt, j);
		long b = b_jt.v;
		long jt = b_jt.i;
		
		// Output: ((w,b), (j',i'))
		return new Label( (w*H.dim())+b, (jt*H.dim())+it );
	}
	
	/**
	 * Calculates the rotation map of (G_reg)^m, thus we get all of the path that
	 * uses m-moves. we increase the regularity degree to D^m.
	 * @param G Graph given by adjacencies matrix of size N
	 * @param m The level of the powering
	 * @param v_i Pair (v,i) of a vertex and an edge
	 * @return Rot_G_reg(v,i) a (D^m)-regular graph over N*N vertices
	 */
	public static Label powerProduct(Graph G, int m, Label v_i) {
		if (m==1)
			return regulateWithConstDegree(G, v_i);
		else {
			int currEdge = ((int)v_i.i / (int)Math.pow(G.regularityDegree(), m-1));
			int nextEdge = ((int)v_i.i % (int)Math.pow(G.regularityDegree(), m-1));
			// Traverse through one edge
			Label currStep = regulateWithConstDegree(G, new Label(v_i.v, currEdge));
			// Continue recursively 
			return powerProduct(G, m-1, new Label(currStep.v, nextEdge));
		}
	}

	
	/**
	 * Calculates the rotation map of G_reg, which is a regular, non bipartite,
	 * constant degree transformation of G, by turning each vertex in to a "ring"
	 * of size N, adding self loops, and connecting the rings according to G.
	 * @param G Graph given by adjacencies matrix of size N
	 * @param v_i Pair (v,i) of a vertex and an edge
	 * @return Rot_G(v,i) a De-regular graph with N*N vertices
	 */
	public static Label regulateWithConstDegree(Graph G, Label v_i) {
		int N = (int)G.dim();
		int v = (int)(v_i.v)/N;
		int w = (int)v_i.v%N;
		
		// Turn to the right (in the ring)
		if (v_i.i==0) {
			if(w<N-1)
				return new Label((v*N)+(w+1), 1);
			else
				return new Label((v*N), 1);
		}
		
		// Turn to the left (in the ring)
		if (v_i.i==1) {
			if(w>0)
				return new Label((v*N)+(w-1), 0);
			else
				return new Label((v*N)+(N-1), 0);
		}
		
		// Jump between rings
		if (v_i.i==2) {
			if(G.get(v, w) > 0)
				return new Label((w*N)+v, 2);
			else
				return new Label((v*N)+w, 2);
		}
		
		// Self-loop
		else
			return v_i;

		}
}
