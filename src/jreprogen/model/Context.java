package jreprogen.model;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A context contains phrases that are allowed in the context.
 * Corresponds to a builder(-interface)
 * @author angererc
 *
 */
public class Context implements Value {

	private Model model;
	private final String name;
	private Map<String, Phrase> phrases = new HashMap<String,Phrase>();
	
	public Context(String name) {
		this.name = name;
	}
	
	public Model getModel() {
		return model;
	}
	
	void setModel(Model model) {
		if(this.model != null) throw new ModelException("Cannot set model twice!");
		this.model = model;
	}
	
	public String getName() {
		return name;
	}
	
	public void addPhrase(Phrase phrase) {
		if(phrases.containsKey(phrase.getName())) {
			throw new ModelException("Phrase with name " + phrase.getName() + " already exists in context " + name);
		}
		
		phrases.put(phrase.getName(), phrase);
		phrase.setContext(this);
	}
	
	/*
	 * TODO do this in a visitor:
	 */
	public void collectGeneratorsForValue(List<Option> result, Value value) {		
		for(Phrase phrase : phrases.values()) {
			for(Option option : phrase.getOptions()) {
				if(option.getGeneratedValue().equals(value)) {
					result.add(option);
				}
			}
		}
	}
	
	public void traverse(Visitor v) {
		v.visit(this);
		for(Phrase p : phrases.values()) {
			p.traverse(v);
		}
	}
	
	public void describeTo(PrintStream out) {
		out.println("\t<context name=\"" + name + "\">");
		for(Phrase p : phrases.values()) {
			p.describeTo(out);
		}
		out.println("\t</context>");
	}
	
	@Override public Value resolveReferences(Model model) { return this; }
	@Override public String toString() {
		return "Context(" + name + ")";
	}
	
	@Override public String asNodeName() { return name.replace('.', '_') + "Node"; }
	
	@Override public String asJavaType() { return name; }
}
