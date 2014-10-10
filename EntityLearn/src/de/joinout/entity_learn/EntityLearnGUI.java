package de.joinout.entity_learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import de.joinout.criztovyl.tools.connector.Connector;
import de.joinout.criztovyl.tools.gui.GUI;

public class EntityLearnGUI extends GUI{
	
	private static final int ENGLISH = 1;
	private static final int TRANSLATION = 2;
	
	@SuppressWarnings("unused")
	private final Logger logger;
	private Text key, val;
	private Button save, load, random, load_by_key, load_by_val;
	private Group group, load_group;
	private List<String> randomLoad;
	private int lastRandomID;

	public EntityLearnGUI(){
		super();
		
		logger = LogManager.getLogger();
		randomLoad = new ArrayList<>();
		lastRandomID = 0;
		
		//Set layout
		getShell().setLayout(new FillLayout());
		
		//Set title and tool-tip
		getShell().setText("Learn with entities!");
		getShell().setToolTipText("This is a windows!");
		
		//Create Groups
		group = new Group(getShell(), SWT.BORDER);
		load_group = new Group(group, SWT.BORDER);
		
		//Create text fields and buttons
		key = new Text(group, SWT.BORDER);
		val = new Text(group, SWT.BORDER);
		save = new Button(group, SWT.BORDER);
		load = new Button(group, SWT.BORDER);
		random = new Button(group, SWT.BORDER);
		load_by_key = new Button(load_group, SWT.RADIO);
		load_by_val = new Button(load_group, SWT.RADIO);
		
		//Add button texts
		save.setText("Save");
		load.setText("Load");
		random.setText("Random");
		load_by_key.setText("Load/Randomize Key");
		load_by_val.setText("Load/Randomize by Value");
		
		//Add them
		key.setLocation(0, 0);
		key.pack();
		val.setLocation(key.getBounds().x + key.getBounds().width, key.getBounds().y);
		val.pack();
		save.setLocation(0, val.getBounds().height + val.getBounds().y);
		save.pack();
		load.setLocation(save.getBounds().x + save.getBounds().width, save.getBounds().y);
		load.pack();
		random.setLocation(load.getBounds().x + load.getBounds().width, load.getBounds().y);
		random.pack();
		load_by_key.setLocation(0, 0);
		load_by_key.pack();
		load_by_val.setLocation(0, load_by_key.getBounds().y + load_by_key.getBounds().height);
		load_by_val.pack();
		load_group.setLocation(0, load.getBounds().y + load.getBounds().height);
		load_group.pack();
		
		//Pack shell
		getShell().pack();
		
		
		
		
		//Add actions
		save.addSelectionListener(new SelectionAdapter() {
			 @Override
			 public void widgetSelected(SelectionEvent e){
				 
				 
				 if(!key.getText().equals("") && !val.getText().equals(""))
					 EntityLearnMain.getEntityLearn().getEntities().addBidirectionalConnection(key.getText(), ENGLISH, val.getText(), TRANSLATION);
				
			 }
		});
		load.addSelectionListener(new SelectionAdapter() {
			 @Override
			 public void widgetSelected(SelectionEvent e){
				 
				 String keyString = load_by_key.getSelection() ? key.getText() : load_by_val.getSelection() ? val.getText() : null;
				 
				 String valString = (String) EntityLearnMain.getEntityLearn().getEntities().getConnected(keyString);
				 
				 keyString = keyString == null ? "" : keyString;
				 valString = valString == null ? "" : valString;
				 
				 key.setText(load_by_key.getSelection() ? keyString : load_by_val.getSelection() ? valString : "");
				 val.setText(key.getText().equals(keyString) ? valString : key.getText().equals(valString) ? keyString : "");
				 
			 }
		});
		random.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){

				if(randomLoad.isEmpty() || load_by_key.getSelection() && lastRandomID == TRANSLATION || load_by_val.getSelection() && lastRandomID == ENGLISH){
					randomLoad = new ArrayList<>(EntityLearnMain.getEntityLearn().getEntities().getConnectors(load_by_key.getSelection() ? ENGLISH : load_by_val.getSelection() ? TRANSLATION : Connector.FOO_ID));
					Collections.shuffle(randomLoad);
				}
				
				String keyString = randomLoad.isEmpty() ? "" : randomLoad.remove(0);
				String valString = "";
				
				key.setText(load_by_key.getSelection() ? keyString : load_by_val.getSelection() ? valString : "");
				val.setText(key.getText().equals(keyString) ? valString : key.getText().equals(valString) ? keyString : "");

			}
		});
		
	}
}
