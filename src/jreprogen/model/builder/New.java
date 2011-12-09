package jreprogen.model.builder;

import java.util.HashMap;
import java.util.Map;

import jreprogen.model.Context;

public class New implements ModelBuilder, ContextBuilder, PhraseBuilder {

	public static ModelBuilder modelBuilder() {
		return new New();
	}
	
	private Map<String, Context> contexts = new HashMap<String, Context>();
	
	private New() {
		
	}
	
	private Context currentContext;
	@Override
	public ContextBuilder context(String name) {
		currentContext = new Context(name);
		contexts.put(name, currentContext);
		return this;
	}
		
}
