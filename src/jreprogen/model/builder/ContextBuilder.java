package jreprogen.model.builder;

import java.util.HashMap;
import java.util.Map;

import jreprogen.model.Context;
import jreprogen.model.Model;
import jreprogen.model.Phrase;

public class ContextBuilder {

	private final String name;
	private Map<String,PhraseBuilder> phrases = new HashMap<String,PhraseBuilder>();
	
	public ContextBuilder(String name) {
		this.name = name;
	}
	
	public PhraseBuilder phrase(String name) {
		PhraseBuilder pb = phrases.get(name);
		if(pb == null) {
			pb = new PhraseBuilder(name);
			phrases.put(name, pb);
		} 
		return pb;
	}
	
	public Context buildContext() {
		return new Context(name);
	}
	
	public void buildPhrases(Model model) {
		Context c = model.getContext(name);
		if(c == null) {
			throw new ModelBuilderException("shouldn't happen... call buildContext() first.");
		}
		
		for(PhraseBuilder pBuilder : phrases.values()) {
			Phrase phrase = pBuilder.build(model);
			c.addPhrase(phrase);
		}
	}
	
}
