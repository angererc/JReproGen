package jreprogen.codegen.syntax;

public abstract class AbstractSyntaxVisitor implements SyntaxVisitor {

	@Override
	public void visit(Grammar grammar) {
		grammar.visitTokens(this);
		grammar.visitProductions(this);
	}
	
	@Override
	public void visit(KeywordToken token) {
	}

	@Override
	public void visit(Production production) {
		production.visitAlternatives(this);
	}

	@Override
	public void visit(Alternative alternative) {
		alternative.visitElements(this);
	}

	@Override
	public void visit(KeywordElement keyword) {
	}

	@Override
	public void visit(ComplexElement complex) {
	}

}
