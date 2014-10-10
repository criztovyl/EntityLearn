package de.joinout.entity_learn;

public class EntityLearnMain {


	private static EntityLearn entityLearn;

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args){
		entityLearn = new EntityLearn();
		
		entityLearn.getGUI().start();
		
		entityLearn.save();
	}
	
	public static EntityLearn getEntityLearn(){
		return entityLearn;
	}
}
