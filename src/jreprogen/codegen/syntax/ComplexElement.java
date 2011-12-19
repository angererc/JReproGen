package jreprogen.codegen.syntax;

public class ComplexElement extends Element {

	private Production production;
	
	public void setProduction(Production production) {
		this.production = production;
	}
	
	public Production getProduction() {
		return production;
	}
	
	@Override
	public void visit(SyntaxVisitor visitor) {
		visitor.visit(this);
	}
}
