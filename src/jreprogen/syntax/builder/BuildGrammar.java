package jreprogen.syntax.builder;

import java.util.HashMap;
import java.util.Map;

import jreprogen.syntax.Alternative;
import jreprogen.syntax.ComplexElement;
import jreprogen.syntax.Element;
import jreprogen.syntax.Grammar;
import jreprogen.syntax.LiteralElement;
import jreprogen.syntax.LiteralToken;
import jreprogen.syntax.Production;

public class BuildGrammar implements GrammarBuilder, ProductionBuilder, AlternativeBuilder, ElementBuilder {

	public static GrammarBuilder inPackage(String _package) {
		return new BuildGrammar(_package);
	}
	
	private Grammar grammar = new Grammar();
	private Map<String, Production> productionsTable = new HashMap<String,Production>();
	private Production currentProduction;
	private Alternative currentAlternative;
	private Element currentElement;
	
	public BuildGrammar(String pack) {
		grammar.setPackageName(pack);		
	}
	
	private Production getOrCreateProduction(String name) {
		Production result;
		if(productionsTable.containsKey(name)) {
			result = productionsTable.get(name);
		} else {
			result = new Production();
			result.setName(name);
			productionsTable.put(name, result);
			grammar.addProduction(result);
		}	
		return result;
	}
	@Override
	public ProductionBuilder production(String prod) {
		currentProduction = getOrCreateProduction(prod);		
		return this;
	}
	
	@Override
	public AlternativeBuilder alternative(String name) {
		currentAlternative = new Alternative();
		currentAlternative.setName(name);
		currentProduction.addAlternative(currentAlternative);
		return this;
	}
	
	@Override
	public ElementBuilder is() {
		return this;
	}
	
	@Override
	public ElementBuilder literal(String literal) {
		LiteralElement element = new LiteralElement();
		element.setToken(new LiteralToken(literal));
		currentElement = element;
		currentAlternative.addElement(currentElement);
		return this;
	}
	
	@Override
	public ElementBuilder ref(String name) {
		Production prod = getOrCreateProduction(name);
		ComplexElement element = new ComplexElement();
		element.setProduction(prod);
		currentElement = element;
		currentAlternative.addElement(currentElement);
		return this;
	}

	@Override
	public ElementBuilder many() {
		currentElement.setModifier(Element.Modifier.MANY);
		return this;
	}

	@Override
	public ElementBuilder one() {
		currentElement.setModifier(Element.Modifier.ONE);
		return this;
	}

	@Override
	public ElementBuilder oneOrMore() {
		currentElement.setModifier(Element.Modifier.AT_LEAST_ONE);
		return this;
	}

	@Override
	public ElementBuilder optional() {
		currentElement.setModifier(Element.Modifier.OPTIONAL);
		return this;
	}
	
	@Override
	public Grammar build() {
		return grammar;
	}
}
