package gui;

import graph_utils.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ustconn_solver_algorithm.Solver;

import com.cloudgarden.resource.SWTResourceManager;


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
public class USTCONN_Applet extends org.eclipse.swt.widgets.Composite {

	private Text browseText;
	private Label label1;
	private Label t_label;
	private Text t_val;
	private Label s_label;
	private Text s_val;
	private Button Browse;
	private Button okButton;
	private Button browseButton;
	private Button drawButton;

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	public USTCONN_Applet(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}
	
	/**
	* Initializes the GUI.
	*/
	private void initGUI() {
		try {
			this.setSize(new org.eclipse.swt.graphics.Point(400,300));
			this.setBackground(SWTResourceManager.getColor(219, 219, 219));
			FormLayout thisLayout = new FormLayout();
			this.setLayout(thisLayout);
			{
				FormData t_valLData = new FormData();
				t_valLData.left =  new FormAttachment(0, 1000, 167);
				t_valLData.top =  new FormAttachment(0, 1000, 152);
				t_valLData.width = 28;
				t_valLData.height = 14;
				t_val = new Text(this, SWT.MULTI | SWT.WRAP);
				t_val.setLayoutData(t_valLData);
				t_val.setText("1");
			}
			{
				t_label = new Label(this, SWT.NONE);
				FormData t_labelLData = new FormData();
				t_labelLData.left =  new FormAttachment(0, 1000, 67);
				t_labelLData.top =  new FormAttachment(0, 1000, 152);
				t_labelLData.width = 83;
				t_labelLData.height = 14;
				t_label.setLayoutData(t_labelLData);
				t_label.setText("End vertex (t):");
			}
			{
				s_label = new Label(this, SWT.NONE);
				FormData s_labelLData = new FormData();
				s_labelLData.left =  new FormAttachment(0, 1000, 67);
				s_labelLData.top =  new FormAttachment(0, 1000, 126);
				s_labelLData.width = 89;
				s_labelLData.height = 14;
				s_label.setLayoutData(s_labelLData);
				s_label.setText("Start vertex (s):");
			}
			{
				FormData s_valLData = new FormData();
				s_valLData.left =  new FormAttachment(0, 1000, 168);
				s_valLData.top =  new FormAttachment(0, 1000, 126);
				s_valLData.width = 27;
				s_valLData.height = 14;
				s_val = new Text(this, SWT.MULTI | SWT.WRAP);
				s_val.setLayoutData(s_valLData);
				s_val.setText("0");
			}
			{
				Browse = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData BrowseLData = new FormData();
				BrowseLData.left =  new FormAttachment(0, 1000, 306);
				BrowseLData.top =  new FormAttachment(0, 1000, 90);
				BrowseLData.width = 62;
				BrowseLData.height = 27;
				Browse.setLayoutData(BrowseLData);
				Browse.setText("Browse");
				Browse.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						System.out.println("Browse.widgetSelected, event="+evt);
						 FileDialog dialog = new FileDialog(getShell(), SWT.NULL);
						 browseText.setText(dialog.open());
					}
				});
			}
			{
				label1 = new Label(this, SWT.NONE);
				FormData label1LData = new FormData();
				label1LData.left =  new FormAttachment(0, 1000, 31);
				label1LData.top =  new FormAttachment(0, 1000, 12);
				label1LData.width = 341;
				label1LData.height = 33;
				label1.setLayoutData(label1LData);
				label1.setText("Undirected-s-t-connectivity log-space solver");
				label1.setFont(SWTResourceManager.getFont("Times New Roman", 18, 1, false, false));
			}
			{
				FormData browseTextLData = new FormData();
				browseTextLData.left =  new FormAttachment(0, 1000, 67);
				browseTextLData.top =  new FormAttachment(0, 1000, 95);
				browseTextLData.width = 233;
				browseTextLData.height = 19;
				browseText = new Text(this, SWT.NONE);
				browseText.setLayoutData(browseTextLData);
			}
			{
				okButton = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData okButtonLData = new FormData();
				okButtonLData.left =  new FormAttachment(0, 1000, 167);
				okButtonLData.top =  new FormAttachment(0, 1000, 230);
				okButtonLData.width = 58;
				okButtonLData.height = 28;
				okButton.setLayoutData(okButtonLData);
				okButton.setText("OK");
				
				okButton.addListener(SWT.Selection, new Listener(){
		    		public void handleEvent(Event e) {
		    			if(browseButton.getSelection()){
		    				File file = new File(browseText.getText());
		    				Graph G = new Graph(file);
		    				System.out.println(G);
		    				int s = Integer.parseInt(s_val.getText());
		    				int t = Integer.parseInt(t_val.getText());
		    				Solver.solve_ustconn(G, s, t);
		    			}
		    			else{
		    				IO_Handler.drawMatrix(Display.getCurrent());
		    			}
		    		}
			  });
			}
			{
				browseButton = new Button(this, SWT.RADIO | SWT.LEFT);
				FormData browseButtonLData = new FormData();
				browseButtonLData.left =  new FormAttachment(0, 1000, 55);
				browseButtonLData.top =  new FormAttachment(0, 1000, 67);
				browseButtonLData.width = 124;
				browseButtonLData.height = 16;
				browseButton.setLayoutData(browseButtonLData);
				browseButton.setText("Enter graph path:");
				
				
				browseButton.addListener(SWT.Selection, new Listener(){
		    		public void handleEvent(Event e) {
		    			browseText.setVisible(true);
		    			s_val.setVisible(true);
		    			t_val.setVisible(true);
		    			
		    		}	
		    			
			  });
			}
			{
				drawButton = new Button(this, SWT.RADIO | SWT.LEFT);
				FormData drawButtonLData = new FormData();
				drawButtonLData.left =  new FormAttachment(0, 1000, 55);
				drawButtonLData.top =  new FormAttachment(0, 1000, 202);
				drawButtonLData.width = 115;
				drawButtonLData.height = 16;
				drawButton.setLayoutData(drawButtonLData);
				drawButton.setText("Draw graph");
				
				drawButton.addListener(SWT.Selection, new Listener(){
		    		public void handleEvent(Event e) {
		    			browseText.setVisible(false);
		    			s_val.setVisible(false);
		    			t_val.setVisible(false);
		    		}
			  });
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		USTCONN_Applet inst = new USTCONN_Applet(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
