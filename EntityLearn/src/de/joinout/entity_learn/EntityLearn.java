package de.joinout.entity_learn;

import de.joinout.criztovyl.tools.connector.Connector;
import de.joinout.criztovyl.tools.file.Path;
import de.joinout.criztovyl.tools.json.JSONFile;
import de.joinout.criztovyl.tools.json.creator.JSONCreators;

public class EntityLearn extends Connector<String>{
	
	public static final int UNDEFINED = -1;
	public static final int KEY = 1;
	public static final int VALUE = 2;

	private JSONFile jsonFile;
	
	/**
	 * Setup a new instance
	 */
	public EntityLearn(){
		
		super(new JSONFile(new Path("user.home", true).append(".entityLearn").append("entities.json")).getJSONObject(), JSONCreators.STRING);

		jsonFile = new JSONFile(new Path("user.home", true).append(".entityLearn").append("entities.json"));
		
	}
	/**
	 * Writes all data to a JSON file.
	 */
	public void save(){
		jsonFile.setData(getJSON().toString());
		jsonFile.write();
		
	}
	/**
	 * Adds a connection between the key and value.<br>
	 * Key and value gets corresponding IDs.
	 * @param key the key
	 * @param val the value
	 */
	public void addEntity(String key, String val){
		 if(!key.equals("") && !val.equals(""))
			 addBidirectionalConnection(key, KEY, val, VALUE);
	}
	/**
	 * Adds a connection between the key and some values.<br>
	 * Key and values get corresponding IDs.
	 * @param key the key
	 * @param vals the values
	 */
	public void addEntity(String key, String[] vals){
		for(String val : vals)
			addEntity(key, val);
	}
}
