package jreprogen.syntax;

import java.util.ArrayList;
import java.util.List;

public class Production {
	
	private Grammar grammar;
	private String name;
	private List<Alternative> alternatives = new ArrayList<Alternative>();
	
	public Grammar getGrammar() {
		return grammar;
	}
	
	void setGrammar(Grammar grammar) {
		this.grammar = grammar;
	}
	
	public void remove() {
		grammar.removeProduction(this);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Alternative> getAlternatives() {
		return alternatives;
	}
	public void addAlternative(Alternative alternative) {
		alternative.setProduction(this);
		this.alternatives.add(alternative);
	}
	
	void removeAlternative(Alternative alternative) {
		this.alternatives.remove(alternative);
		alternative.setProduction(null);
	}
	
	public void visit(SyntaxVisitor visitor) {
		visitor.visit(this);
	}
	
	public void visitAlternatives(SyntaxVisitor visitor) {
		for(Alternative alt : alternatives)
			alt.visit(visitor);
	}
}
