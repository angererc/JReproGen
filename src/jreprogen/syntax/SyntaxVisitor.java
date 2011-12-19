package jreprogen.syntax;

public interface SyntaxVisitor {

	void visit(Grammar grammar);
	void visit(LiteralToken token);
	void visit(Production production);
	void visit(Alternative alternative);
	void visit(LiteralElement keyword);
	void visit(ComplexElement complex);
}
