package jreprogen.model;
/**
 * A context contains phrases that are allowed in the context.
 * Corresponds to a builder(-interface)
 * @author angererc
 *
 */
public class Context {

	private final String name;
	
	public Context(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
