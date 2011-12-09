package jreprogen.model;

import java.io.PrintStream;

/**
 * A Phrase is similar to a production in a grammar. 
 * Corresponds to a method(-name) in a builder(-interface); 
 * the concrete method variant is represented by an Option 
 * 
 * A Phrase contains Options
 * @author angererc
 *
 */
public class Phrase {

	private Context context;
	private final String name;
	private final Option[] options;
	
	public Phrase(String name, Option...options) {
		this.name = name;
		this.options = options;
		for(Option op : options) {
			op.setPhrase(this);
		}
	}
	
	public Context getContext() {
		return context;
	}
	
	void setContext(Context context) {
		if(this.context != null) throw new ModelException("Cannot set context twice!");
		this.context = context;
	}
	
	public String getName() {
		return name;
	}
	
	public Option[] getOptions() {
		return options;
	}
	
	public void traverse(Visitor v) {
		v.visit(this);
		for(Option o : options) {
			o.traverse(v);
		}
	}
	public void describeTo(PrintStream out) {
		out.println("\t\t<phrase name=\"" + name + "\">");
		for(Option o : options) {
			o.describeTo(out);
		}
		out.println("\t\t</phrase>");
	}
}
