package de.joinout.entity_learn;

import de.joinout.criztovyl.tools.gui.GUI;
import de.joinout.criztovyl.tools.log4j.Log4JEnvironment;
import de.joinout.entity_learn.gui.StartGUI;

public class EntityLearnMain {


	private static EntityLearn entityLearn;
	private static GUI gui;

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args){
		
		new Log4JEnvironment();
		
		entityLearn = new EntityLearn();
		
		gui = new StartGUI();
		
		gui.start();
		
		entityLearn.save();
	}
	
	public static EntityLearn getEntityLearn(){
		return entityLearn;
	}
}
