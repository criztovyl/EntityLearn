package de.joinout.entity_learn.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.joinout.criztovyl.tools.connector.Connector;
import de.joinout.criztovyl.tools.gui.GUI;
import de.joinout.criztovyl.tools.gui.GUITools;
import de.joinout.entity_learn.EntityLearnMain;

public class ControlGUI extends GUI{
	
	//private static final int UNDEFINED = -1;
	private static final int A = 1;
	private static final int B = 2;
	
	private Text a, b, a_def, b_def;
	private Label l_a, l_b, l_a_def, l_b_def;
	private Button save, load, random, load_by_a, load_by_b;
	private Group group, load_group;
	private List<String> randomLoad;
	private int lastRandomID;
	private GUITools guiTools;

	public ControlGUI(){
		super();
		
		randomLoad = new ArrayList<>();
		lastRandomID = 0;
		guiTools = new GUITools();
		
		//Set layout
		getShell().setLayout(new FillLayout());
		
		//Create Groups
		group = new Group(getShell(), SWT.BORDER);
		load_group = new Group(group, SWT.BORDER);
		
		//Create text fields and buttons
		
		a = new Text(group, SWT.BORDER);
		b = new Text(group, SWT.BORDER);
		a_def = new Text(group, SWT.BORDER);
		b_def = new Text(group, SWT.BORDER);
		
		save = new Button(group, SWT.BORDER);
		load = new Button(group, SWT.BORDER);
		random = new Button(group, SWT.BORDER);
		load_by_a = new Button(load_group, SWT.RADIO);
		load_by_b = new Button(load_group, SWT.RADIO);
		
		l_a = new Label(group, SWT.LEFT | SWT.HORIZONTAL);
		l_b = new Label(group, SWT.LEFT);
		l_a_def = new Label(group, SWT.LEFT);
		l_b_def = new Label(group, SWT.LEFT);

		
		//Set texts and tool-tips
		
		getShell().setText("Learn with entities!- Control Centre");
		getShell().setToolTipText("This is a windows!");
		
		save.setText("Save");
		save.setToolTipText("Click to save your entered pair.");
		
		load.setText("Load");
		load.setToolTipText("Click to load a corresponding value.");
		
		random.setText("Random");
		random.setToolTipText("Click to load a random key");
		
		load_by_a.setText("Load/Randomize by A");
		load_by_b.setText("Load/Randomize by B");
		
		l_a.setText("A:");
		l_b.setText("B:");
		l_a_def.setText("A Def.:");
		l_b_def.setText("B Def.:");
		
		//Standart selections
		load_by_a.setSelection(true);
		
		//Add them and positions them
		guiTools.setClientArea(group);
		
		guiTools.inside(l_a);
		l_a.pack();
		
		guiTools.right(a);
		a.pack();
		guiTools.setWidth(200);
		
		guiTools.right(l_b);
		l_b.pack();
		
		guiTools.right(b);
		b.pack();
		guiTools.setWidth(200);
		
		guiTools.belowRow(save);
		save.pack();
		
		guiTools.right(load);
		load.pack();
		
		guiTools.right(random);
		random.pack();
		
		guiTools.belowRow(load_group);
		guiTools.setClientArea(load_group);
		
		guiTools.inside(load_by_a);
		load_by_a.pack();
		
		guiTools.below(load_by_b);
		load_by_b.pack();
		
		guiTools.below(load_group);
		load_group.pack();
		
		//Pack shell
		getShell().pack();
		
		
		
		
		//Add actions
		save.addSelectionListener(new SelectionAdapter() {
			 @Override
			 public void widgetSelected(SelectionEvent e){
				 
				 EntityLearnMain.getEntityLearn().addEntity(a.getText(), b.getText());
				
			 }
		});
		load.addSelectionListener(new SelectionAdapter() {
			 @Override
			 public void widgetSelected(SelectionEvent e){
				 
				 load();
				 
			 }
		});
		random.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){

				if(randomLoad.isEmpty() || load_by_a.getSelection() && lastRandomID == B || load_by_b.getSelection() && lastRandomID == A){
					randomLoad = new ArrayList<>(EntityLearnMain.getEntityLearn().getConnectors(load_by_a.getSelection() ? A : load_by_b.getSelection() ? B : Connector.FOO_ID));
					Collections.shuffle(randomLoad);
				}
				
				String keyString = randomLoad.isEmpty() ? "" : randomLoad.remove(0);
				String valString = "";
				
				a.setText(load_by_a.getSelection() ? keyString : load_by_b.getSelection() ? valString : "");
				b.setText(a.getText().equals(keyString) ? valString : a.getText().equals(valString) ? keyString : "");

			}
		});	
		
	}

	private void load(){
		
		//Receive key
		 String keyString = load_by_a.getSelection() ? a.getText() : load_by_b.getSelection() ? b.getText() : "";
		 
		 //Receive value and set to empty string if is null
		 String valString = EntityLearnMain.getEntityLearn().getConnected(keyString);
		 valString = valString == null ? "" : valString;
		 
		 //Set texts
		 a.setText(load_by_a.getSelection() ? keyString : load_by_b.getSelection() ? valString : "");
		 b.setText(a.getText().equals(keyString) ? valString : a.getText().equals(valString) ? keyString : "");
	}
}
