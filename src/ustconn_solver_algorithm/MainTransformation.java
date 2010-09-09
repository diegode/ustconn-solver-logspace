package ustconn_solver_algorithm;

import misc.Label;
import graph_utils.Expander;
import graph_utils.Graph;
import graph_utils.GraphOperators;

public class MainTransformation {
	
	private Graph G;
	private Expander H;
	private int l;
	private int Gl_diameter_bound;
	
	public MainTransformation(Graph G, Expander H) {
		this.G = G;
		this.H = H;
		
	 	// Calculate l - the number of iterations of zigzag + powering
		double N = G.dim() * G.dim(); // The size of G_reg
		int D = H.regularityDegree();
		int l=0;
		while (Math.pow((1 - (1/(D*Math.pow(N, 2)))), Math.pow(2,l)) > 1/2)
			l++;
		this.l = l;
		
		this.Gl_diameter_bound = (int)Math.log(N*Math.pow(Gl_regularity_degree(),l));
		
	}
	
	/**
	 * The rotation map of G_l - A De^16 regular graph over N*N * (De^16)^l vertices
	 * @param v_i A label (vertex+edge)
	 * @return Rot_G_l(v,i)
	 */
	public Label rotGl(Label v_i) {
		if (l==0)
			return GraphOperators.regulateWithConstDegree(G, v_i);
		else
			return power(v_i, 8, l);
	}
	
	
	/**
	 * Used to calculate the composition of the Zigzag product and the Power product
	 * @param v_a_i_j Vertez (v,a) and edges (i,j)
	 * @param k the amount of iterations of G_i
	 * @return Rot_G_k(v,a,i,j)
	 */
	private Label zigzag(Label v_a_i_j, int k) {
		long v = v_a_i_j.v / H.dim(); // The vertex in G
		long a = v_a_i_j.v % H.dim(); // The vertex in H, inside v
		int i = (int)(v_a_i_j.i / H.regularityDegree()); // The first "zig"
		int j = (int)(v_a_i_j.i % H.regularityDegree()); // The second "zig"
			
		// First "zig": (a',i') = Rot_H(a,i)
		Label at_it = H.rot(a, i);
		long at = at_it.v;
		long it = at_it.i;
		
		// The "zag": (w,b') = Rot_G(v,a')
		Label w_bt;
		if (k==0)
			w_bt = GraphOperators.regulateWithConstDegree(G, new Label(v, at));
		else
			w_bt = power(new Label(v, at), 8, k-1);
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
	 * Used to calculate the composition of the Zigzag product and the Power product
	 * @param v_i A label (vertex+edge) 
	 * @param m The level of the powering
	 * @param k the amount of iterations
	 * @return
	 */
	private Label power(Label v_i, int m, int k) {
			if (m==1)
				return zigzag(v_i, k);
			else {
				long currEdge = (v_i.i / (int)Math.pow(Gl_regularity_degree(), m-1));
				long nextEdge = (v_i.i % (int)Math.pow(Gl_regularity_degree(), m-1));
				// Traverse through one edge
				Label currStep = zigzag(new Label(v_i.v, currEdge), k);
				// Continue recursively 
				return power(new Label(currStep.v, nextEdge), m-1, k);
			}
		}
	
	public int Gl_diameter_bound() {
		return Gl_diameter_bound;
	}
	
	public long Gl_regularity_degree() {
		return H.dim();
	}
}
