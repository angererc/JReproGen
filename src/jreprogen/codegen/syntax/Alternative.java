package jreprogen.codegen.syntax;

import java.util.ArrayList;
import java.util.List;

public class Alternative {

	private Production production;
	private String name;
	private List<Element> elements = new ArrayList<Element>();
	
	public Production getProduction() {
		return production;
	}
	
	void setProduction(Production production) {
		this.production = production;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Element> getElements() {
		return elements;
	}
	public void addElement(Element element) {
		element.setAlternative(this);
		this.elements.add(element);
	}
	void removeElement(Element element) {
		this.elements.remove(element);
		element.setAlternative(null);
	}
	
	public void visit(SyntaxVisitor visitor) {
		visitor.visit(this);
	}
	
	public void visitElements(SyntaxVisitor visitor) {
		for(Element elem : elements)
			elem.visit(visitor);
	}
}
