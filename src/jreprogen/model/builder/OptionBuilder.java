package jreprogen.model.builder;

import java.util.ArrayList;
import java.util.List;
import jreprogen.model.Model;
import jreprogen.model.Option;
import jreprogen.model.Value;
import jreprogen.model.Value.ContextRef;

public class OptionBuilder {

	//return type must either be null or a return type
	private final Value generatedValue;
	private List<Value> params = new ArrayList<Value>();
	
	public OptionBuilder(Value generatedValue) {
		this.generatedValue = generatedValue;
	}
	
	public boolean isChain() {
		return (generatedValue instanceof ContextRef);
	}
	
	public OptionBuilder param(Value value) {
		params.add(value);
		return this;
	}

	public Option build(Model model) {
		Value[] values = new Value[params.size()];
		values = params.toArray(values);
		return new Option(generatedValue, values);
		
	}
}
