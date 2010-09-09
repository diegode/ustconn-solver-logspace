package graph_utils;

import misc.Label;

/**
 * Creates a Gabber-Galil expander of regular degree 8 over 8^16 vertices
 */
public class GabberGalilExpander extends Expander {
	
	public GabberGalilExpander() {
		super(8);
	}
	
	public long dim() {
		return (long)Math.pow(8, 16);
	}
	
	
	public Label rot(long v, int i) {
		long m = (long)Math.sqrt(this.dim());
		long x = v/m; // The first coordinate of the m*m graph
		long y = v%m; // The second coordinate
		long v_out;
		long v_out_x = 0;
		long v_out_y = 0;
		long i_out = 0;
		switch(i) {
		case 0:
			v_out_x = (x + y)%m;
			v_out_y = y;
			i_out = 1;
			break;
		case 1:
			v_out_x = (x - y)%m;
			v_out_y = y;
			i_out = 0;
			break;
		case 2:
			v_out_x = (x + (y+1))%m;
			v_out_y = y;
			i_out = 3;
			break;
		case 3:
			v_out_x = (x - (y+1))%m;
			v_out_y = y;
			i_out = 2;
			break;
		case 4:
			v_out_x = x;
			v_out_y = (y + x)%m;
			i_out = 5;
			break;
		case 5:
			v_out_x = x;
			v_out_y = (y - x)%m;
			i_out = 4;
			break;
		case 6:
			v_out_x = x;
			v_out_y = (y + (x+1))%m;
			i_out = 7;
			break;
		case 7:
			v_out_x = x;
			v_out_y = (y - (x+1))%m;
			i_out = 6;
			break;
		}
		v_out = v_out_x*m + v_out_y;
		return new Label(v_out,i_out);
	}

}
