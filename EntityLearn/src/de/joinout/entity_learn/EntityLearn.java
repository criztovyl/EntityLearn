package de.joinout.entity_learn;

import java.util.HashMap;

import de.joinout.criztovyl.tools.connector.Connector;
import de.joinout.criztovyl.tools.file.Path;
import de.joinout.criztovyl.tools.json.JSONFile;
import de.joinout.criztovyl.tools.json.creator.JSONCreators;
import de.joinout.criztovyl.tools.log4j.Log4JEnvironment;

public class EntityLearn {

	private Connector<String> entities;
	private EntityLearnGUI gui;
	private JSONFile jsonFile;
	
	/**
	 * Setup a new instance
	 */
	public EntityLearn(){
		new Log4JEnvironment();
		gui = new EntityLearnGUI();

		jsonFile = new JSONFile(new Path("user.home", true).append(".entityLearn").append("entities.json"));
		
		entities = new Connector<>(jsonFile.getJSONObject(), JSONCreators.STRING);
	}
	/**
	 * 
	 * @return the GUI
	 */
	public EntityLearnGUI getGUI(){
		return gui;
	}
	/**
	 * Returns the entities
	 * @return a {@link HashMap} with {@link String}s as key and values
	 */
	public Connector<String> getEntities(){
		return entities;
	}
	/**
	 * Writes all data to a JSON file.
	 */
	public void save(){
		jsonFile.setData(entities.getJSON().toString());
		jsonFile.write();
		
	}
}
