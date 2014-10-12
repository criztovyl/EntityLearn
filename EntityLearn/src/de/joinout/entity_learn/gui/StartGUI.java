package de.joinout.entity_learn.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import de.joinout.criztovyl.tools.gui.GUI;

public class StartGUI extends GUI{
	
	private Button control;

	public StartGUI(){
		
		super();
		
		//Create objects
		control = new Button(getShell(), SWT.BORDER);
		
		
		//Set texts
		getShell().setText("Learn with entities! - Start");
		control.setText("Control Centre");
		
		//Add and position them
		control.setLocation(0, 0);
		control.pack();
		
		getShell().pack();
		
		//Add actions
		control.addSelectionListener(new SelectionAdapter() {
			 @Override
			 public void widgetSelected(SelectionEvent e){
				 
				 new ControlGUI().start();
				
			 }
		});
	}
}
