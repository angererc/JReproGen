package jreprogen.model;

/**
 * A Phrase is similar to a production in a grammar. 
 * Corresponds to a method in a builder(-interface)
 * @author angererc
 *
 */
public class Phrase {

	private final String name;
	
	public Phrase(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
