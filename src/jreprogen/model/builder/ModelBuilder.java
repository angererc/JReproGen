package jreprogen.model.builder;

import java.util.HashMap;
import java.util.Map;

import jreprogen.model.Context;
import jreprogen.model.Model;

public class ModelBuilder {

	private Map<String, ContextBuilder> contexts = new HashMap<String, ContextBuilder>();
	
	public ContextBuilder context(String name) {
		ContextBuilder cb = contexts.get(name);
		if(cb == null) {
			cb = new ContextBuilder(name);
			contexts.put(name, cb);
		}
		return cb;
	}
	
	public Model build() {
		
		Map<String, Context> contextTable = new HashMap<String, Context>();
		//first, build all context objects
		//phrases can reference other contexts and we want to be able to resolve those
		for(ContextBuilder cb : contexts.values()) {
			Context c = cb.buildContext();
			contextTable.put(c.getName(), c);
		}
		
		//then, build model
		Model model = new Model(contextTable);
		
		//then, build all the phrases
		for(ContextBuilder cb : contexts.values()) {
			cb.buildPhrases(model);
		}
		
		return model;
	}
}
