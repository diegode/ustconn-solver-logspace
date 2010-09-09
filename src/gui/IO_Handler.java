package gui;


import graph_utils.Graph;

import java.util.Random;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class IO_Handler {
	 
	static int x1, y1, x2, y2; 
	static boolean first = true;
	static boolean s_selected = false;
	static boolean t_selected = false;
	static private Label t_label;
	static private Label s_label;
	static int s = 0;
	static int t = 1;
	static Matrix m = new Matrix();
	static Button[] arr;
	static private Label Instructions;

	public static void visualizeMatrix(final int[][] matrix, Display display){
		
		final Shell shell = new Shell (display);

		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width/3,monitor_bounds.height/2));
		
		arr = new Button[matrix.length];
		
		for(int i=0; i<matrix.length; i++){
			 arr[i] = new Button (shell, SWT.PUSH);
			 arr[i].setText ("");
			 
			 Random generator = new Random();
			 int x = generator.nextInt(shell.getSize().x-15);
			 int y = generator.nextInt(shell.getSize().y-15);
			 arr[i].setBounds(x, y, 15, 15);
		}
		
		final GC gc = new GC(shell);
		
		shell.addPaintListener(new PaintListener(){ 
	        public void paintControl(PaintEvent e){ 
	        	for(int i=0; i<matrix.length; i++){
	    			for(int j=0; j<matrix.length; j++){
	    				if(matrix[i][j]==1){
	    					x1 = arr[i].getBounds().x;
	    					y1 = arr[i].getBounds().y;
	    					x2 = arr[j].getBounds().x;
	    					y2 = arr[j].getBounds().y;
	    					e.gc.drawLine(x1, y1, x2, y2);
	    				}	
	    			}
	    		}
	        } 
	    }); 
	  
		shell.open ();
			while (!shell.isDisposed ()) {
				if (!display.readAndDispatch ()) display.sleep ();
			}
			display.dispose ();
	}
	
	
	
	public static void drawMatrix(Display display){
		
		final Shell shell = new Shell ();
		shell.setSize(449, 408);

		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width/3,monitor_bounds.height/2));
		
		final GC gc = new GC(shell);
	    
		shell.addMouseListener(new MouseListener() {
		      public void mouseDown(MouseEvent e) {
		     
		    	  Button button = new Button (shell, SWT.RADIO);
		    	  button.setText ("");
		    	  button.setBounds(e.x, e.y, 16, 16);
		    	  Point p = new Point(e.x, e.y);	
		    	  m.addVertex(p);
		    	  
		    	  final int a = e.x;
		       	  final int b = e.y;
		     	  
		       	  button.addMouseListener(new MouseListener() {

					@Override
					public void mouseDoubleClick(MouseEvent e) {
						if (!s_selected) {
							s = e.button;
							s_label.setVisible(true);
							s_selected = true;
						}
						else if (!t_selected) {
							t = e.button;
							t_label.setVisible(true);
							t_selected = true;
						}

					}

					@Override
					public void mouseDown(MouseEvent e) {
					}

					@Override
					public void mouseUp(MouseEvent e) {						
					}
		       	  });
		       	
		    	  button.addListener(SWT.Activate, new Listener(){
		      		public void handleEvent(Event e) {
		      			if(first){
		      				x1 = a;
		      				y1 = b;
		      				first=false;
		      			}
		      			else{
		      				x2 = a;
		      				y2 = b;
		      				first=true;

		      				gc.drawLine(x1, y1, x2, y2);
		      				m.addEdge(new Point(x1, y1), new Point(x2, y2));		      				
		      			}
		      		}
		      	});
		      }

		      
			@Override
			public void mouseDoubleClick(MouseEvent e) {}

			@Override
			public void mouseUp(MouseEvent e) {}
		 });
		
		
	  Button okButton = new Button (shell, SWT.PUSH);
	  okButton.setText ("  OK  ");
	  okButton.setBounds(183, 344, 60, 30);
	  {
		  Instructions = new Label(shell, SWT.NONE);
		  Instructions.setText("Click on an empty space to create a vertex.\nDouble-Click on a vertex to set the start vertex (s).\nDouble-Click on another vertex to set the end vertex (t).\nClick on two vertices to create an edges between them.\n");
		  Instructions.setBounds(12, 12, 419, 70);
	  }
	  {
		  s_label = new Label(shell, SWT.NONE);
		  s_label.setText("Start vetrex (s) was selected");
		  s_label.setBounds(12, 351, 171, 30);
		  s_label.setVisible(false);
	  }
	  {
		  t_label = new Label(shell, SWT.NONE);
		  t_label.setText("End vetrex (t) was selected");
		  t_label.setBounds(255, 351, 176, 30);
		  t_label.setVisible(false);
	  }

	 okButton.addListener(SWT.Selection, new Listener(){
    		public void handleEvent(Event e) {
				Graph G = new Graph(m.toArray());
				System.out.println(G);
    		}
	  });
	 
	 shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
	
}
	