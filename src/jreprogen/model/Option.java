package jreprogen.model;

import java.io.PrintStream;

/**
 * an Option belongs to a Phrase
 * Corresponds to an overloaded method (method name = phrase, option = variant)
 * 
 * @author angererc
 *
 */
public class Option implements Generator {

	private Phrase phrase;
	private final Value generatedValue;
	private final Value[] values;
	
	public Option(Value generatedValue, Value...values) {
		this.generatedValue = generatedValue;
		this.values = values;
	}
	
	public Phrase getPhrase() {
		return phrase;
	}
	
	void setPhrase(Phrase phrase) {
		if(this.phrase != null) throw new ModelException("Cannot set phrase twice!");
		this.phrase = phrase;
	}
	
	@Override
	public Value getGeneratedValue() {
		return generatedValue;
	}
	
	public Value[] getValues() {
		return values;
	}
	
	@Override
	public <T> T map(GeneratorMapper<T> mapper) { return mapper.map(this); }
	
	public void traverse(Visitor v) {
		v.visit(this);
	}
	
	public void describeTo(PrintStream out) {
		out.print("\t\t\t(");
		for(int i = 0; i < values.length; i++) {
			if(i > 0) out.print(", ");
			out.print(values[i]);
		}
		out.println(")->" + generatedValue);
	}
	
}
