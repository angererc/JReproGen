package jreprogen.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A context contains phrases that are allowed in the context.
 * Corresponds to a builder(-interface)
 * @author angererc
 *
 */
public class Context {

	private Model model;
	private final String name;
	private Map<String, Phrase> phrases = new HashMap<String,Phrase>();
	
	private Set<Value> usedValues;
	private Map<Value,List<Generator>> generatorMap;
	
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
	
	public void reset() {
		this.generatorMap = null;
		this.usedValues = null;
	}
	
	public void addPhrase(Phrase phrase) {
		this.reset();
		if(phrases.containsKey(phrase.getName())) {
			throw new ModelException("Phrase with name " + phrase.getName() + " already exists in context " + name);
		}
		
		phrases.put(phrase.getName(), phrase);
		phrase.setContext(this);
	}
	
	public void validate(List<Message> messages) {
		collectUsedValues();
		collectGeneratorMap();
		
		for(Value val : usedValues) {
			if(! generatorMap.containsKey(val)) {
				messages.add(new Message("No generator found for value " + val + "!"));
			}
		}
		
	}
	
	public boolean hasBeenValidated() {
		return usedValues != null && generatorMap != null;
	}
	
	public Map<Value,List<Generator>> getGeneratorMap() {
		return generatorMap;
	}
	
	public List<Generator> getGeneratorsForValue(Value val) {
		List<Generator> result = generatorMap.get(val);
		if(result == null) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}
	public Set<Value> getUsedValues() {
		return usedValues;
	}
	
	private void collectUsedValues() {
		usedValues = new HashSet<Value>();
		this.traverse(new AbstractVisitor() {
			@Override
			public void visit(Option o) {
				for(Value val : o.getValues()) {
					usedValues.add(val);
				}
			}
		});
		
	}
	
	private void addGeneratorToMap(Map<Value,List<Generator>> map, Generator gen) {
		Value generatedValue = gen.getGeneratedValue();
		List<Generator> list = map.get(generatedValue);
		if(list == null) {
			list = new ArrayList<Generator>();
			map.put(generatedValue, list);
		}
		list.add(gen);
	}
	
	private void collectGeneratorMap() {
		generatorMap = new HashMap<Value,List<Generator>>();
		
		//collect phrase options that generate non-void values
		this.traverse(new AbstractVisitor() {
			@Override
			public void visit(Option o) {
				Value generatedValue = o.getGeneratedValue();
				if( ! generatedValue.equals(Value.VoidVal)) {
					addGeneratorToMap(generatorMap, o);
				}
			}
		});
		
		//add the immediates, if used
		if(usedValues.contains(Value.BoolVal))
			addGeneratorToMap(generatorMap, Generator.ImmediateBool);
		
		if(usedValues.contains(Value.IntVal))
			addGeneratorToMap(generatorMap, Generator.ImmediateInt);
		
		if(usedValues.contains(Value.StringVal))
			addGeneratorToMap(generatorMap, Generator.ImmediateString);
		
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
	
	@Override public String toString() {
		return "Context(" + name + ")";
	}
	
}
