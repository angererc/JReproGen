package jreprogen.model.builder;

import java.util.ArrayList;
import java.util.List;

import jreprogen.model.Model;
import jreprogen.model.Option;
import jreprogen.model.Phrase;
import jreprogen.model.Value;

public class PhraseBuilder {

	private final String name;
	private List<OptionBuilder> options = new ArrayList<OptionBuilder>();
	
	public PhraseBuilder(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public OptionBuilder option(Value generatedValue) {
		OptionBuilder ob = new OptionBuilder(generatedValue);
		options.add(ob);
		return ob;
	}
	
	public Phrase build(Model model) {
		Option[] optArr = new Option[options.size()];
		for(int i = 0; i < optArr.length; i++) {
			optArr[i] = options.get(i).build(model);
		}
		
		return new Phrase(name, optArr);
	}

}
